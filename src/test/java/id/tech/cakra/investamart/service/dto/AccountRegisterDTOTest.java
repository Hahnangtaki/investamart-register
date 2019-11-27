package id.tech.cakra.investamart.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import id.tech.cakra.investamart.web.rest.TestUtil;

public class AccountRegisterDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AccountRegisterDTO.class);
        AccountRegisterDTO accountRegisterDTO1 = new AccountRegisterDTO();
        accountRegisterDTO1.setId(1L);
        AccountRegisterDTO accountRegisterDTO2 = new AccountRegisterDTO();
        assertThat(accountRegisterDTO1).isNotEqualTo(accountRegisterDTO2);
        accountRegisterDTO2.setId(accountRegisterDTO1.getId());
        assertThat(accountRegisterDTO1).isEqualTo(accountRegisterDTO2);
        accountRegisterDTO2.setId(2L);
        assertThat(accountRegisterDTO1).isNotEqualTo(accountRegisterDTO2);
        accountRegisterDTO1.setId(null);
        assertThat(accountRegisterDTO1).isNotEqualTo(accountRegisterDTO2);
    }
}
