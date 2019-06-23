package bs.demo.controller;

import bs.demo.modelandrespository.ChatList;
import bs.demo.modelandrespository.ChatListRespository;
import bs.demo.modelandrespository.MsgResponsebody;
import bs.demo.modelandrespository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*",allowCredentials = "true")
@RestController
@RequestMapping(value = "/api")
public class ChatListController {
    @Autowired
    private ChatListRespository chatListRespository;
    @Autowired
    private UserRespository userRespository;

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
    public List<ChatList> chatList(@RequestBody Map<String,String> httpMessageBody){
        String user = httpMessageBody.get("user");
        List<ChatList> list1 = chatListRespository.findAllByUser(user);
        List<ChatList> list2 = chatListRespository.findAllByAnotherUser(user);
        list1.addAll(list2);
        return list1;
    }
}
