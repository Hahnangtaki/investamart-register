package id.tech.cakra.investamart.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import id.tech.cakra.investamart.web.rest.TestUtil;

public class AccountRegisterTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AccountRegister.class);
        AccountRegister accountRegister1 = new AccountRegister();
        accountRegister1.setId(1L);
        AccountRegister accountRegister2 = new AccountRegister();
        accountRegister2.setId(accountRegister1.getId());
        assertThat(accountRegister1).isEqualTo(accountRegister2);
        accountRegister2.setId(2L);
        assertThat(accountRegister1).isNotEqualTo(accountRegister2);
        accountRegister1.setId(null);
        assertThat(accountRegister1).isNotEqualTo(accountRegister2);
    }
}
