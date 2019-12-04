package id.tech.cakra.investamart.service;

import id.tech.cakra.investamart.service.dto.AccountAuthorizeRegisterDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link id.tech.cakra.investamart.domain.AccountAuthorizeRegister}.
 */
public interface AccountAuthorizeRegisterService {

    /**
     * Save a accountAuthorizeRegister.
     *
     * @param accountAuthorizeRegisterDTO the entity to save.
     * @return the persisted entity.
     */
    AccountAuthorizeRegisterDTO save(AccountAuthorizeRegisterDTO accountAuthorizeRegisterDTO);

    /**
     * Get all the accountAuthorizeRegisters.
     *
     * @return the list of entities.
     */
    List<AccountAuthorizeRegisterDTO> findAll();


    /**
     * Get the "id" accountAuthorizeRegister.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AccountAuthorizeRegisterDTO> findOne(Long id);

    /**
     * Delete the "id" accountAuthorizeRegister.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
