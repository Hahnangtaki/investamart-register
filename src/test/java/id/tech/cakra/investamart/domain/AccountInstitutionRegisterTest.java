package id.tech.cakra.investamart.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import id.tech.cakra.investamart.web.rest.TestUtil;

public class AccountInstitutionRegisterTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AccountInstitutionRegister.class);
        AccountInstitutionRegister accountInstitutionRegister1 = new AccountInstitutionRegister();
        accountInstitutionRegister1.setId(1L);
        AccountInstitutionRegister accountInstitutionRegister2 = new AccountInstitutionRegister();
        accountInstitutionRegister2.setId(accountInstitutionRegister1.getId());
        assertThat(accountInstitutionRegister1).isEqualTo(accountInstitutionRegister2);
        accountInstitutionRegister2.setId(2L);
        assertThat(accountInstitutionRegister1).isNotEqualTo(accountInstitutionRegister2);
        accountInstitutionRegister1.setId(null);
        assertThat(accountInstitutionRegister1).isNotEqualTo(accountInstitutionRegister2);
    }
}
