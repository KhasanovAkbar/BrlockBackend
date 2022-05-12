
package univ.tuit.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import univ.tuit.backend.domain.Chat;
import univ.tuit.backend.service.ChatService;

@RestController
@RequestMapping("/api/v1/chat")
public class ChatController {

    @Autowired
    ChatService chatService;

    @PostMapping(value = "/chat_history")
    HttpEntity<?> save_chat(@RequestBody Chat chat) {
        Chat save = chatService.save(chat);
        return ResponseEntity.ok(save);
    }
}
