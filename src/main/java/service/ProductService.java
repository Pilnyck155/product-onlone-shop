package service;

import dao.ProductDao;
import entity.Product;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }
    //List<Product> productList = new ArrayList<>();

    public List<Product> findAll(){
        System.out.println("In findAll ProductService");
        List<Product> products = productDao.findAll();
        return products;
    }

    public void saveProduct(Product product) {
        System.out.println("In saveProduct ProductService");
        //product.setId(4);
        //productDao.save;
    }
}
