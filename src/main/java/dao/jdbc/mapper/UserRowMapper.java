package dao.jdbc.mapper;

import entity.Product;
import entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class UserRowMapper {
    public User mapRow(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        return User.builder()
                .id(id)
                .email(email)
                .password(password)
                .build();
    }
}
