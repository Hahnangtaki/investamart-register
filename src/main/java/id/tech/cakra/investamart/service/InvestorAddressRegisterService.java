package id.tech.cakra.investamart.service;

import id.tech.cakra.investamart.service.dto.InvestorAddressRegisterDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link id.tech.cakra.investamart.domain.InvestorAddressRegister}.
 */
public interface InvestorAddressRegisterService {

    /**
     * Save a investorAddressRegister.
     *
     * @param investorAddressRegisterDTO the entity to save.
     * @return the persisted entity.
     */
    InvestorAddressRegisterDTO save(InvestorAddressRegisterDTO investorAddressRegisterDTO);

    /**
     * Get all the investorAddressRegisters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<InvestorAddressRegisterDTO> findAll(Pageable pageable);


    /**
     * Get the "id" investorAddressRegister.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<InvestorAddressRegisterDTO> findOne(Long id);

    /**
     * Delete the "id" investorAddressRegister.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
