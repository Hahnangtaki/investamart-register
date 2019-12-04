package id.tech.cakra.investamart.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import id.tech.cakra.investamart.web.rest.TestUtil;

public class AccountAuthorizeRegisterTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AccountAuthorizeRegister.class);
        AccountAuthorizeRegister accountAuthorizeRegister1 = new AccountAuthorizeRegister();
        accountAuthorizeRegister1.setId(1L);
        AccountAuthorizeRegister accountAuthorizeRegister2 = new AccountAuthorizeRegister();
        accountAuthorizeRegister2.setId(accountAuthorizeRegister1.getId());
        assertThat(accountAuthorizeRegister1).isEqualTo(accountAuthorizeRegister2);
        accountAuthorizeRegister2.setId(2L);
        assertThat(accountAuthorizeRegister1).isNotEqualTo(accountAuthorizeRegister2);
        accountAuthorizeRegister1.setId(null);
        assertThat(accountAuthorizeRegister1).isNotEqualTo(accountAuthorizeRegister2);
    }
}
