package bs.demo.controller;

import bs.demo.modelandrespository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*",allowCredentials = "true")
@RestController
@RequestMapping(value = "/api")
public class ChatListController {
    @Autowired
    private ChatListRespository chatListRespository;
    @Autowired
    private UserRespository userRespository;
    @Autowired
    private ChatMsgRespository chatMsgRespository;

    @RequestMapping(value = "/addChat",method = RequestMethod.POST)
    public MsgResponsebody addChat(@RequestBody Map<String,String> httpMessageBody){
        String user = httpMessageBody.get("user");
        String anotherUser = httpMessageBody.get("anotherUser");
        if(userRespository.findByUsername(anotherUser)==null){
            return new MsgResponsebody(-1,"不存在该用户");
        }

        if(chatListRespository.findByUserAndAnotherUser(user,anotherUser) == null && chatListRespository.findByUserAndAnotherUser(anotherUser,user) == null){
            ChatList chatList = new ChatList();
            chatList.setUser(user);
            chatList.setAnotherUser(anotherUser);
            chatListRespository.save(chatList);
            return new MsgResponsebody(1,"成功添加联系人");
        }
        else {
            return new MsgResponsebody(-1,"请勿重复添加");
        }
    }

    @RequestMapping(value = "/chatList",method = RequestMethod.POST)
    public List<Chatitem> chatList(@RequestBody Map<String,String> httpMessageBody){
        String user = httpMessageBody.get("user");
        List<ChatList> list1 = chatListRespository.findAllByUser(user);
        List<ChatList> list2 = chatListRespository.findAllByAnotherUser(user);
        list1.addAll(list2);
        List<Chatitem> chatitems = list1.stream()
                .map(r -> new Chatitem(r.getChatid(),userRespository.findByUsername(r.getUser().equals(user)?r.getAnotherUser():r.getUser())))
                .collect(Collectors.toList());
        return chatitems;
    }

    @RequestMapping(value = "/chatMsg",method = RequestMethod.POST)
    public List<ChatMsg> chatMsg(@RequestBody Map<String,String> httpMessageBody){
        int chatId =  Integer.parseInt(httpMessageBody.get("chatId"));
        return chatMsgRespository.findAllByChatId(chatId);
    }
}
