package id.tech.cakra.investamart.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class InvestorAddressRegisterMapperTest {

    private InvestorAddressRegisterMapper investorAddressRegisterMapper;

    @BeforeEach
    public void setUp() {
        investorAddressRegisterMapper = new InvestorAddressRegisterMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(investorAddressRegisterMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(investorAddressRegisterMapper.fromId(null)).isNull();
    }
}
