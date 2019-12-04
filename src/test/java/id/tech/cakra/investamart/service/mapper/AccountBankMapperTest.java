package id.tech.cakra.investamart.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class AccountBankMapperTest {

    private AccountBankMapper accountBankMapper;

    @BeforeEach
    public void setUp() {
        accountBankMapper = new AccountBankMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(accountBankMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(accountBankMapper.fromId(null)).isNull();
    }
}
