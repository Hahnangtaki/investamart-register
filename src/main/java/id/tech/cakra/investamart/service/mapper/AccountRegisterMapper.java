package id.tech.cakra.investamart.service.mapper;

import id.tech.cakra.investamart.domain.*;
import id.tech.cakra.investamart.service.dto.AccountRegisterDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AccountRegister} and its DTO {@link AccountRegisterDTO}.
 */
@Mapper(componentModel = "spring", uses = {AccountIndividuRegisterMapper.class, AccountInstitutionRegisterMapper.class})
public interface AccountRegisterMapper extends EntityMapper<AccountRegisterDTO, AccountRegister> {

    @Mapping(source = "accountIndividuRegister.id", target = "accountIndividuRegisterId")
    @Mapping(source = "accountInstitutionRegister.id", target = "accountInstitutionRegisterId")
    AccountRegisterDTO toDto(AccountRegister accountRegister);

    @Mapping(source = "accountIndividuRegisterId", target = "accountIndividuRegister")
    @Mapping(source = "accountInstitutionRegisterId", target = "accountInstitutionRegister")
    @Mapping(target = "accountAuthorizeRegisters", ignore = true)
    @Mapping(target = "removeAccountAuthorizeRegister", ignore = true)
    @Mapping(target = "investorAddressRegisters", ignore = true)
    @Mapping(target = "removeInvestorAddressRegister", ignore = true)
    AccountRegister toEntity(AccountRegisterDTO accountRegisterDTO);

    default AccountRegister fromId(Long id) {
        if (id == null) {
            return null;
        }
        AccountRegister accountRegister = new AccountRegister();
        accountRegister.setId(id);
        return accountRegister;
    }
}
