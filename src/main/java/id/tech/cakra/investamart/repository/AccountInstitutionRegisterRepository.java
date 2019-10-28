package id.tech.cakra.investamart.repository;
import id.tech.cakra.investamart.domain.AccountInstitutionRegister;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AccountInstitutionRegister entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AccountInstitutionRegisterRepository extends JpaRepository<AccountInstitutionRegister, Long> {

}
