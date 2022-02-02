package pl.edu.zsel.contestbackend.authentication.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@NoArgsConstructor
public class AdminUser {
    private final String username = "admin";
    private final String password = "ZAQ!2wsx";

    public boolean isAdmin(User user) {
        return user.getUsername().equals(username) && user.getPassword().equals(password);
    }
}
