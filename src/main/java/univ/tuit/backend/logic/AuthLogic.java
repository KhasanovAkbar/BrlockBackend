package univ.tuit.backend.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import univ.tuit.backend.domain.User;
import univ.tuit.backend.service.AuthService;
import univ.tuit.backend.store.AuthStore;

public class AuthLogic implements UserDetailsService, AuthService {

    @Autowired
    AuthStore authStore;

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        return authStore.loadUserByUsername(phoneNumber);
    }


    @Override
    public User retrieve(String phoneNumber) {
        return authStore.retrieve(phoneNumber);
    }
}
