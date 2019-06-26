package bs.demo.modelandrespository;

import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;

@ResponseBody
public class OrderResponseBody {
    private int key;
    private int orderid;
    private String bookname;
    private Timestamp ordertime;
    private String buyer;
    private String seller;
    private int state;
    private int bcom;
    private int scom;

    public OrderResponseBody(Order order){
        this.key = order.getOrderid();
        this.orderid = order.getOrderid();
        this.bookname = order.getBookname();
        this.ordertime = order.getOrdertime();
        this.buyer = order.getBuyer();
        this.seller = order.getSeller();
        this.state = order.getState();
        this.bcom = order.getBcom();
        this.scom = order.getScom();
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public Timestamp getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Timestamp ordertime) {
        this.ordertime = ordertime;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getBcom() {
        return bcom;
    }

    public void setBcom(int bcom) {
        this.bcom = bcom;
    }

    public int getScom() {
        return scom;
    }

    public void setScom(int scom) {
        this.scom = scom;
    }
}
