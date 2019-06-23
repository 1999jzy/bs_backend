package bs.demo.modelandrespository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMsgRespository extends JpaRepository<ChatMsg,Integer> {
    ChatMsg findByChatId(int chatId);
    List<ChatMsg> findAllByChatId(int chatId);
}
