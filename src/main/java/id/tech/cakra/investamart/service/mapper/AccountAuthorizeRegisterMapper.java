package id.tech.cakra.investamart.service.mapper;

import id.tech.cakra.investamart.domain.*;
import id.tech.cakra.investamart.service.dto.AccountAuthorizeRegisterDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AccountAuthorizeRegister} and its DTO {@link AccountAuthorizeRegisterDTO}.
 */
@Mapper(componentModel = "spring", uses = {AccountRegisterMapper.class})
public interface AccountAuthorizeRegisterMapper extends EntityMapper<AccountAuthorizeRegisterDTO, AccountAuthorizeRegister> {

    @Mapping(source = "accountRegister.id", target = "accountRegisterId")
    AccountAuthorizeRegisterDTO toDto(AccountAuthorizeRegister accountAuthorizeRegister);

    @Mapping(source = "accountRegisterId", target = "accountRegister")
    AccountAuthorizeRegister toEntity(AccountAuthorizeRegisterDTO accountAuthorizeRegisterDTO);

    default AccountAuthorizeRegister fromId(Long id) {
        if (id == null) {
            return null;
        }
        AccountAuthorizeRegister accountAuthorizeRegister = new AccountAuthorizeRegister();
        accountAuthorizeRegister.setId(id);
        return accountAuthorizeRegister;
    }
}
