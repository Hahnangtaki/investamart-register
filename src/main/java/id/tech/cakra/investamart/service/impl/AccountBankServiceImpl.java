package id.tech.cakra.investamart.service.impl;

import id.tech.cakra.investamart.service.AccountBankService;
import id.tech.cakra.investamart.domain.AccountBank;
import id.tech.cakra.investamart.repository.AccountBankRepository;
import id.tech.cakra.investamart.service.dto.AccountBankDTO;
import id.tech.cakra.investamart.service.mapper.AccountBankMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link AccountBank}.
 */
@Service
@Transactional
public class AccountBankServiceImpl implements AccountBankService {

    private final Logger log = LoggerFactory.getLogger(AccountBankServiceImpl.class);

    private final AccountBankRepository accountBankRepository;

    private final AccountBankMapper accountBankMapper;

    public AccountBankServiceImpl(AccountBankRepository accountBankRepository, AccountBankMapper accountBankMapper) {
        this.accountBankRepository = accountBankRepository;
        this.accountBankMapper = accountBankMapper;
    }

    /**
     * Save a accountBank.
     *
     * @param accountBankDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AccountBankDTO save(AccountBankDTO accountBankDTO) {
        log.debug("Request to save AccountBank : {}", accountBankDTO);
        AccountBank accountBank = accountBankMapper.toEntity(accountBankDTO);
        accountBank = accountBankRepository.save(accountBank);
        return accountBankMapper.toDto(accountBank);
    }

    /**
     * Get all the accountBanks.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<AccountBankDTO> findAll() {
        log.debug("Request to get all AccountBanks");
        return accountBankRepository.findAll().stream()
            .map(accountBankMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one accountBank by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AccountBankDTO> findOne(Long id) {
        log.debug("Request to get AccountBank : {}", id);
        return accountBankRepository.findById(id)
            .map(accountBankMapper::toDto);
    }

    /**
     * Delete the accountBank by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AccountBank : {}", id);
        accountBankRepository.deleteById(id);
    }
}
