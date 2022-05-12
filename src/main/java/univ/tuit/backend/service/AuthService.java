package univ.tuit.backend.service;

import univ.tuit.backend.domain.User;

public interface AuthService {

    User retrieve(String phoneNumber);

}
