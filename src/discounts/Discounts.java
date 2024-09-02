package discounts;
import java.util.*;
import java.util.stream.Collectors;

public class Discounts {

	private TreeMap<Integer, Card> cards = new TreeMap<>();
	private TreeMap<String, Product> products = new TreeMap<>();
	private TreeMap<String, Integer> categoryDiscounts = new TreeMap<>();
	private TreeMap<Integer, Purchase> purchases = new TreeMap<>();


	
	//R1
	public int issueCard(String name) {
		Card c = new Card(name);
		cards.put(c.getId(), c);
	    return c.getId();
	}
	
    public String cardHolder(int cardN) {
        return cards.get(cardN).getName();
    }
    

	public int nOfCards() {
	       return cards.size();

	}
	
	//R2
	public void addProduct(String categoryId, String productId, double price) 
			throws DiscountsException {
		if(products.containsKey(productId)) throw new DiscountsException();
		
		products.put(productId, new Product(categoryId, productId, price));
		if(!categoryDiscounts.containsKey(categoryId)) categoryDiscounts.put(categoryId, 0);
	}
	
	public double getPrice(String productId) 
			throws DiscountsException {
		if(!products.containsKey(productId)) throw new DiscountsException();

        return products.get(productId).getPrice();
	}

	public int getAveragePrice(String categoryId) throws DiscountsException {
		return (int)Math.round(products.values().stream().filter(p->p.getCategoryId().equals(categoryId))
		.collect(Collectors.summarizingDouble(Product::getPrice)).getAverage());
	}
	
	//R3
	public void setDiscount(String categoryId, int percentage) throws DiscountsException {
		if(!categoryDiscounts.containsKey(categoryId)) throw new DiscountsException();
		if(percentage<0 || percentage>50) throw new DiscountsException();

		categoryDiscounts.put(categoryId, percentage);
		
	}

	public int getDiscount(String categoryId) {
        return categoryDiscounts.get(categoryId);
	}

	//R4
	public int addPurchase(int cardId, String... items) throws DiscountsException {
		double amount=0.0;
		double discount=0.0;

		for(int i=0; i<items.length; i++){
            String[] p = items[i].split(":");
			if(!products.containsKey(p[0])) throw new DiscountsException();
			products.get(p[0]).addNumUnitPurchased(Integer.parseInt(p[1]));
			double tot= ((double)Integer.parseInt(p[1])*products.get(p[0]).getPrice())-(((double)Integer.parseInt(p[1])*products.get(p[0]).getPrice())*((double)categoryDiscounts.get(products.get(p[0]).getCategoryId())/(double)100));
			amount+=tot;
			discount+=(((double)Integer.parseInt(p[1])*products.get(p[0]).getPrice())*((double)categoryDiscounts.get(products.get(p[0]).getCategoryId())/(double)100));
        }
		
		Purchase pur = new Purchase(true, cardId, items, amount, discount);
		purchases.put(pur.getId(), pur);
		cards.get(cardId).addPurchase(amount);
		cards.get(cardId).addDiscount(discount);
        return pur.getId();
	}

	public int addPurchase(String... items) throws DiscountsException {
        double amount=0.0;

		for(int i=0; i<items.length; i++){
            String[] p = items[i].split(":");
			if(!products.containsKey(p[0])) throw new DiscountsException();
			products.get(p[0]).addNumUnitPurchased(Integer.parseInt(p[1]));
			double tot= (double)Integer.parseInt(p[1])*products.get(p[0]).getPrice();
			amount+=tot;
        }

		Purchase pur = new Purchase(false, 0, items, amount, 0.0);
		purchases.put(pur.getId(), pur);
        return pur.getId();
	}
	
	public double getAmount(int purchaseCode) {
        return purchases.get(purchaseCode).getAmount();
	}
	
	public double getDiscount(int purchaseCode)  {
        return purchases.get(purchaseCode).getDiscount();
	}
	
	public int getNofUnits(int purchaseCode) {
        return purchases.get(purchaseCode).getNumUnits();
	}
	
	//R5
	public SortedMap<Integer, List<String>> productIdsPerNofUnits() {
        return products.values().stream().filter(p->p.getNumUnitsPurchased()!=0)
		.sorted(Comparator.comparing(Product::getNumUnitsPurchased).thenComparing(Product::getProductId))
		.collect(Collectors.groupingBy(Product::getNumUnitsPurchased, TreeMap::new, Collectors
		.mapping(Product::getProductId, Collectors.toList())));
	}
	
	public SortedMap<Integer, Double> totalPurchasePerCard() {
        return cards.values().stream().filter(c->c.getTotPurchase()!=0.0)
		.sorted(Comparator.comparing(Card::getId))
		.collect(Collectors.toMap(Card::getId, Card::getTotPurchase, (p1, p2)->p1, TreeMap::new));
	}
	
	public int totalPurchaseWithoutCard() {
        return (int)purchases.values().stream().filter(p->p.getWithCard()==false)
		.collect(Collectors.summarizingDouble(Purchase::getAmount)).getSum();
	}
	
	public SortedMap<Integer, Double> totalDiscountPerCard() {
        return cards.values().stream().filter(c->c.getTotDiscount()!=0.0)
		.sorted(Comparator.comparing(Card::getId))
		.collect(Collectors.toMap(Card::getId, Card::getTotDiscount, (p1, p2)->p1, TreeMap::new));
	}


}
