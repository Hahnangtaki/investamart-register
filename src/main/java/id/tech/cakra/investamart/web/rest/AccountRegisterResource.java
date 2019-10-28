package id.tech.cakra.investamart.web.rest;

import id.tech.cakra.investamart.service.AccountRegisterService;
import id.tech.cakra.investamart.web.rest.errors.BadRequestAlertException;
import id.tech.cakra.investamart.service.dto.AccountRegisterDTO;

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

/**
 * REST controller for managing {@link id.tech.cakra.investamart.domain.AccountRegister}.
 */
@RestController
@RequestMapping("/api")
public class AccountRegisterResource {

    private final Logger log = LoggerFactory.getLogger(AccountRegisterResource.class);

    private static final String ENTITY_NAME = "investaregistersvcAccountRegister";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AccountRegisterService accountRegisterService;

    public AccountRegisterResource(AccountRegisterService accountRegisterService) {
        this.accountRegisterService = accountRegisterService;
    }

    /**
     * {@code POST  /account-registers} : Create a new accountRegister.
     *
     * @param accountRegisterDTO the accountRegisterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new accountRegisterDTO, or with status {@code 400 (Bad Request)} if the accountRegister has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/account-registers")
    public ResponseEntity<AccountRegisterDTO> createAccountRegister(@Valid @RequestBody AccountRegisterDTO accountRegisterDTO) throws URISyntaxException {
        log.debug("REST request to save AccountRegister : {}", accountRegisterDTO);
        if (accountRegisterDTO.getId() != null) {
            throw new BadRequestAlertException("A new accountRegister cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AccountRegisterDTO result = accountRegisterService.save(accountRegisterDTO);
        return ResponseEntity.created(new URI("/api/account-registers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /account-registers} : Updates an existing accountRegister.
     *
     * @param accountRegisterDTO the accountRegisterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated accountRegisterDTO,
     * or with status {@code 400 (Bad Request)} if the accountRegisterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the accountRegisterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/account-registers")
    public ResponseEntity<AccountRegisterDTO> updateAccountRegister(@Valid @RequestBody AccountRegisterDTO accountRegisterDTO) throws URISyntaxException {
        log.debug("REST request to update AccountRegister : {}", accountRegisterDTO);
        if (accountRegisterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AccountRegisterDTO result = accountRegisterService.save(accountRegisterDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, accountRegisterDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /account-registers} : get all the accountRegisters.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of accountRegisters in body.
     */
    @GetMapping("/account-registers")
    public List<AccountRegisterDTO> getAllAccountRegisters() {
        log.debug("REST request to get all AccountRegisters");
        return accountRegisterService.findAll();
    }

    /**
     * {@code GET  /account-registers/:id} : get the "id" accountRegister.
     *
     * @param id the id of the accountRegisterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the accountRegisterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/account-registers/{id}")
    public ResponseEntity<AccountRegisterDTO> getAccountRegister(@PathVariable Long id) {
        log.debug("REST request to get AccountRegister : {}", id);
        Optional<AccountRegisterDTO> accountRegisterDTO = accountRegisterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(accountRegisterDTO);
    }

    /**
     * {@code DELETE  /account-registers/:id} : delete the "id" accountRegister.
     *
     * @param id the id of the accountRegisterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/account-registers/{id}")
    public ResponseEntity<Void> deleteAccountRegister(@PathVariable Long id) {
        log.debug("REST request to delete AccountRegister : {}", id);
        accountRegisterService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
