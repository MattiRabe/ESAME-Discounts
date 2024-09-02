package discounts;

public class Card {
    private static final int BASE=1;
    private static int INCREMENTER=0;

    private String name;
    private Integer id;


    public Card(String name) {
        this.name = name;
        this.id= BASE+ INCREMENTER++;
    }


    public String getName() {
        return name;
    }


    public int getId() {
        return id;
    }

    

    

}
