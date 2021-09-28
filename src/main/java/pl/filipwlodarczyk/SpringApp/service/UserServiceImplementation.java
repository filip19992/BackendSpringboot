package pl.filipwlodarczyk.SpringApp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.filipwlodarczyk.SpringApp.domain.Role;
import pl.filipwlodarczyk.SpringApp.domain.User;
import pl.filipwlodarczyk.SpringApp.repo.RoleRepo;
import pl.filipwlodarczyk.SpringApp.repo.UserRepo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImplementation implements UserService, UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email);

        if (user == null) {
            log.error("There is no user with such email");
            throw new UsernameNotFoundException("There is no user with such email");
        } else {
            log.info("User found");
        }
        Collection<SimpleGrantedAuthority> authroties = new ArrayList<>();

        Collection<Role> roles = user.getRoles();

        roles.forEach(role ->
                authroties.add(new SimpleGrantedAuthority(role.getName())));

        return new org.springframework.security.core.userdetails.
                User(user.getEmail(),user.getPassword(),authroties);
    }

    @Autowired
    private final UserRepo userRepo;

    @Autowired
    private final RoleRepo roleRepo;


    @Override
    public User saveUser(User user) {
        return userRepo.save(user);
    }


    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role");
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String email, String roleName) {
        log.info(String.format("Adding role %s to %s", roleName, email));
        User userByEmail = userRepo.findByEmail(email);
        Role roleByName = roleRepo.findByName(roleName);
        userByEmail.getRoles().add(roleByName);

    }

    @Override
    public User getUser(String email) {
        log.info(String.format("Getting user with email %s", email));
        return userRepo.findByEmail(email);
    }

    @Override
    public List<User> getUsers() {
        log.info("Getting all users");
        return userRepo.findAll();
    }

    @Override
    public List<User> getAdmins() {
        log.info("Getting all admins");

        List<User> allUsers = userRepo.findAll();
        List<User> adminList = new ArrayList<>();

        findAdmins(allUsers, adminList);

        return adminList;
    }

    private void findAdmins(List<User> allUsers, List<User> adminList) {
        for (User user : allUsers) {
            Collection<Role> roles = user.getRoles();
            if (roles.contains(roleRepo.findByName("ROLE_ADMIN"))) {
                adminList.add(user);
            }
        }
    }


}
