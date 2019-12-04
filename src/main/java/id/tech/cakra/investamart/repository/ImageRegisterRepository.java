package id.tech.cakra.investamart.repository;
import id.tech.cakra.investamart.domain.ImageRegister;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ImageRegister entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ImageRegisterRepository extends JpaRepository<ImageRegister, Long> {

}
