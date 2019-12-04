package id.tech.cakra.investamart.service;

import id.tech.cakra.investamart.service.dto.AccountInstitutionRegisterDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link id.tech.cakra.investamart.domain.AccountInstitutionRegister}.
 */
public interface AccountInstitutionRegisterService {

    /**
     * Save a accountInstitutionRegister.
     *
     * @param accountInstitutionRegisterDTO the entity to save.
     * @return the persisted entity.
     */
    AccountInstitutionRegisterDTO save(AccountInstitutionRegisterDTO accountInstitutionRegisterDTO);

    /**
     * Get all the accountInstitutionRegisters.
     *
     * @return the list of entities.
     */
    List<AccountInstitutionRegisterDTO> findAll();


    /**
     * Get the "id" accountInstitutionRegister.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AccountInstitutionRegisterDTO> findOne(Long id);

    /**
     * Delete the "id" accountInstitutionRegister.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
