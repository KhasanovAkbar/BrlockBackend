package univ.tuit.backend.store;

import univ.tuit.backend.domain.Chat;

public interface ChatStore {
    Chat saveChat(Chat chat);

}
