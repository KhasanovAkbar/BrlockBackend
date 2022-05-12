package univ.tuit.backend.store.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import univ.tuit.backend.store.jpo.ChatJpo;

public interface ChatRepository extends JpaRepository<ChatJpo, Integer> {

}
