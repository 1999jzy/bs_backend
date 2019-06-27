package bs.demo.modelandrespository;

import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;

@ResponseBody
public class BookSeekResponseBody {
    private int key;
    private int seekId;
    private String pic;
    private String bookname;
    private String category;
    private int state;
    private Timestamp time;

    public BookSeekResponseBody(BookSeek bookSeek){
        this.key = bookSeek.getSeekId();
        this.seekId = bookSeek.getSeekId();
        this.pic = bookSeek.getPic();
        this.bookname = bookSeek.getBookName();
        this.category = bookSeek.getCategory();
        this.state = bookSeek.getState();
        this.time = bookSeek.getTime();
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getSeekId() {
        return seekId;
    }

    public void setSeekId(int seekId) {
        this.seekId = seekId;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
