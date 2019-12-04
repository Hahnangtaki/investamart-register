package id.tech.cakra.investamart.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class AccountIndividuRegisterMapperTest {

    private AccountIndividuRegisterMapper accountIndividuRegisterMapper;

    @BeforeEach
    public void setUp() {
        accountIndividuRegisterMapper = new AccountIndividuRegisterMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(accountIndividuRegisterMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(accountIndividuRegisterMapper.fromId(null)).isNull();
    }
}
