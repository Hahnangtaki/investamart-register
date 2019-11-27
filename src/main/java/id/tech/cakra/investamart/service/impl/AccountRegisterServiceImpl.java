package id.tech.cakra.investamart.service.impl;

import id.tech.cakra.investamart.service.AccountRegisterService;
import id.tech.cakra.investamart.domain.AccountRegister;
import id.tech.cakra.investamart.repository.AccountRegisterRepository;
import id.tech.cakra.investamart.service.dto.AccountRegisterDTO;
import id.tech.cakra.investamart.service.mapper.AccountRegisterMapper;
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
 * Service Implementation for managing {@link AccountRegister}.
 */
@Service
@Transactional
public class AccountRegisterServiceImpl implements AccountRegisterService {

    private final Logger log = LoggerFactory.getLogger(AccountRegisterServiceImpl.class);

    private final AccountRegisterRepository accountRegisterRepository;

    private final AccountRegisterMapper accountRegisterMapper;

    public AccountRegisterServiceImpl(AccountRegisterRepository accountRegisterRepository, AccountRegisterMapper accountRegisterMapper) {
        this.accountRegisterRepository = accountRegisterRepository;
        this.accountRegisterMapper = accountRegisterMapper;
    }

    /**
     * Save a accountRegister.
     *
     * @param accountRegisterDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AccountRegisterDTO save(AccountRegisterDTO accountRegisterDTO) {
        log.debug("Request to save AccountRegister : {}", accountRegisterDTO);
        AccountRegister accountRegister = accountRegisterMapper.toEntity(accountRegisterDTO);
        accountRegister = accountRegisterRepository.save(accountRegister);
        return accountRegisterMapper.toDto(accountRegister);
    }

    /**
     * Get all the accountRegisters.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<AccountRegisterDTO> findAll() {
        log.debug("Request to get all AccountRegisters");
        return accountRegisterRepository.findAll().stream()
            .map(accountRegisterMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }



    /**
    *  Get all the accountRegisters where AccountIndividuRegister is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<AccountRegisterDTO> findAllWhereAccountIndividuRegisterIsNull() {
        log.debug("Request to get all accountRegisters where AccountIndividuRegister is null");
        return StreamSupport
            .stream(accountRegisterRepository.findAll().spliterator(), false)
            .filter(accountRegister -> accountRegister.getAccountIndividuRegister() == null)
            .map(accountRegisterMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
    *  Get all the accountRegisters where AccountInstitutionRegister is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<AccountRegisterDTO> findAllWhereAccountInstitutionRegisterIsNull() {
        log.debug("Request to get all accountRegisters where AccountInstitutionRegister is null");
        return StreamSupport
            .stream(accountRegisterRepository.findAll().spliterator(), false)
            .filter(accountRegister -> accountRegister.getAccountInstitutionRegister() == null)
            .map(accountRegisterMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one accountRegister by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AccountRegisterDTO> findOne(Long id) {
        log.debug("Request to get AccountRegister : {}", id);
        return accountRegisterRepository.findById(id)
            .map(accountRegisterMapper::toDto);
    }

    /**
     * Delete the accountRegister by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AccountRegister : {}", id);
        accountRegisterRepository.deleteById(id);
    }
}
