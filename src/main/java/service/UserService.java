package service;

import dao.jdbc.UserDao;
import entity.User;

public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean saveUser(String email, String password) {
        return userDao.saveUser(email, password);
    }

    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    public boolean isUserExist(String email){
        return userDao.isUserExist(email);
    }
}
