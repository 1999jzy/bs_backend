package bs.demo.modelandrespository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "Ord")
public class Order implements Serializable {
    @Column(name = "orderid")
    @Id
    private int orderid;
    @Column(name = "bookid")
    private int bookid;
    @Column(name = "bookname")
    private String bookname;
    @Column(name = "ordertime")
    private Timestamp ordertime;
    @Column(name = "buyer")
    private String buyer;
    @Column(name = "seller")
    private String seller;
    @Column(name = "ordertype")
    private int ordertype;
    @Column(name = "address")
    private String address;
    @Column(name = "state")
    private int state;
    @Column(name = "bcom")
    private int bcom;
    @Column(name = "scom")
    private int scom;

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
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

    public int getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(int ordertype) {
        this.ordertype = ordertype;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
