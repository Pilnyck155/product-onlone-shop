package entity;

import java.sql.Date;

public class Product {
    private int id;
    private String name;
    private int price;
    private Date creationDate;

    public Product(int id, String name, int price, Date creationDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.creationDate = creationDate;
    }
    public Product(String name, int price, Date creationDate) {
        this.name = name;
        this.price = price;
        this.creationDate = creationDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public Date getCreationDate() {
        return creationDate;
    }
}
