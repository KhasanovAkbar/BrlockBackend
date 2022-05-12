package univ.tuit.backend.store.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import univ.tuit.backend.store.jpo.UserJpo;

import java.util.Optional;


public interface UserRepository extends JpaRepository<UserJpo, Integer> {

    UserJpo findByPhoneNumber(String phoneNumber);

    UserJpo findByCarNumber(String carNumber);

    boolean existsByPhoneNumber(String phoneNumber);

    Optional<UserJpo> findById(Integer sequence);

    boolean existsByStatus(String status);


}
