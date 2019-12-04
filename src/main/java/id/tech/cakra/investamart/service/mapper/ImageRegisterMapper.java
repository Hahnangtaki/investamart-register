package id.tech.cakra.investamart.service.mapper;

import id.tech.cakra.investamart.domain.*;
import id.tech.cakra.investamart.service.dto.ImageRegisterDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ImageRegister} and its DTO {@link ImageRegisterDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ImageRegisterMapper extends EntityMapper<ImageRegisterDTO, ImageRegister> {



    default ImageRegister fromId(Long id) {
        if (id == null) {
            return null;
        }
        ImageRegister imageRegister = new ImageRegister();
        imageRegister.setId(id);
        return imageRegister;
    }
}
