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

    public List<Product> findAll(){
        List<Product> products = productDao.findAll();
        return products;
    }

    public void saveProduct(Product product) {
        productDao.save(product);
    }

    public void deleteById(int id) {
        productDao.deleteById(id);
    }

    public void editProductById(Product product) {
        productDao.editProductById(product);
    }

    public Product getProductById(int id) {
        return productDao.getProductById(id);
    }
}
