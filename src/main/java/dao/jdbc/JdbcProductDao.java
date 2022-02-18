package dao.jdbc;

import dao.ProductRowMapper;
import entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class JdbcProductDao implements ProductDao {
    ProductRowMapper productRowMapper = new ProductRowMapper();
    ConnectionFactory connectionFactory = new ConnectionFactory();

    private static final String GET_ALL_PRODUCTS = "SELECT id, name, price, creation_date FROM Product";
    private static final String GET_PRODUCT_BY_ID = "SELECT id, name, price, creation_date FROM Product WHERE id = ?";
    private static final String SAVE_PRODUCT = "INSERT INTO Product (name, price, creation_date) VALUES (?, ?, ?)";
    private static final String DELETE_PRODUCT_BY_ID = "DELETE FROM Product WHERE id = ?";
    private static final String EDIT_PRODUCT_BY_ID = "UPDATE Product SET name=?, price=?, creation_date=? WHERE id=?";


    @Override
    public List<Product> findAll(){
        try(Connection connection = connectionFactory.getConnection();
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

    @Override
    public void save(Product product) {
        try(Connection connection = connectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_PRODUCT)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setDate(3, product.getCreationDate());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {
        try(Connection connection = connectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editProductById(Product product) {
        try(Connection connection = connectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(EDIT_PRODUCT_BY_ID)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setDate(3, product.getCreationDate());
            preparedStatement.setInt(4, product.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product getProductById(int id) {
        try(Connection connection = connectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCT_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();

            Product product = null;
            while (resultSet.next()){
                product = productRowMapper.mapRow(resultSet);
            }
            return product;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
