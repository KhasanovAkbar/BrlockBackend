package univ.tuit.backend.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import univ.tuit.backend.domain.Chat;
import univ.tuit.backend.store.jpo.ChatJpo;
import univ.tuit.backend.store.repo.ChatRepository;

@Repository
public class ChatStoreLogic implements ChatStore {

    @Autowired
    ChatRepository chatRepository;

    @Override
    public Chat saveChat(Chat chat) {
        ChatJpo chatJpo = new ChatJpo(chat);
        return chatRepository.save(chatJpo).toDomain();
    }
}
