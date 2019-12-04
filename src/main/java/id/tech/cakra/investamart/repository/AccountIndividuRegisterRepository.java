package id.tech.cakra.investamart.repository;
import id.tech.cakra.investamart.domain.AccountIndividuRegister;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AccountIndividuRegister entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AccountIndividuRegisterRepository extends JpaRepository<AccountIndividuRegister, Long> {

}
