package id.tech.cakra.investamart.service.impl;

import id.tech.cakra.investamart.service.InvestorAddressRegisterService;
import id.tech.cakra.investamart.domain.InvestorAddressRegister;
import id.tech.cakra.investamart.repository.InvestorAddressRegisterRepository;
import id.tech.cakra.investamart.service.dto.InvestorAddressRegisterDTO;
import id.tech.cakra.investamart.service.mapper.InvestorAddressRegisterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link InvestorAddressRegister}.
 */
@Service
@Transactional
public class InvestorAddressRegisterServiceImpl implements InvestorAddressRegisterService {

    private final Logger log = LoggerFactory.getLogger(InvestorAddressRegisterServiceImpl.class);

    private final InvestorAddressRegisterRepository investorAddressRegisterRepository;

    private final InvestorAddressRegisterMapper investorAddressRegisterMapper;

    public InvestorAddressRegisterServiceImpl(InvestorAddressRegisterRepository investorAddressRegisterRepository, InvestorAddressRegisterMapper investorAddressRegisterMapper) {
        this.investorAddressRegisterRepository = investorAddressRegisterRepository;
        this.investorAddressRegisterMapper = investorAddressRegisterMapper;
    }

    /**
     * Save a investorAddressRegister.
     *
     * @param investorAddressRegisterDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public InvestorAddressRegisterDTO save(InvestorAddressRegisterDTO investorAddressRegisterDTO) {
        log.debug("Request to save InvestorAddressRegister : {}", investorAddressRegisterDTO);
        InvestorAddressRegister investorAddressRegister = investorAddressRegisterMapper.toEntity(investorAddressRegisterDTO);
        investorAddressRegister = investorAddressRegisterRepository.save(investorAddressRegister);
        return investorAddressRegisterMapper.toDto(investorAddressRegister);
    }

    /**
     * Get all the investorAddressRegisters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<InvestorAddressRegisterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all InvestorAddressRegisters");
        return investorAddressRegisterRepository.findAll(pageable)
            .map(investorAddressRegisterMapper::toDto);
    }


    /**
     * Get one investorAddressRegister by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<InvestorAddressRegisterDTO> findOne(Long id) {
        log.debug("Request to get InvestorAddressRegister : {}", id);
        return investorAddressRegisterRepository.findById(id)
            .map(investorAddressRegisterMapper::toDto);
    }

    /**
     * Delete the investorAddressRegister by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete InvestorAddressRegister : {}", id);
        investorAddressRegisterRepository.deleteById(id);
    }
}
