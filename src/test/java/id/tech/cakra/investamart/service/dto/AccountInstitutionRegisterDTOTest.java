package id.tech.cakra.investamart.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import id.tech.cakra.investamart.web.rest.TestUtil;

public class AccountInstitutionRegisterDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AccountInstitutionRegisterDTO.class);
        AccountInstitutionRegisterDTO accountInstitutionRegisterDTO1 = new AccountInstitutionRegisterDTO();
        accountInstitutionRegisterDTO1.setId(1L);
        AccountInstitutionRegisterDTO accountInstitutionRegisterDTO2 = new AccountInstitutionRegisterDTO();
        assertThat(accountInstitutionRegisterDTO1).isNotEqualTo(accountInstitutionRegisterDTO2);
        accountInstitutionRegisterDTO2.setId(accountInstitutionRegisterDTO1.getId());
        assertThat(accountInstitutionRegisterDTO1).isEqualTo(accountInstitutionRegisterDTO2);
        accountInstitutionRegisterDTO2.setId(2L);
        assertThat(accountInstitutionRegisterDTO1).isNotEqualTo(accountInstitutionRegisterDTO2);
        accountInstitutionRegisterDTO1.setId(null);
        assertThat(accountInstitutionRegisterDTO1).isNotEqualTo(accountInstitutionRegisterDTO2);
    }
}
