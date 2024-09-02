package discounts;

import java.util.TreeMap;

public class Purchase {
    private static final int BASE=1;
    private static int INCREMENTER=0;


    private int id;
    private Boolean withCard;
    private int cardId;
    private double amount;
    private double discount;
    private TreeMap<String, Integer> quantities = new TreeMap<>();


    public Purchase(Boolean withCard, int cardId, String[] items, double amount, double discount) {
        this.withCard = withCard;
        this.cardId = cardId;
        this.id= BASE+ INCREMENTER++;
        for(int i=0; i<items.length; i++){
            String[] p = items[i].split(":");
            quantities.put(p[0], Integer.parseInt(p[1]));
        }
        this.amount=amount;
        this.discount=discount;
        
    }


    public Boolean getWithCard() {
        return withCard;
    }


    public int getCardId() {
        return cardId;
    }


    public int getId() {
        return id;
    }


    public double getAmount() {
        return amount;
    }


    public double getDiscount() {
        return discount;
    }


    public TreeMap<String, Integer> getQuantities() {
        return quantities;
    }

    public int getNumUnits(){
        int sum=0;
        for(int i : quantities.values()) sum+=i;
        return sum;
    }

    


}
