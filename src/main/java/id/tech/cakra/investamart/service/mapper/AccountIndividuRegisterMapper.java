package id.tech.cakra.investamart.service.mapper;

import id.tech.cakra.investamart.domain.*;
import id.tech.cakra.investamart.service.dto.AccountIndividuRegisterDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AccountIndividuRegister} and its DTO {@link AccountIndividuRegisterDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AccountIndividuRegisterMapper extends EntityMapper<AccountIndividuRegisterDTO, AccountIndividuRegister> {


    @Mapping(target = "accountRegister", ignore = true)
    AccountIndividuRegister toEntity(AccountIndividuRegisterDTO accountIndividuRegisterDTO);

    default AccountIndividuRegister fromId(Long id) {
        if (id == null) {
            return null;
        }
        AccountIndividuRegister accountIndividuRegister = new AccountIndividuRegister();
        accountIndividuRegister.setId(id);
        return accountIndividuRegister;
    }
}
