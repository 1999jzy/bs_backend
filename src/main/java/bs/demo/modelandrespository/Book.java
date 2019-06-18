package bs.demo.modelandrespository;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "Book")
public class Book implements Serializable {
    @Column(name = "bookid")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;
    @Column(name = "bookname")
    private String bookName;
    @Column(name = "priceori")
    private BigDecimal priceOri;
    @Column(name = "pricenow")
    private BigDecimal priceNow;
    @Column(name = "category")
    private String category;
    @Column(name = "content")
    private String content;
    @Column(name = "pic")
    private String pic;
    @Column(name = "bookurl")
    private String bookUrl;
    @Column(name = "username")
    private String userName;
    @Column(name = "state")
    private int state;
    @Column(name = "time")
    private Timestamp time;


    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public BigDecimal getPriceOri() {
        return priceOri;
    }

    public void setPriceOri(BigDecimal priceOri) {
        this.priceOri = priceOri;
    }

    public BigDecimal getPriceNow() {
        return priceNow;
    }

    public void setPriceNow(BigDecimal priceNow) {
        this.priceNow = priceNow;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getBookUrl() {
        return bookUrl;
    }

    public void setBookUrl(String bookUrl) {
        this.bookUrl = bookUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
