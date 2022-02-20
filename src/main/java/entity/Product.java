package entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;


@Data
@Builder
public class Product {
    private int id;
    private String name;
    //TODO: Rewrite to double when migrate db
    private int price;
    private LocalDate creationDate;

    public Product(int id, String name, int price, LocalDate creationDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.creationDate = creationDate;
    }

    public Product(String name, int price, LocalDate creationDate) {
        this.name = name;
        this.price = price;
        this.creationDate = creationDate;
    }
}
