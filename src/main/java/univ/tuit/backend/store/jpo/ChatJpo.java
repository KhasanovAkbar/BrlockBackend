package univ.tuit.backend.store.jpo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import univ.tuit.backend.domain.Chat;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "chats")
public class ChatJpo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_sequence")
    private Integer chat_sequence;

    @Column(name = "from_user")
    private String from;

    @Column(name = "to_user")
    private String to;

    private String date;

    public ChatJpo(Chat chat) {
        BeanUtils.copyProperties(chat, this);
    }

    public Chat toDomain() {
        Chat chat = new Chat();
        BeanUtils.copyProperties(this, chat);
        return chat;
    }
}
