package id.tech.cakra.investamart.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import id.tech.cakra.investamart.web.rest.TestUtil;

public class InvestorAddressRegisterDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InvestorAddressRegisterDTO.class);
        InvestorAddressRegisterDTO investorAddressRegisterDTO1 = new InvestorAddressRegisterDTO();
        investorAddressRegisterDTO1.setId(1L);
        InvestorAddressRegisterDTO investorAddressRegisterDTO2 = new InvestorAddressRegisterDTO();
        assertThat(investorAddressRegisterDTO1).isNotEqualTo(investorAddressRegisterDTO2);
        investorAddressRegisterDTO2.setId(investorAddressRegisterDTO1.getId());
        assertThat(investorAddressRegisterDTO1).isEqualTo(investorAddressRegisterDTO2);
        investorAddressRegisterDTO2.setId(2L);
        assertThat(investorAddressRegisterDTO1).isNotEqualTo(investorAddressRegisterDTO2);
        investorAddressRegisterDTO1.setId(null);
        assertThat(investorAddressRegisterDTO1).isNotEqualTo(investorAddressRegisterDTO2);
    }
}
