package bs.demo.modelandrespository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRespository extends JpaRepository<Book, Integer> {
    public List<Book> findAllByBookName(String bookName);
    public List<Book> findAllByBookId(int book_id);
    public List<Book> findAllByCategory(String category);
    public Book findByBookId(int bookId);
}
