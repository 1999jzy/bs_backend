package bs.demo.modelandrespository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatListRespository extends JpaRepository <ChatList, Integer>{
    public ChatList findByChatid(int chatId);
    public List<ChatList> findAllByUser(String user);
    public List<ChatList> findAllByAnotherUser(String anotherUser);
    public ChatList findByUserAndAnotherUser(String user, String anotherUser);
}
