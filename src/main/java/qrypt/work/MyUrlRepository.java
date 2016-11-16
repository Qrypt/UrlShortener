package qrypt.work;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyUrlRepository extends JpaRepository<MyUrl, Long>{

    MyUrl findById(Long id);
    List<MyUrl> findByAccountUsername(String username);
}
