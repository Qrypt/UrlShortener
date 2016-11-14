package qrypt.work;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UrlRepository extends JpaRepository<MyUrl, Long>{

}
