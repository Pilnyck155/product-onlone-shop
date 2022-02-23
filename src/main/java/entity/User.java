package entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class User {
    private int id;
    private String email;
    private String password;
}
