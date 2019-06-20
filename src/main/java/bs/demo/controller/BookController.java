package bs.demo.controller;

import bs.demo.modelandrespository.Book;
import bs.demo.modelandrespository.BookRespository;
import bs.demo.modelandrespository.MsgResponsebody;
import bs.demo.modelandrespository.SearchResponsebody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*",allowCredentials = "true")
@RestController
@RequestMapping(value = "/api")
public class BookController {
    @Autowired
    private BookRespository bookRespository;

    @RequestMapping(value = "/bookadd",method = RequestMethod.POST)
    public MsgResponsebody AddBook(@RequestBody Map<String,String> httpMessageBody){
        String bookName = httpMessageBody.get("bookName");
        BigDecimal priceOri = new BigDecimal(httpMessageBody.get("priceOri"));
        BigDecimal priceNow = new BigDecimal(httpMessageBody.get("priceNow"));
        String category = httpMessageBody.get("category");
        String content = httpMessageBody.get("content");
        String pic = httpMessageBody.get("pic");
        String bookUrl = httpMessageBody.get("ISBN");
        String userName = httpMessageBody.get("username");
        int state = 0;
        Timestamp time = new Timestamp(new Date().getTime());
        Book book = new Book();
        book.setBookName(bookName);
        book.setPriceOri(priceOri);
        book.setPriceNow(priceNow);
        book.setCategory(category);
        book.setContent(content);
        book.setPic(pic);
        book.setBookUrl(bookUrl);
        book.setUserName(userName);
        book.setState(state);
        book.setTime(time);
        try {
            bookRespository.save(book);
            return new MsgResponsebody(book.getBookId(), "发布图书成功");
        }
        catch (Exception e){
            return new MsgResponsebody(-1,"发布时出现错误");
        }
    }

    @RequestMapping(value = "/bookshow",method = RequestMethod.POST)
    public Book bookShow(@RequestBody Map<String,String> httpMessageBody){
        int bookId = Integer.parseInt(httpMessageBody.get("bookId"));
        System.out.println(bookId);
        return bookRespository.findByBookId(bookId);
    }

    @RequestMapping(value = "/searchbook",method = RequestMethod.POST)
    public List<SearchResponsebody> searchBook(@RequestBody Map<String,String> httpMessageBody) {
        String choice = httpMessageBody.get("choice");
        String content = httpMessageBody.get("content");
        System.out.println(choice);
        System.out.println(content);
        switch (choice) {
            case "1": {
                List<Book> books = bookRespository.findAllByBookName(content);
                List<SearchResponsebody> searchResponsebodies = books
                        .stream()
                        .map(r -> new SearchResponsebody(r))
                        .collect(Collectors.toList());
                return searchResponsebodies;
            }
            case "2": {
                List<Book> books = bookRespository.findAllByBookId(Integer.parseInt(content));
                List<SearchResponsebody> searchResponsebodies = books
                        .stream()
                        .map(r -> new SearchResponsebody(r))
                        .collect(Collectors.toList());
                return searchResponsebodies;
            }
            case "3": {
                List<Book> books = bookRespository.findAllByCategory(content);
                List<SearchResponsebody> searchResponsebodies = books
                        .stream()
                        .map(r -> new SearchResponsebody(r))
                        .collect(Collectors.toList());
                return searchResponsebodies;
            }
            default:
                return null;
        }
    }

    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public List<Book> homeBooks(){
        return bookRespository.findAll();
    }
}
