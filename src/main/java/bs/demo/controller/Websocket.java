package bs.demo.controller;

import bs.demo.modelandrespository.ChatList;
import bs.demo.modelandrespository.ChatListRespository;
import bs.demo.modelandrespository.ChatMsg;
import bs.demo.modelandrespository.ChatMsgRespository;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;


import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@CrossOrigin(value = "*", allowCredentials = "true")
@Component
@RestController
@ServerEndpoint("/websocket/{username}")
public class Websocket {

    private static ChatListRespository chatListRespository;
    private static ChatMsgRespository chatMsgRespository;

    @Autowired
    public void setChatListRespository(ChatListRespository chatListRespository) {
        Websocket.chatListRespository = chatListRespository;
    }
    @Autowired
    public void setChatMsgRespository(ChatMsgRespository chatMsgRespository) {
        Websocket.chatMsgRespository = chatMsgRespository;
    }


    /**
     * 在线人数
     */
    private static int onlineNumber = 0;
    /**
     * 以用户的姓名为key，WebSocket为对象保存起来
     */
    private static Map<String, Websocket> clients = new ConcurrentHashMap<String, Websocket>();
    /**
     * 会话
     */
    private Session session;
    /**
     * 用户名称
     */
    private String username;
    /**
     * 建立连接
     *
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam("username") String username, Session session) {
        onlineNumber++;
        System.out.println("现在来连接的客户id：" + session.getId() + "用户名：" + username);
        this.username = username;
        this.session = session;
        System.out.println("有新连接加入！ 当前在线人数" + onlineNumber);
        try {
            //将socket连接保存起来
            clients.put(username, this);
        } catch (Exception e) {
            System.out.println(username + "上线的时候通知所有人发生了错误");
        }
    }

    @OnClose
    public void onClose()
    {
        onlineNumber--;
        //webSockets.remove(this);
        clients.remove(username);
        try {
            System.out.println(username + "已下线");
        }
        catch (Exception e){
            System.out.println(username+"下线的时候通知所有人发生了错误");
        }
        System.out.println("有连接关闭！ 当前在线人数" + onlineNumber);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        try {
            System.out.println("来自客户端消息：" + message + "客户端的id是：" + session.getId());
            JSONObject jsonObject = JSON.parseObject(message);
            String sender = jsonObject.getString("sender");
            int chatId = jsonObject.getInteger("chatid");
            String msg = jsonObject.getString("msg");
            System.out.println("sender：" + sender + ";chatid:"+ chatId + ";msg:" + msg);
            sendMessage(sender, chatId, msg);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("发生了错误了");
        }
    }

    private void sendMessage(String sender, int chatId, String msg) throws IOException {
        System.out.println("运行到这里了1");
        ChatList chat = chatListRespository.findByChatid(chatId);
        System.out.println("运行到这里了2");
        String aid = sender.equals(chat.getUser())?chat.getAnotherUser():chat.getUser();
        System.out.println("运行到这里了3");
        try{
            ChatMsg chatMsg = new ChatMsg();
            chatMsg.setChatId(chatId);
            chatMsg.setSender(sender);
            chatMsg.setContent(msg);
            chatMsg.setTime(new Timestamp(new Date().getTime()));
            chatMsgRespository.save(chatMsg);
        }catch (Exception e){
            System.out.println("插入数据库失败");
        }

        for (Websocket item : clients.values()){
            if(item.username.equals(aid)){
                item.session.getAsyncRemote().sendText(msg);
            }
        }

    }

}
