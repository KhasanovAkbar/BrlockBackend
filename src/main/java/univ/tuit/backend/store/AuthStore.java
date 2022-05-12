package univ.tuit.backend.store;

import org.springframework.security.core.userdetails.UserDetails;
import univ.tuit.backend.domain.User;

public interface AuthStore {

    UserDetails loadUserByUsername(String username);

    User retrieve(String phoneNumber);
}
