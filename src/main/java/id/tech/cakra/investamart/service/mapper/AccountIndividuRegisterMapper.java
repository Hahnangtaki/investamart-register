package id.tech.cakra.investamart.service.mapper;

import id.tech.cakra.investamart.domain.*;
import id.tech.cakra.investamart.service.dto.AccountIndividuRegisterDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AccountIndividuRegister} and its DTO {@link AccountIndividuRegisterDTO}.
 */
@Mapper(componentModel = "spring", uses = {AccountRegisterMapper.class})
public interface AccountIndividuRegisterMapper extends EntityMapper<AccountIndividuRegisterDTO, AccountIndividuRegister> {

    @Mapping(source = "accountRegister.id", target = "accountRegisterId")
    AccountIndividuRegisterDTO toDto(AccountIndividuRegister accountIndividuRegister);

    @Mapping(source = "accountRegisterId", target = "accountRegister")
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
