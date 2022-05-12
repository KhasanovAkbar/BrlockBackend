package univ.tuit.backend.store.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import univ.tuit.backend.store.jpo.RoleJpo;

import java.util.Optional;


public interface RoleRepository extends JpaRepository<RoleJpo, Integer> {
    Optional<RoleJpo> findByName(String roleName);

}
