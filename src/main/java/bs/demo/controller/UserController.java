package bs.demo.controller;

import bs.demo.modelandrespository.MsgResponsebody;
import bs.demo.modelandrespository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import bs.demo.modelandrespository.User;

@CrossOrigin(origins = "*", allowCredentials = "true")
@RestController
@RequestMapping(value = "/api")
public class UserController {
    @Autowired
    private UserRespository userRespository;

    @RequestMapping(value="/register",method = RequestMethod.POST)
    public MsgResponsebody Register(@RequestBody Map<String ,String> httpMessageBody){
        String Username = httpMessageBody.get("username");
        String Password = httpMessageBody.get("password");
        String Email = httpMessageBody.get("email");
        User user = new User();
        user.setEmail(Email);
        user.setPassword(Password);
        user.setUsername(Username);
        try{
            userRespository.save(user);
            return new MsgResponsebody(1,"注册成功");
        }
        catch (Exception e){
            //e.printStackTrace();
            return new MsgResponsebody(-1,"注册失败");
        }
    }

    @RequestMapping(value="/login",method = RequestMethod.POST)
    public MsgResponsebody Login(@RequestBody Map<String ,String> httpMessageBody){
        String Username = httpMessageBody.get("username");
        String Password = httpMessageBody.get("password");
        User user = userRespository.findByUsername(Username);
        if (user == null){
            return new MsgResponsebody(-1,"用户不存在");
        }
        else{
            if(!user.getPassword().equals(Password)){
                return new MsgResponsebody(-1,"密码错误");
            }
            else{
                return new MsgResponsebody(1,"登录成功");
            }
        }
    }

    @RequestMapping(value = "/init",method = RequestMethod.POST)
    public User Init(@RequestBody Map<String ,String> httpMessageBody){
        String username = httpMessageBody.get("username");
        return userRespository.findByUsername(username);
    }

    @RequestMapping(value = "/changeAva", method = RequestMethod.POST)
    public MsgResponsebody changeAva(@RequestBody Map<String ,String> httpMessageBody){
        String username = httpMessageBody.get("username");
        String pic = httpMessageBody.get("pic");
        try {
            User user = userRespository.findByUsername(username);
            user.setFace(pic);
            userRespository.save(user);
            return new MsgResponsebody(1,"头像更换成功");
        }
        catch (Exception e){
            return new MsgResponsebody(-1,"未知错误");
        }
    }
}
