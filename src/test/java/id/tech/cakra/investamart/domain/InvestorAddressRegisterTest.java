package id.tech.cakra.investamart.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import id.tech.cakra.investamart.web.rest.TestUtil;

public class InvestorAddressRegisterTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InvestorAddressRegister.class);
        InvestorAddressRegister investorAddressRegister1 = new InvestorAddressRegister();
        investorAddressRegister1.setId(1L);
        InvestorAddressRegister investorAddressRegister2 = new InvestorAddressRegister();
        investorAddressRegister2.setId(investorAddressRegister1.getId());
        assertThat(investorAddressRegister1).isEqualTo(investorAddressRegister2);
        investorAddressRegister2.setId(2L);
        assertThat(investorAddressRegister1).isNotEqualTo(investorAddressRegister2);
        investorAddressRegister1.setId(null);
        assertThat(investorAddressRegister1).isNotEqualTo(investorAddressRegister2);
    }
}
