package id.tech.cakra.investamart.service.mapper;

import id.tech.cakra.investamart.domain.*;
import id.tech.cakra.investamart.service.dto.AccountBankDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AccountBank} and its DTO {@link AccountBankDTO}.
 */
@Mapper(componentModel = "spring", uses = {AccountRegisterMapper.class})
public interface AccountBankMapper extends EntityMapper<AccountBankDTO, AccountBank> {

    @Mapping(source = "accountRegister.id", target = "accountRegisterId")
    AccountBankDTO toDto(AccountBank accountBank);

    @Mapping(source = "accountRegisterId", target = "accountRegister")
    AccountBank toEntity(AccountBankDTO accountBankDTO);

    default AccountBank fromId(Long id) {
        if (id == null) {
            return null;
        }
        AccountBank accountBank = new AccountBank();
        accountBank.setId(id);
        return accountBank;
    }
}
