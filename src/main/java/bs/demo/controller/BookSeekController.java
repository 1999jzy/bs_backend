package bs.demo.controller;

import bs.demo.modelandrespository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*",allowCredentials = "true")
@RestController
@RequestMapping(value = "/api")
public class BookSeekController {

    @Autowired
    private BookSeekRepository bookSeekRepository;

    @RequestMapping(value = "bookseek",method = RequestMethod.POST)
    public MsgResponsebody addBookSeek(@RequestBody Map<String,String> httpMessageBody){
        String bookName = httpMessageBody.get("bookName");
        BigDecimal seekPrice = new BigDecimal(httpMessageBody.get("seekPrice"));
        String category = httpMessageBody.get("category");
        String pic = httpMessageBody.get("pic");
        String userName = httpMessageBody.get("username");
        String message = httpMessageBody.get("message");
        int state = 0;
        Timestamp time = new Timestamp(new Date().getTime());

        BookSeek bookSeek= new BookSeek();
        bookSeek.setBookName(bookName);
        bookSeek.setCategory(category);
        bookSeek.setMessage(message);
        bookSeek.setPic(pic);
        bookSeek.setSeekPrice(seekPrice);
        bookSeek.setState(state);
        bookSeek.setUsername(userName);
        bookSeek.setTime(time);

        try {
            bookSeekRepository.save(bookSeek);
            return new MsgResponsebody(bookSeek.getSeekId(), "发布求购成功");
        }
        catch (Exception e){
            return new MsgResponsebody(-1,"发布时出现错误");
        }
    }

    @RequestMapping(value = "seekshow",method = RequestMethod.POST)
    public BookSeek seekShow(@RequestBody Map<String,String> httpMessageBody){
        int seekid = Integer.parseInt(httpMessageBody.get("seekid"));
        return bookSeekRepository.findBySeekId(seekid);
    }

    @RequestMapping(value = "supply",method = RequestMethod.POST)
    public MsgResponsebody supply(@RequestBody Map<String,String> httpMessageBody){
        int seekid = Integer.parseInt(httpMessageBody.get("seekid"));
        BookSeek bookSeek = bookSeekRepository.findBySeekId(seekid);
        if(bookSeek == null){
            return new MsgResponsebody(-1, "查无此书");
        }
        else{
            bookSeek.setState(1);
            bookSeekRepository.save(bookSeek);
            return new MsgResponsebody(1, "请联系求购人");
        }
    }

    @RequestMapping(value = "seekAll",method = RequestMethod.GET)
    public List<BookSeekResponseBody> seekAll(){
        List<BookSeek> bookSeeks = bookSeekRepository.findAll();
        return bookSeeks
                .stream()
                .map(BookSeekResponseBody::new)
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "mySeek",method = RequestMethod.POST)
    public List<BookSeekResponseBody> mySeek(@RequestBody Map<String,String> httpMessageBody){
        String username = httpMessageBody.get("username");
        List<BookSeek> bookSeeks = bookSeekRepository.findAllByUsername(username);
        return bookSeeks
                .stream()
                .map(BookSeekResponseBody::new)
                .collect(Collectors.toList());
    }
}
