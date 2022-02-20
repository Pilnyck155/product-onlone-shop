package dao.jdbc;

import dao.ProductDao;
import dao.jdbc.mapper.ProductRowMapper;
import entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class JdbcProductDao implements ProductDao {
    private static final ProductRowMapper PRODUCT_ROW_MAPPER = new ProductRowMapper();
    private final ConnectionFactory connectionFactory;

    public JdbcProductDao(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    private static final String GET_ALL_PRODUCTS = "SELECT id, name, price, creation_date FROM Product";
    private static final String GET_PRODUCT_BY_ID = "SELECT id, name, price, creation_date FROM Product WHERE id = ?";
    private static final String SAVE_PRODUCT = "INSERT INTO Product (name, price, creation_date) VALUES (?, ?, ?)";
    private static final String DELETE_PRODUCT_BY_ID = "DELETE FROM Product WHERE id = ?";
    private static final String EDIT_PRODUCT_BY_ID = "UPDATE Product SET name=?, price=?, creation_date=? WHERE id=?";


    @Override
    public List<Product> findAll() {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_PRODUCTS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            List<Product> list = new ArrayList<>();
            while (resultSet.next()) {
                Product product = PRODUCT_ROW_MAPPER.mapRow(resultSet);
                list.add(product);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't return all products", e);
        }
    }

    @Override
    public void save(Product product) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_PRODUCT)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setDate(3, Date.valueOf(product.getCreationDate()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't save product", e);
        }
    }

    @Override
    public void deleteById(int id) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't delete product with id " + id, e);
        }
    }

    @Override
    public void editProductById(Product product) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(EDIT_PRODUCT_BY_ID)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setDate(3, Date.valueOf(product.getCreationDate()));
            preparedStatement.setInt(4, product.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't edit product with id " + product.getId(), e);
        }
    }

    @Override
    public Product getProductById(int id) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCT_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return PRODUCT_ROW_MAPPER.mapRow(resultSet);
                }
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't find product with id " + id, e);
        }
    }
}
