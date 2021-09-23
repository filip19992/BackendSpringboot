package pl.filipwlodarczyk.SpringApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import pl.filipwlodarczyk.SpringApp.domain.Role;
import pl.filipwlodarczyk.SpringApp.domain.User;
import pl.filipwlodarczyk.SpringApp.repo.RoleRepo;
import pl.filipwlodarczyk.SpringApp.service.UserService;
import pl.filipwlodarczyk.SpringApp.service.UserServiceImplementation;

import java.util.ArrayList;

@Configuration
public class DefaultData {

    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
            userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));
            userService.saveRole(new Role(null, "ROLE_MANAGER"));
            userService.saveRole(new Role(null, "ROLE_USER"));

            userService.saveUser(new User(null, "filipwlodarczyk99@gmail.com", "password", new ArrayList<>()));
            userService.saveUser(new User(null, "maciek@gmail.com", "password", new ArrayList<>()));
            userService.saveUser(new User(null, "adrian@gmail.com", "password", new ArrayList<>()));
            userService.saveUser(new User(null, "kuba@gmail.com", "password", new ArrayList<>()));

            userService.addRoleToUser("filipwlodarczyk99@gmail.com", "ROLE_SUPER_ADMIN");
            userService.addRoleToUser("filipwlodarczyk99@gmail.com", "ROLE_ADMIN");
            userService.addRoleToUser("filipwlodarczyk99@gmail.com", "ROLE_MANAGER");
            userService.addRoleToUser("filipwlodarczyk99@gmail.com", "ROLE_USER");
            userService.addRoleToUser("maciek@gmail.com", "ROLE_ADMIN");
            userService.addRoleToUser("adrian@gmail.com", "ROLE_MANAGER");
            userService.addRoleToUser("kuba@gmail.com", "ROLE_USER");
        };
    }
}
