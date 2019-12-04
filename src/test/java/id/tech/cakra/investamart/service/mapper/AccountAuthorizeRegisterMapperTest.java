package id.tech.cakra.investamart.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class AccountAuthorizeRegisterMapperTest {

    private AccountAuthorizeRegisterMapper accountAuthorizeRegisterMapper;

    @BeforeEach
    public void setUp() {
        accountAuthorizeRegisterMapper = new AccountAuthorizeRegisterMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(accountAuthorizeRegisterMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(accountAuthorizeRegisterMapper.fromId(null)).isNull();
    }
}
