package id.tech.cakra.investamart.service;

import id.tech.cakra.investamart.service.dto.ImageRegisterDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link id.tech.cakra.investamart.domain.ImageRegister}.
 */
public interface ImageRegisterService {

    /**
     * Save a imageRegister.
     *
     * @param imageRegisterDTO the entity to save.
     * @return the persisted entity.
     */
    ImageRegisterDTO save(ImageRegisterDTO imageRegisterDTO);

    /**
     * Get all the imageRegisters.
     *
     * @return the list of entities.
     */
    List<ImageRegisterDTO> findAll();


    /**
     * Get the "id" imageRegister.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ImageRegisterDTO> findOne(Long id);

    /**
     * Delete the "id" imageRegister.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
