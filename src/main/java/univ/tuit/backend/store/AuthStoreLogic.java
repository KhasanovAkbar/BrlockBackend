package univ.tuit.backend.store;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import univ.tuit.backend.domain.User;
import univ.tuit.backend.store.jpo.UserJpo;
import univ.tuit.backend.store.repo.UserRepository;

@Service
@RequiredArgsConstructor
@Repository
public class AuthStoreLogic implements UserDetailsService, AuthStore {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByPhoneNumber(s);
    }

    @Override
    public User retrieve(String phoneNumber) {
        UserJpo jpo = userRepository.findByPhoneNumber(phoneNumber);
        return jpo.toDomain();
    }
}
