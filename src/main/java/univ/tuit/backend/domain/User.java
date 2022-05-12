package univ.tuit.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String name;
    private String surname;
    private String phoneNumber;
    private String carNumber;
    private String password;
    private Set<String> roles;
    private String status;

}
