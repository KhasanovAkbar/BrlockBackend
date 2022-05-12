package univ.tuit.backend.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import univ.tuit.backend.domain.Chat;
import univ.tuit.backend.service.ChatService;
import univ.tuit.backend.store.ChatStore;

@Service
public class ChatLogic implements ChatService {

    @Autowired
    ChatStore chatStore;

    @Override
    public Chat save(Chat chat) {
        return chatStore.saveChat(chat);
    }
}
