package discounts;

public class Card {
    private static final int BASE=1;
    private static int INCREMENTER=0;

    private String name;
    private Integer id;
    private double totPurchase;
    private double totDiscount;


    public Card(String name) {
        this.name = name;
        this.id= BASE+ INCREMENTER++;
        this.totPurchase=0.0;
        this.totDiscount=0.0;
    }


    public String getName() {
        return name;
    }


    public int getId() {
        return id;
    }


    public double getTotPurchase() {
        return totPurchase;
    }

    public void addPurchase(double d){
        this.totPurchase+=d;
    }

    public double getTotDiscount() {
        return totDiscount;
    }

    public void addDiscount(double d){
        this.totDiscount+=d;
    }

    

    

    

}
