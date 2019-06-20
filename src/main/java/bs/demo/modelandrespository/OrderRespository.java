package bs.demo.modelandrespository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRespository extends JpaRepository<Order,Integer> {
    public List<Order> findAllByBuyer(String buyer);
}
