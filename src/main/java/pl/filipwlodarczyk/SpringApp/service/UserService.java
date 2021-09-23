package pl.filipwlodarczyk.SpringApp.service;

import org.springframework.stereotype.Service;
import pl.filipwlodarczyk.SpringApp.domain.Role;
import pl.filipwlodarczyk.SpringApp.domain.User;
import pl.filipwlodarczyk.SpringApp.repo.UserRepo;

import java.util.List;

@Service
public interface UserService {

    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String email, String roleName);
    User getUser(String email);
    List<User> getUsers();
    List<User> getAdmins();
}
