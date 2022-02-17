package dao;

import entity.Product;
import util.ProductRowMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class ProductDao {
    ProductRowMapper productRowMapper = new ProductRowMapper();
    String url = "jdbc:postgresql://localhost:3002/product_shop";
    String user = "postgres";
    String password = "password";

    private static final String GET_ALL_PRODUCTS = "SELECT id, name, price, creation_date FROM Product";


    public List<Product> findAll(){
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_PRODUCTS);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            List<Product> list = new ArrayList<>();
            while (resultSet.next()){
                Product product = productRowMapper.mapRow(resultSet);
                list.add(product);
            }
         return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
