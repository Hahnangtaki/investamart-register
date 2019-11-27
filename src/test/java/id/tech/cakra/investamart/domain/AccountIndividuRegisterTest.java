package id.tech.cakra.investamart.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import id.tech.cakra.investamart.web.rest.TestUtil;

public class AccountIndividuRegisterTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AccountIndividuRegister.class);
        AccountIndividuRegister accountIndividuRegister1 = new AccountIndividuRegister();
        accountIndividuRegister1.setId(1L);
        AccountIndividuRegister accountIndividuRegister2 = new AccountIndividuRegister();
        accountIndividuRegister2.setId(accountIndividuRegister1.getId());
        assertThat(accountIndividuRegister1).isEqualTo(accountIndividuRegister2);
        accountIndividuRegister2.setId(2L);
        assertThat(accountIndividuRegister1).isNotEqualTo(accountIndividuRegister2);
        accountIndividuRegister1.setId(null);
        assertThat(accountIndividuRegister1).isNotEqualTo(accountIndividuRegister2);
    }
}
