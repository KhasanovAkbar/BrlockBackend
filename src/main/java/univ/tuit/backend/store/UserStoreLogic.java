package univ.tuit.backend.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import univ.tuit.backend.config.exception.klass.ResourceNotFoundException;
import univ.tuit.backend.domain.User;
import univ.tuit.backend.domain.enums.RoleName;
import univ.tuit.backend.dto.EditUserRequest;
import univ.tuit.backend.dto.LogOutResponse;
import univ.tuit.backend.store.jpo.RoleJpo;
import univ.tuit.backend.store.jpo.UserJpo;
import univ.tuit.backend.store.repo.RoleRepository;
import univ.tuit.backend.store.repo.UserRepository;
import univ.tuit.backend.utils.PasswordUtils;

import java.util.List;

@Repository
public class UserStoreLogic implements UserStore {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public User create(User user) {
//        Set<RoleJpo> roleJpos = new HashSet<>();
        RoleJpo roleJpos = new RoleJpo();
        user.setPassword(PasswordUtils.hashPassword(user.getPassword()));
        for (String role : user.getRoles()) {
            RoleJpo roleJpo = roleRepository.findByName(role)
                    .orElseThrow(() ->
                            new ResourceNotFoundException(RoleName.class, role + " role not found"));
//            roleJpos.add(roleJpo);
            roleJpos = roleJpo;
        }

        UserJpo jpo = new UserJpo(user, roleJpos);
        return this.userRepository.save(jpo).toDomain();
    }

    @Override
    public User register(String phoneNumber) {
        UserJpo byPhoneNumber = userRepository.findByPhoneNumber(phoneNumber.trim());
        byPhoneNumber.setStatus("ACTIVE");
        return userRepository.save(byPhoneNumber).toDomain();
    }

    @Override
    public User retrieve(Integer sequence) {
        UserJpo userJpo = userRepository.getById(sequence);
        return userJpo.toDomain();
    }

    @Override
    public List<User> retrieve() {
        List<UserJpo> jpos = userRepository.findAll();
        return UserJpo.toDomain(jpos);
    }

    @Override
    public User update(EditUserRequest editUserRequest) {
        UserJpo byId = userRepository.getById(editUserRequest.getSequence());
        byId.setName(editUserRequest.getName());
        byId.setSurname(editUserRequest.getSurname());
        byId.setPhoneNumber(editUserRequest.getPhoneNumber());
        byId.setCarNumber(editUserRequest.getCarNumber());
        return userRepository.save(byId).toDomain();
    }

    @Override
    public User retrieve(String carNumber) {
        UserJpo jpo = userRepository.findByCarNumber(carNumber);
        return jpo.toDomain();
    }

    @Override
    public boolean existsByPhoneNumber(String phoneNumber) {
        return userRepository.existsByPhoneNumber(phoneNumber);
    }

    @Override
    public boolean userStatus(String status) {
        return userRepository.existsByStatus(status);
    }

    @Override
    public void delete(Integer sequence) {
        UserJpo byId = userRepository.getOne(sequence);
        userRepository.delete(byId);
    }

    @Override
    public LogOutResponse logout() {
        LogOutResponse logOutResponse = new LogOutResponse();
        logOutResponse.setMessage("Logout");
        return logOutResponse;
    }
}
