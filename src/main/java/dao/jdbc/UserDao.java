package dao.jdbc;

import entity.User;

public interface UserDao {
    boolean saveUser(String email, String password);

    User getUserByEmail(String email);

    boolean isUserExist(String email);
}
