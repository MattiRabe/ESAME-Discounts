package discounts;
import java.util.*;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.List;

public class Discounts {

	private TreeMap<Integer, Card> cards = new TreeMap<>();
	private TreeMap<String, Product> products = new TreeMap<>();

	
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
	}

	public int getDiscount(String categoryId) {
        return -1;
	}

	//R4
	public int addPurchase(int cardId, String... items) throws DiscountsException {
        return -1;
	}

	public int addPurchase(String... items) throws DiscountsException {
        return -1;
	}
	
	public double getAmount(int purchaseCode) {
        return -1.0;
	}
	
	public double getDiscount(int purchaseCode)  {
        return -1.0;
	}
	
	public int getNofUnits(int purchaseCode) {
        return -1;
	}
	
	//R5
	public SortedMap<Integer, List<String>> productIdsPerNofUnits() {
        return null;
	}
	
	public SortedMap<Integer, Double> totalPurchasePerCard() {
        return null;
	}
	
	public int totalPurchaseWithoutCard() {
        return -1;
	}
	
	public SortedMap<Integer, Double> totalDiscountPerCard() {
        return null;
	}


}
