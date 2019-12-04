package id.tech.cakra.investamart.service.mapper;

import id.tech.cakra.investamart.domain.*;
import id.tech.cakra.investamart.service.dto.VisitorDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Visitor} and its DTO {@link VisitorDTO}.
 */
@Mapper(componentModel = "spring", uses = {VisitorRegisterAccountMapper.class})
public interface VisitorMapper extends EntityMapper<VisitorDTO, Visitor> {

    @Mapping(source = "visitorRegisterAccount.id", target = "visitorRegisterAccountId")
    VisitorDTO toDto(Visitor visitor);

    @Mapping(source = "visitorRegisterAccountId", target = "visitorRegisterAccount")
    @Mapping(target = "otpHistories", ignore = true)
    @Mapping(target = "removeOtpHistory", ignore = true)
    Visitor toEntity(VisitorDTO visitorDTO);

    default Visitor fromId(Long id) {
        if (id == null) {
            return null;
        }
        Visitor visitor = new Visitor();
        visitor.setId(id);
        return visitor;
    }
}
