package univ.tuit.backend.service;

import univ.tuit.backend.config.exception.klass.AlreadyExistsException;
import univ.tuit.backend.domain.User;
import univ.tuit.backend.dto.EditUserRequest;
import univ.tuit.backend.dto.LogOutResponse;
import univ.tuit.backend.dto.LoginRequest;
import univ.tuit.backend.dto.LoginResponse;

import java.util.List;

public interface UserService {

    User create(User user) throws AlreadyExistsException;

    User register(String phoneNumber);

    User retrieve(Integer sequence);

    LoginResponse login(LoginRequest loginRequest);

    User edit(EditUserRequest editUserRequest);

    User retrieve(String carNumber);

    List<User> getAllUsers();

    void delete(Integer id, String carNumber);

    LogOutResponse logOut();


}
