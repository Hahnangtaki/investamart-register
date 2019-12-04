package id.tech.cakra.investamart.service.mapper;

import id.tech.cakra.investamart.domain.*;
import id.tech.cakra.investamart.service.dto.InvestorAddressRegisterDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link InvestorAddressRegister} and its DTO {@link InvestorAddressRegisterDTO}.
 */
@Mapper(componentModel = "spring", uses = {AccountRegisterMapper.class})
public interface InvestorAddressRegisterMapper extends EntityMapper<InvestorAddressRegisterDTO, InvestorAddressRegister> {

    @Mapping(source = "accountRegister.id", target = "accountRegisterId")
    InvestorAddressRegisterDTO toDto(InvestorAddressRegister investorAddressRegister);

    @Mapping(source = "accountRegisterId", target = "accountRegister")
    InvestorAddressRegister toEntity(InvestorAddressRegisterDTO investorAddressRegisterDTO);

    default InvestorAddressRegister fromId(Long id) {
        if (id == null) {
            return null;
        }
        InvestorAddressRegister investorAddressRegister = new InvestorAddressRegister();
        investorAddressRegister.setId(id);
        return investorAddressRegister;
    }
}
