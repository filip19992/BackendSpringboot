package pl.filipwlodarczyk.SpringApp.api;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.filipwlodarczyk.SpringApp.domain.Role;
import pl.filipwlodarczyk.SpringApp.domain.User;
import pl.filipwlodarczyk.SpringApp.service.UserService;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping("/admins")
    public ResponseEntity<List<User>> getAdmins() {
        return ResponseEntity.ok().body(userService.getAdmins());
    }

    @PostMapping("/user/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/save").toUriString());

        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/role/save").toUriString());

        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PutMapping("/user/addRole")
    public ResponseEntity<?> editUserRole(@RequestBody RoleToUserForm form) {
        userService.addRoleToUser(form.getUserEmail(), form.getRoleName());
        return ResponseEntity.ok().build();
    }


    @Data
    static
    class RoleToUserForm {
        private String userEmail;
        private String roleName;
    }
}
