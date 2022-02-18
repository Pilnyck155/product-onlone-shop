package dao.jdbc;

import entity.Product;

import java.util.List;

public interface ProductDao {
    List<Product> findAll();

    void save(Product product);

    void deleteById(int id);

    void editProductById(Product product);

    Product getProductById(int id);
}
