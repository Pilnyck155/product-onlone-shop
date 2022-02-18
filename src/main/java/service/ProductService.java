package service;

import dao.jdbc.JdbcProductDao;
import entity.Product;

import java.util.List;

public class ProductService {
    JdbcProductDao productDao;

    public ProductService(JdbcProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> findAll(){
        return productDao.findAll();
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
