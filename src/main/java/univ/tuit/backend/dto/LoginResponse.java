package univ.tuit.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import univ.tuit.backend.store.jpo.UserJpo;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    private String authToken;

//    private Long currentDate;

    private UserJpo userJpo;
}
