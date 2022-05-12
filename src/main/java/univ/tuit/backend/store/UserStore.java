package univ.tuit.backend.store;


import univ.tuit.backend.domain.User;
import univ.tuit.backend.dto.EditUserRequest;
import univ.tuit.backend.dto.LogOutResponse;

import java.util.List;

public interface UserStore {

    User create(User user);

    User register(String phoneNumber);

    User retrieve(Integer sequence);

    List<User> retrieve();

    User update(EditUserRequest editUserRequest);

    User retrieve(String carNumber);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean userStatus(String status);

    void delete(Integer sequence);

    LogOutResponse logout();
}
