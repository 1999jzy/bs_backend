package bs.demo.modelandrespository;


import org.springframework.data.jpa.repository.JpaRepository;
import bs.demo.modelandrespository.User;

public interface UserRespository extends JpaRepository<User,Integer>{
    public User findByUsername(String username);
    public User findByEmail(String email);
}