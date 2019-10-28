package id.tech.cakra.investamart.service;

import id.tech.cakra.investamart.service.dto.AccountIndividuRegisterDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link id.tech.cakra.investamart.domain.AccountIndividuRegister}.
 */
public interface AccountIndividuRegisterService {

    /**
     * Save a accountIndividuRegister.
     *
     * @param accountIndividuRegisterDTO the entity to save.
     * @return the persisted entity.
     */
    AccountIndividuRegisterDTO save(AccountIndividuRegisterDTO accountIndividuRegisterDTO);

    /**
     * Get all the accountIndividuRegisters.
     *
     * @return the list of entities.
     */
    List<AccountIndividuRegisterDTO> findAll();
    /**
     * Get all the AccountIndividuRegisterDTO where AccountRegister is {@code null}.
     *
     * @return the list of entities.
     */
    List<AccountIndividuRegisterDTO> findAllWhereAccountRegisterIsNull();


    /**
     * Get the "id" accountIndividuRegister.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AccountIndividuRegisterDTO> findOne(Long id);

    /**
     * Delete the "id" accountIndividuRegister.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
