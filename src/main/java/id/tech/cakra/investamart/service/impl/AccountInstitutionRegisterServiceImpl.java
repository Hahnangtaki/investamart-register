package id.tech.cakra.investamart.service.impl;

import id.tech.cakra.investamart.service.AccountInstitutionRegisterService;
import id.tech.cakra.investamart.domain.AccountInstitutionRegister;
import id.tech.cakra.investamart.repository.AccountInstitutionRegisterRepository;
import id.tech.cakra.investamart.service.dto.AccountInstitutionRegisterDTO;
import id.tech.cakra.investamart.service.mapper.AccountInstitutionRegisterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link AccountInstitutionRegister}.
 */
@Service
@Transactional
public class AccountInstitutionRegisterServiceImpl implements AccountInstitutionRegisterService {

    private final Logger log = LoggerFactory.getLogger(AccountInstitutionRegisterServiceImpl.class);

    private final AccountInstitutionRegisterRepository accountInstitutionRegisterRepository;

    private final AccountInstitutionRegisterMapper accountInstitutionRegisterMapper;

    public AccountInstitutionRegisterServiceImpl(AccountInstitutionRegisterRepository accountInstitutionRegisterRepository, AccountInstitutionRegisterMapper accountInstitutionRegisterMapper) {
        this.accountInstitutionRegisterRepository = accountInstitutionRegisterRepository;
        this.accountInstitutionRegisterMapper = accountInstitutionRegisterMapper;
    }

    /**
     * Save a accountInstitutionRegister.
     *
     * @param accountInstitutionRegisterDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AccountInstitutionRegisterDTO save(AccountInstitutionRegisterDTO accountInstitutionRegisterDTO) {
        log.debug("Request to save AccountInstitutionRegister : {}", accountInstitutionRegisterDTO);
        AccountInstitutionRegister accountInstitutionRegister = accountInstitutionRegisterMapper.toEntity(accountInstitutionRegisterDTO);
        accountInstitutionRegister = accountInstitutionRegisterRepository.save(accountInstitutionRegister);
        return accountInstitutionRegisterMapper.toDto(accountInstitutionRegister);
    }

    /**
     * Get all the accountInstitutionRegisters.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<AccountInstitutionRegisterDTO> findAll() {
        log.debug("Request to get all AccountInstitutionRegisters");
        return accountInstitutionRegisterRepository.findAll().stream()
            .map(accountInstitutionRegisterMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }



    /**
    *  Get all the accountInstitutionRegisters where AccountRegister is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<AccountInstitutionRegisterDTO> findAllWhereAccountRegisterIsNull() {
        log.debug("Request to get all accountInstitutionRegisters where AccountRegister is null");
        return StreamSupport
            .stream(accountInstitutionRegisterRepository.findAll().spliterator(), false)
            .filter(accountInstitutionRegister -> accountInstitutionRegister.getAccountRegister() == null)
            .map(accountInstitutionRegisterMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one accountInstitutionRegister by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AccountInstitutionRegisterDTO> findOne(Long id) {
        log.debug("Request to get AccountInstitutionRegister : {}", id);
        return accountInstitutionRegisterRepository.findById(id)
            .map(accountInstitutionRegisterMapper::toDto);
    }

    /**
     * Delete the accountInstitutionRegister by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AccountInstitutionRegister : {}", id);
        accountInstitutionRegisterRepository.deleteById(id);
    }
}
