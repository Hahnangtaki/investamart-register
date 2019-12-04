package id.tech.cakra.investamart.service.impl;

import id.tech.cakra.investamart.service.AccountIndividuRegisterService;
import id.tech.cakra.investamart.domain.AccountIndividuRegister;
import id.tech.cakra.investamart.repository.AccountIndividuRegisterRepository;
import id.tech.cakra.investamart.service.dto.AccountIndividuRegisterDTO;
import id.tech.cakra.investamart.service.mapper.AccountIndividuRegisterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link AccountIndividuRegister}.
 */
@Service
@Transactional
public class AccountIndividuRegisterServiceImpl implements AccountIndividuRegisterService {

    private final Logger log = LoggerFactory.getLogger(AccountIndividuRegisterServiceImpl.class);

    private final AccountIndividuRegisterRepository accountIndividuRegisterRepository;

    private final AccountIndividuRegisterMapper accountIndividuRegisterMapper;

    public AccountIndividuRegisterServiceImpl(AccountIndividuRegisterRepository accountIndividuRegisterRepository, AccountIndividuRegisterMapper accountIndividuRegisterMapper) {
        this.accountIndividuRegisterRepository = accountIndividuRegisterRepository;
        this.accountIndividuRegisterMapper = accountIndividuRegisterMapper;
    }

    /**
     * Save a accountIndividuRegister.
     *
     * @param accountIndividuRegisterDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AccountIndividuRegisterDTO save(AccountIndividuRegisterDTO accountIndividuRegisterDTO) {
        log.debug("Request to save AccountIndividuRegister : {}", accountIndividuRegisterDTO);
        AccountIndividuRegister accountIndividuRegister = accountIndividuRegisterMapper.toEntity(accountIndividuRegisterDTO);
        accountIndividuRegister = accountIndividuRegisterRepository.save(accountIndividuRegister);
        return accountIndividuRegisterMapper.toDto(accountIndividuRegister);
    }

    /**
     * Get all the accountIndividuRegisters.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<AccountIndividuRegisterDTO> findAll() {
        log.debug("Request to get all AccountIndividuRegisters");
        return accountIndividuRegisterRepository.findAll().stream()
            .map(accountIndividuRegisterMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one accountIndividuRegister by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AccountIndividuRegisterDTO> findOne(Long id) {
        log.debug("Request to get AccountIndividuRegister : {}", id);
        return accountIndividuRegisterRepository.findById(id)
            .map(accountIndividuRegisterMapper::toDto);
    }

    /**
     * Delete the accountIndividuRegister by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AccountIndividuRegister : {}", id);
        accountIndividuRegisterRepository.deleteById(id);
    }
}
