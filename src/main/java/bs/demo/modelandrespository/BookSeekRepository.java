package bs.demo.modelandrespository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookSeekRepository extends JpaRepository<BookSeek,Integer> {
    public BookSeek findBySeekId(int seekId);
    public List<BookSeek> findAllByUsername(String username);
}
