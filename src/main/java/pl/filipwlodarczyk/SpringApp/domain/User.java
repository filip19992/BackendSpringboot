package pl.filipwlodarczyk.SpringApp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @SequenceGenerator(name = "userId_sequence", sequenceName = "userId_sequence", allocationSize = 1)
    @GeneratedValue(generator = "userId_sequence")
    private Long id;
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();

}
