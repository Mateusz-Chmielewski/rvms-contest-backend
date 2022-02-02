package pl.edu.zsel.contestbackend.authentication.model;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class User {
    private final String username;
    private final String password;
}
