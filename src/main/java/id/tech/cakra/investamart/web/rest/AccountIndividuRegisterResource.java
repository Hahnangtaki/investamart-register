package id.tech.cakra.investamart.web.rest;

import id.tech.cakra.investamart.service.AccountIndividuRegisterService;
import id.tech.cakra.investamart.web.rest.errors.BadRequestAlertException;
import id.tech.cakra.investamart.service.dto.AccountIndividuRegisterDTO;

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
 * REST controller for managing {@link id.tech.cakra.investamart.domain.AccountIndividuRegister}.
 */
@RestController
@RequestMapping("/api")
public class AccountIndividuRegisterResource {

    private final Logger log = LoggerFactory.getLogger(AccountIndividuRegisterResource.class);

    private static final String ENTITY_NAME = "investaregistersvcAccountIndividuRegister";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AccountIndividuRegisterService accountIndividuRegisterService;

    public AccountIndividuRegisterResource(AccountIndividuRegisterService accountIndividuRegisterService) {
        this.accountIndividuRegisterService = accountIndividuRegisterService;
    }

    /**
     * {@code POST  /account-individu-registers} : Create a new accountIndividuRegister.
     *
     * @param accountIndividuRegisterDTO the accountIndividuRegisterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new accountIndividuRegisterDTO, or with status {@code 400 (Bad Request)} if the accountIndividuRegister has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/account-individu-registers")
    public ResponseEntity<AccountIndividuRegisterDTO> createAccountIndividuRegister(@Valid @RequestBody AccountIndividuRegisterDTO accountIndividuRegisterDTO) throws URISyntaxException {
        log.debug("REST request to save AccountIndividuRegister : {}", accountIndividuRegisterDTO);
        if (accountIndividuRegisterDTO.getId() != null) {
            throw new BadRequestAlertException("A new accountIndividuRegister cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AccountIndividuRegisterDTO result = accountIndividuRegisterService.save(accountIndividuRegisterDTO);
        return ResponseEntity.created(new URI("/api/account-individu-registers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /account-individu-registers} : Updates an existing accountIndividuRegister.
     *
     * @param accountIndividuRegisterDTO the accountIndividuRegisterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated accountIndividuRegisterDTO,
     * or with status {@code 400 (Bad Request)} if the accountIndividuRegisterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the accountIndividuRegisterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/account-individu-registers")
    public ResponseEntity<AccountIndividuRegisterDTO> updateAccountIndividuRegister(@Valid @RequestBody AccountIndividuRegisterDTO accountIndividuRegisterDTO) throws URISyntaxException {
        log.debug("REST request to update AccountIndividuRegister : {}", accountIndividuRegisterDTO);
        if (accountIndividuRegisterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AccountIndividuRegisterDTO result = accountIndividuRegisterService.save(accountIndividuRegisterDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, accountIndividuRegisterDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /account-individu-registers} : get all the accountIndividuRegisters.
     *

     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of accountIndividuRegisters in body.
     */
    @GetMapping("/account-individu-registers")
    public List<AccountIndividuRegisterDTO> getAllAccountIndividuRegisters(@RequestParam(required = false) String filter) {
        if ("accountregister-is-null".equals(filter)) {
            log.debug("REST request to get all AccountIndividuRegisters where accountRegister is null");
            return accountIndividuRegisterService.findAllWhereAccountRegisterIsNull();
        }
        log.debug("REST request to get all AccountIndividuRegisters");
        return accountIndividuRegisterService.findAll();
    }

    /**
     * {@code GET  /account-individu-registers/:id} : get the "id" accountIndividuRegister.
     *
     * @param id the id of the accountIndividuRegisterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the accountIndividuRegisterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/account-individu-registers/{id}")
    public ResponseEntity<AccountIndividuRegisterDTO> getAccountIndividuRegister(@PathVariable Long id) {
        log.debug("REST request to get AccountIndividuRegister : {}", id);
        Optional<AccountIndividuRegisterDTO> accountIndividuRegisterDTO = accountIndividuRegisterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(accountIndividuRegisterDTO);
    }

    /**
     * {@code DELETE  /account-individu-registers/:id} : delete the "id" accountIndividuRegister.
     *
     * @param id the id of the accountIndividuRegisterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/account-individu-registers/{id}")
    public ResponseEntity<Void> deleteAccountIndividuRegister(@PathVariable Long id) {
        log.debug("REST request to delete AccountIndividuRegister : {}", id);
        accountIndividuRegisterService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
