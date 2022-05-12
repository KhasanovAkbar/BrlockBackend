package univ.tuit.backend.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import univ.tuit.backend.config.exception.klass.AlreadyExistsException;
import univ.tuit.backend.config.exception.klass.InvalidParameterException;
import univ.tuit.backend.config.exception.klass.ResourceNotFoundException;
import univ.tuit.backend.domain.User;
import univ.tuit.backend.dto.EditUserRequest;
import univ.tuit.backend.dto.LogOutResponse;
import univ.tuit.backend.dto.LoginRequest;
import univ.tuit.backend.dto.LoginResponse;
import univ.tuit.backend.security.JwtProvider;
import univ.tuit.backend.service.UserService;
import univ.tuit.backend.store.AuthStore;
import univ.tuit.backend.store.UserStore;
import univ.tuit.backend.store.jpo.UserJpo;
import univ.tuit.backend.utils.PasswordUtils;

import java.util.List;

@Service
public class UserLogic implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserLogic.class);
    @Autowired
    UserStore userStore;

    @Autowired
    AuthStore authStore;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    @Override
    public User create(User user) throws AlreadyExistsException {
        if (userStore.existsByPhoneNumber(user.getPhoneNumber())) {
            User retrieve = userStore.retrieve(user.getCarNumber());
            if (retrieve.getStatus().equals("ACTIVE")) {
                throw new AlreadyExistsException("Phone number already exists");
            }
        }
        return userStore.create(user);
    }

    @Override
    public User register(String phoneNumber) {
        return userStore.register(phoneNumber);
    }

    @CachePut(cacheNames = "users", key = "#editUserRequest.carNumber")
    @Override
    public User edit(EditUserRequest editUserRequest) {
        logger.info("user updated in db");
        return userStore.update(editUserRequest);
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User user = authStore.retrieve(loginRequest.getPhoneNumber());
        if (user == null) {
            throw new ResourceNotFoundException(User.class, "User with phone number not found.");
        }
        if (!PasswordUtils.isValidPassword(loginRequest.getPassword(), user.getPassword())) {
            throw new InvalidParameterException("Invalid password");
        }
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getPhoneNumber(), loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        UserJpo principal = (UserJpo) authenticate.getPrincipal();
        String token = jwtProvider.generateJwtToken(principal);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setAuthToken(token);
        loginResponse.setUserJpo(principal);
        return loginResponse;
    }

    @Override
    public User retrieve(Integer sequence) {
        return userStore.retrieve(sequence);
    }

    @Cacheable(cacheNames = "users", key = "#carNumber")
    @Override
    public User retrieve(String carNumber) {
        logger.info("fetching data from db");
        return userStore.retrieve(carNumber);
    }

    @Override
    public List<User> getAllUsers() {
        return userStore.retrieve();
    }


    @CacheEvict(cacheNames = "users", key = "#carNumber")
    @Override
    public void delete(Integer id, String carNumber) {
        logger.info("deleting data from db");
        userStore.delete(id);
    }

    @Override
    public LogOutResponse logOut() {
        return userStore.logout();
    }
}
