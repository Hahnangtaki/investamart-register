package id.tech.cakra.investamart.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import id.tech.cakra.investamart.web.rest.TestUtil;

public class AccountIndividuRegisterDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AccountIndividuRegisterDTO.class);
        AccountIndividuRegisterDTO accountIndividuRegisterDTO1 = new AccountIndividuRegisterDTO();
        accountIndividuRegisterDTO1.setId(1L);
        AccountIndividuRegisterDTO accountIndividuRegisterDTO2 = new AccountIndividuRegisterDTO();
        assertThat(accountIndividuRegisterDTO1).isNotEqualTo(accountIndividuRegisterDTO2);
        accountIndividuRegisterDTO2.setId(accountIndividuRegisterDTO1.getId());
        assertThat(accountIndividuRegisterDTO1).isEqualTo(accountIndividuRegisterDTO2);
        accountIndividuRegisterDTO2.setId(2L);
        assertThat(accountIndividuRegisterDTO1).isNotEqualTo(accountIndividuRegisterDTO2);
        accountIndividuRegisterDTO1.setId(null);
        assertThat(accountIndividuRegisterDTO1).isNotEqualTo(accountIndividuRegisterDTO2);
    }
}
