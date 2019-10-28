package id.tech.cakra.investamart.repository;
import id.tech.cakra.investamart.domain.InvestorAddressRegister;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the InvestorAddressRegister entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InvestorAddressRegisterRepository extends JpaRepository<InvestorAddressRegister, Long> {

}
