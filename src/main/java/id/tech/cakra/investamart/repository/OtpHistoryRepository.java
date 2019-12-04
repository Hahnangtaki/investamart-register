package id.tech.cakra.investamart.repository;
import id.tech.cakra.investamart.domain.OtpHistory;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the OtpHistory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OtpHistoryRepository extends JpaRepository<OtpHistory, Long> {

}
