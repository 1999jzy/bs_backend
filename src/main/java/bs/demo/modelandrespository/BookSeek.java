package bs.demo.modelandrespository;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "bookseek")
public class BookSeek implements Serializable {
    @Column(name = "seekid")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seekId;
    @Column(name = "bookname")
    private String bookName;
    @Column(name = "seekprice")
    private BigDecimal seekPrice;
    @Column(name = "category")
    private String category;
    @Column(name = "message")
    private String message;
    @Column(name = "username")
    private String username;
    @Column(name = "pic")
    private String pic;
    @Column(name = "state")
    private int state;
    @Column(name = "time")
    private Timestamp time;

    public int getSeekId() {
        return seekId;
    }

    public void setSeekId(int seekId) {
        this.seekId = seekId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public BigDecimal getSeekPrice() {
        return seekPrice;
    }

    public void setSeekPrice(BigDecimal seekPrice) {
        this.seekPrice = seekPrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
