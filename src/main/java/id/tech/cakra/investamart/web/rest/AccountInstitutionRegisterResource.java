package id.tech.cakra.investamart.web.rest;

import id.tech.cakra.investamart.service.AccountInstitutionRegisterService;
import id.tech.cakra.investamart.web.rest.errors.BadRequestAlertException;
import id.tech.cakra.investamart.service.dto.AccountInstitutionRegisterDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

/**
 * REST controller for managing {@link id.tech.cakra.investamart.domain.AccountInstitutionRegister}.
 */
@RestController
@RequestMapping("/api")
public class AccountInstitutionRegisterResource {

    private final Logger log = LoggerFactory.getLogger(AccountInstitutionRegisterResource.class);

    private static final String ENTITY_NAME = "investaregistersvcAccountInstitutionRegister";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AccountInstitutionRegisterService accountInstitutionRegisterService;

    public AccountInstitutionRegisterResource(AccountInstitutionRegisterService accountInstitutionRegisterService) {
        this.accountInstitutionRegisterService = accountInstitutionRegisterService;
    }

    /**
     * {@code POST  /account-institution-registers} : Create a new accountInstitutionRegister.
     *
     * @param accountInstitutionRegisterDTO the accountInstitutionRegisterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new accountInstitutionRegisterDTO, or with status {@code 400 (Bad Request)} if the accountInstitutionRegister has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/account-institution-registers")
    public ResponseEntity<AccountInstitutionRegisterDTO> createAccountInstitutionRegister(@Valid @RequestBody AccountInstitutionRegisterDTO accountInstitutionRegisterDTO) throws URISyntaxException {
        log.debug("REST request to save AccountInstitutionRegister : {}", accountInstitutionRegisterDTO);
        if (accountInstitutionRegisterDTO.getId() != null) {
            throw new BadRequestAlertException("A new accountInstitutionRegister cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AccountInstitutionRegisterDTO result = accountInstitutionRegisterService.save(accountInstitutionRegisterDTO);
        return ResponseEntity.created(new URI("/api/account-institution-registers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /account-institution-registers} : Updates an existing accountInstitutionRegister.
     *
     * @param accountInstitutionRegisterDTO the accountInstitutionRegisterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated accountInstitutionRegisterDTO,
     * or with status {@code 400 (Bad Request)} if the accountInstitutionRegisterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the accountInstitutionRegisterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/account-institution-registers")
    public ResponseEntity<AccountInstitutionRegisterDTO> updateAccountInstitutionRegister(@Valid @RequestBody AccountInstitutionRegisterDTO accountInstitutionRegisterDTO) throws URISyntaxException {
        log.debug("REST request to update AccountInstitutionRegister : {}", accountInstitutionRegisterDTO);
        if (accountInstitutionRegisterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AccountInstitutionRegisterDTO result = accountInstitutionRegisterService.save(accountInstitutionRegisterDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, accountInstitutionRegisterDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /account-institution-registers} : get all the accountInstitutionRegisters.
     *

     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of accountInstitutionRegisters in body.
     */
    @GetMapping("/account-institution-registers")
    public List<AccountInstitutionRegisterDTO> getAllAccountInstitutionRegisters(@RequestParam(required = false) String filter) {
        if ("accountregister-is-null".equals(filter)) {
            log.debug("REST request to get all AccountInstitutionRegisters where accountRegister is null");
            return accountInstitutionRegisterService.findAllWhereAccountRegisterIsNull();
        }
        log.debug("REST request to get all AccountInstitutionRegisters");
        return accountInstitutionRegisterService.findAll();
    }

    /**
     * {@code GET  /account-institution-registers/:id} : get the "id" accountInstitutionRegister.
     *
     * @param id the id of the accountInstitutionRegisterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the accountInstitutionRegisterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/account-institution-registers/{id}")
    public ResponseEntity<AccountInstitutionRegisterDTO> getAccountInstitutionRegister(@PathVariable Long id) {
        log.debug("REST request to get AccountInstitutionRegister : {}", id);
        Optional<AccountInstitutionRegisterDTO> accountInstitutionRegisterDTO = accountInstitutionRegisterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(accountInstitutionRegisterDTO);
    }

    /**
     * {@code DELETE  /account-institution-registers/:id} : delete the "id" accountInstitutionRegister.
     *
     * @param id the id of the accountInstitutionRegisterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/account-institution-registers/{id}")
    public ResponseEntity<Void> deleteAccountInstitutionRegister(@PathVariable Long id) {
        log.debug("REST request to delete AccountInstitutionRegister : {}", id);
        accountInstitutionRegisterService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
