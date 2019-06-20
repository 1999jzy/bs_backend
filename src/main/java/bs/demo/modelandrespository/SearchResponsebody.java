package bs.demo.modelandrespository;

import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;

@ResponseBody
public class SearchResponsebody implements Serializable {
    private String key;
    private String bookid;
    private String pic;
    private String bookname;
    private String category;
    private String state;
    public SearchResponsebody(Book book){
        this.key = String.valueOf(book.getBookId());
        this.bookid = String.valueOf(book.getBookId());
        this.pic = book.getPic();
        this.bookname = book.getBookName();
        this.category = book.getCategory();
        this.state = String.valueOf(book.getState());
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
