package pl.edu.zsel.contestbackend.authentication.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.zsel.contestbackend.authentication.model.AdminUser;
import pl.edu.zsel.contestbackend.authentication.model.User;

@RequestMapping("api/v1/auth")
@RestController
@CrossOrigin(origins = "*")
public class AuthenticationController {
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User user) {
        AdminUser adminUser = new AdminUser();

        if (adminUser.isAdmin(user)) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
