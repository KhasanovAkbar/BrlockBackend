package univ.tuit.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditUserRequest {

    private Integer sequence;
    private String name;
    private String surname;
    private String phoneNumber;
    private String carNumber;
}
