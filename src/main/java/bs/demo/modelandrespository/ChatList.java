package bs.demo.modelandrespository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name = "chatlist")
public class ChatList implements Serializable{
    @Column(name = "chatid")
    @Id
    private int chatid;
    @Column(name = "user")
    private String user;
    @Column(name = "another_user")
    private String anotherUser;

    public int getChatid() {
        return chatid;
    }

    public void setChatid(int chatid) {
        this.chatid = chatid;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAnotherUser() {
        return anotherUser;
    }

    public void setAnotherUser(String anotherUser) {
        this.anotherUser = anotherUser;
    }
}
