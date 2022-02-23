package dao.jdbc;

import dao.jdbc.mapper.UserRowMapper;
import entity.Product;
import entity.User;

import java.sql.*;

public class JdbcUserDao implements UserDao {
    private static final UserRowMapper USER_ROW_MAPPER = new UserRowMapper();
    private final ConnectionFactory connectionFactory;

    public JdbcUserDao(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    private static final String SAVE_USER = "INSERT INTO Shop_user (email, password) VALUES (?, ?)";
    private static final String GET_USER_BY_EMAIL = "SELECT id, email, password FROM Shop_user WHERE email = ?";
    private static final String EXIST_USER_BY_EMAIL = "EXIST (SELECT id, email, password FROM Shop_user WHERE email = ?)";

    @Override
    public boolean saveUser(String email, String password) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_USER)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't save user", e);
        }
    }

    @Override
    public User getUserByEmail(String email) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return USER_ROW_MAPPER.mapRow(resultSet);
                }
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't find user with email " + email, e);
        }
    }

    @Override
    public boolean isUserExist(String email) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't check if user with email: " + email + " exist", e);
        }
    }
}
