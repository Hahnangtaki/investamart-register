package id.tech.cakra.investamart.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class AccountInstitutionRegisterMapperTest {

    private AccountInstitutionRegisterMapper accountInstitutionRegisterMapper;

    @BeforeEach
    public void setUp() {
        accountInstitutionRegisterMapper = new AccountInstitutionRegisterMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(accountInstitutionRegisterMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(accountInstitutionRegisterMapper.fromId(null)).isNull();
    }
}
