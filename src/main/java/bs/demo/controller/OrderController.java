package bs.demo.controller;

import bs.demo.modelandrespository.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*",allowCredentials = "true")
@RestController
@RequestMapping(value = "/api")
public class OrderController {
    @Autowired
    private OrderRespository orderRespository;

    @Autowired
    private BookRespository bookRespository;

    @RequestMapping(value = "/orderCreate",method = RequestMethod.POST)
    public MsgResponsebody orderCreate(@RequestBody Map<String,String> httpMessageBody){
        int bookid = Integer.parseInt(httpMessageBody.get("bookid"));
        String bookname = httpMessageBody.get("bookname");
        Timestamp ordertime = new Timestamp(new Date().getTime());
        String buyer = httpMessageBody.get("buyer");
        String seller = httpMessageBody.get("seller");
        int ordertype = Integer.parseInt(httpMessageBody.get("ordertype"));
        String address = httpMessageBody.get("address");
        int state = 0;
        int bcom = 0;
        int scom = 0;
        Order order = new Order();
        order.setBookid(bookid);
        order.setBookname(bookname);
        order.setOrdertime(ordertime);
        order.setBuyer(buyer);
        order.setSeller(seller);
        order.setOrdertype(ordertype);
        order.setAddress(address);
        order.setState(state);
        order.setBcom(bcom);
        order.setScom(scom);
        try{
            orderRespository.save(order);
            Book book = bookRespository.findByBookId(bookid);
            book.setState(1);
            bookRespository.save(book);
            return new MsgResponsebody(1,"购买成功");
        }
        catch (Exception e){
            return new MsgResponsebody(-1,"生成订单失败");
        }
    }

    @RequestMapping(value = "/mybuy",method = RequestMethod.POST)
    public List<Order> myBuy(@RequestBody Map<String,String> httpMessageBody){
        return null;
    }
}
