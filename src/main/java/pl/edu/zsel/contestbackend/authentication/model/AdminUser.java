package pl.edu.zsel.contestbackend.authentication.model;

import com.google.common.hash.Hashing;
import lombok.NoArgsConstructor;
import org.springframework.aop.scope.ScopedProxyUtils;

import java.nio.charset.StandardCharsets;

@NoArgsConstructor
public class AdminUser {
    private final String username = "admin";
    private final String password = "ZAQ!2wsx";

    public boolean isAdmin(User user) {
        return user.getUsername().equals(getHash(username))
                && user.getPassword().equals(getHash(password));
    }

    private String getHash(String string) {
        return Hashing.sha256()
                .hashString(string, StandardCharsets.UTF_8)
                .toString();
    }
}
