package pl.filipwlodarczyk.SpringApp.repo;

import pl.filipwlodarczyk.SpringApp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);
 }
