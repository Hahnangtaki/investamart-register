package id.tech.cakra.investamart.repository;
import id.tech.cakra.investamart.domain.AccountAuthorizeRegister;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AccountAuthorizeRegister entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AccountAuthorizeRegisterRepository extends JpaRepository<AccountAuthorizeRegister, Long> {

}
