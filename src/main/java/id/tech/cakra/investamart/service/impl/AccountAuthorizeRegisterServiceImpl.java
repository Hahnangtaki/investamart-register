package id.tech.cakra.investamart.service.impl;

import id.tech.cakra.investamart.service.AccountAuthorizeRegisterService;
import id.tech.cakra.investamart.domain.AccountAuthorizeRegister;
import id.tech.cakra.investamart.repository.AccountAuthorizeRegisterRepository;
import id.tech.cakra.investamart.service.dto.AccountAuthorizeRegisterDTO;
import id.tech.cakra.investamart.service.mapper.AccountAuthorizeRegisterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link AccountAuthorizeRegister}.
 */
@Service
@Transactional
public class AccountAuthorizeRegisterServiceImpl implements AccountAuthorizeRegisterService {

    private final Logger log = LoggerFactory.getLogger(AccountAuthorizeRegisterServiceImpl.class);

    private final AccountAuthorizeRegisterRepository accountAuthorizeRegisterRepository;

    private final AccountAuthorizeRegisterMapper accountAuthorizeRegisterMapper;

    public AccountAuthorizeRegisterServiceImpl(AccountAuthorizeRegisterRepository accountAuthorizeRegisterRepository, AccountAuthorizeRegisterMapper accountAuthorizeRegisterMapper) {
        this.accountAuthorizeRegisterRepository = accountAuthorizeRegisterRepository;
        this.accountAuthorizeRegisterMapper = accountAuthorizeRegisterMapper;
    }

    /**
     * Save a accountAuthorizeRegister.
     *
     * @param accountAuthorizeRegisterDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AccountAuthorizeRegisterDTO save(AccountAuthorizeRegisterDTO accountAuthorizeRegisterDTO) {
        log.debug("Request to save AccountAuthorizeRegister : {}", accountAuthorizeRegisterDTO);
        AccountAuthorizeRegister accountAuthorizeRegister = accountAuthorizeRegisterMapper.toEntity(accountAuthorizeRegisterDTO);
        accountAuthorizeRegister = accountAuthorizeRegisterRepository.save(accountAuthorizeRegister);
        return accountAuthorizeRegisterMapper.toDto(accountAuthorizeRegister);
    }

    /**
     * Get all the accountAuthorizeRegisters.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<AccountAuthorizeRegisterDTO> findAll() {
        log.debug("Request to get all AccountAuthorizeRegisters");
        return accountAuthorizeRegisterRepository.findAll().stream()
            .map(accountAuthorizeRegisterMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one accountAuthorizeRegister by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AccountAuthorizeRegisterDTO> findOne(Long id) {
        log.debug("Request to get AccountAuthorizeRegister : {}", id);
        return accountAuthorizeRegisterRepository.findById(id)
            .map(accountAuthorizeRegisterMapper::toDto);
    }

    /**
     * Delete the accountAuthorizeRegister by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AccountAuthorizeRegister : {}", id);
        accountAuthorizeRegisterRepository.deleteById(id);
    }
}
