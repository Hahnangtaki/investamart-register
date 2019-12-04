package id.tech.cakra.investamart.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class AccountRegisterMapperTest {

    private AccountRegisterMapper accountRegisterMapper;

    @BeforeEach
    public void setUp() {
        accountRegisterMapper = new AccountRegisterMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(accountRegisterMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(accountRegisterMapper.fromId(null)).isNull();
    }
}
