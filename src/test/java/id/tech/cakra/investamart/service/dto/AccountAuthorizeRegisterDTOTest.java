package id.tech.cakra.investamart.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import id.tech.cakra.investamart.web.rest.TestUtil;

public class AccountAuthorizeRegisterDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AccountAuthorizeRegisterDTO.class);
        AccountAuthorizeRegisterDTO accountAuthorizeRegisterDTO1 = new AccountAuthorizeRegisterDTO();
        accountAuthorizeRegisterDTO1.setId(1L);
        AccountAuthorizeRegisterDTO accountAuthorizeRegisterDTO2 = new AccountAuthorizeRegisterDTO();
        assertThat(accountAuthorizeRegisterDTO1).isNotEqualTo(accountAuthorizeRegisterDTO2);
        accountAuthorizeRegisterDTO2.setId(accountAuthorizeRegisterDTO1.getId());
        assertThat(accountAuthorizeRegisterDTO1).isEqualTo(accountAuthorizeRegisterDTO2);
        accountAuthorizeRegisterDTO2.setId(2L);
        assertThat(accountAuthorizeRegisterDTO1).isNotEqualTo(accountAuthorizeRegisterDTO2);
        accountAuthorizeRegisterDTO1.setId(null);
        assertThat(accountAuthorizeRegisterDTO1).isNotEqualTo(accountAuthorizeRegisterDTO2);
    }
}
