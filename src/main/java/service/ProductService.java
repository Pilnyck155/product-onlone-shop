package service;

import entity.Product;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    public List<Product> findAll(){
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1, "milk", 27, new Date(2021-5-12)));
        productList.add(new Product(2, "bread", 24, new Date(2021-6-21)));
        productList.add(new Product(3, "honey", 29, new Date(2021-7-18)));
        return productList;
    }
}
