package dao;

import entity.Product;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper {
    public Product mapRow (ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        int price = resultSet.getInt("price");
        Date creationDate = resultSet.getDate("creation_date");
        Product product = new Product(id, name, price, creationDate);
        return product;
    }
}
