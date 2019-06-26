package bs.demo.controller;

import bs.demo.modelandrespository.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @RequestMapping(value = "/myBuy",method = RequestMethod.POST)
    public List<OrderResponseBody> myBuy(@RequestBody Map<String,String> httpMessageBody){
        String username = httpMessageBody.get("username");
        List<OrderResponseBody> orderResponseBodies = orderRespository.findAllByBuyer(username)
                .stream()
                .map(r->new OrderResponseBody(r))
                .collect(Collectors.toList());
        return orderResponseBodies;
    }

    @RequestMapping(value = "/mySell",method = RequestMethod.POST)
    public List<OrderResponseBody> mySell(@RequestBody Map<String,String> httpMessageBody){
        String username = httpMessageBody.get("username");
        List<OrderResponseBody> orderResponseBodies = orderRespository.findAllBySeller(username)
                .stream()
                .map(r->new OrderResponseBody(r))
                .collect(Collectors.toList());
        return orderResponseBodies;
    }

    @RequestMapping(value = "modifyScom", method = RequestMethod.POST)
    public MsgResponsebody modifyScom(@RequestBody Map<String,String> httpMessageBody){
        int orderID = Integer.parseInt(httpMessageBody.get("orderId"));
        Order order = orderRespository.findByOrderid(orderID);
        order.setScom(1);
        if(order.getBcom()==1){
            order.setState(1);
        }
        try{
            orderRespository.save(order);
            return new MsgResponsebody(1,"出货成功");
        }
        catch (Exception e){
            return new MsgResponsebody(-1,"未知错误");
        }
    }

    @RequestMapping(value = "modifyBcom", method = RequestMethod.POST)
    public MsgResponsebody modifyBcom(@RequestBody Map<String,String> httpMessageBody){
        int orderID = Integer.parseInt(httpMessageBody.get("orderId"));
        Order order = orderRespository.findByOrderid(orderID);
        order.setBcom(1);
        if(order.getScom()==1){
            order.setState(1);
        }
        try{
            orderRespository.save(order);
            return new MsgResponsebody(1,"确认收货");
        }
        catch (Exception e){
            return new MsgResponsebody(-1,"未知错误");
        }
    }
}
