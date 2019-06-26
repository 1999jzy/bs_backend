package bs.demo.modelandrespository;

import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public class Chatitem {
    private int chatId;
    private String username;
    private String face;
    private String email;

    public Chatitem(int chatId,User user){
        this.chatId = chatId;
        this.username = user.getUsername();
        this.face = user.getFace();
        this.email = user.getEmail();
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
