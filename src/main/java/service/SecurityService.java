package service;

import entity.User;

import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class SecurityService {

    private final UserService userService;

    public SecurityService(UserService userService) {
        this.userService = userService;
    }

    private final List<String> tokens = Collections.synchronizedList(new ArrayList<>());

    public boolean checkCookies(Cookie[] cookies) {
        if (cookies != null){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user-token")) {
                    if (tokens.contains(cookie.getValue())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Cookie generateCookie() {
        String token = UUID.randomUUID().toString();
        String cookieName = "user-token";
        tokens.add(token);
        return new Cookie(cookieName, token);
    }

    public boolean validateUser(String email, String password) {
        User userByEmail = userService.getUserByEmail(email);
        if (userByEmail == null) {
            userService.saveUser(email, password);
            return true;
        } else if (userByEmail.getPassword().equals(password)) {
            return true;
        }
        return false;
    }
}