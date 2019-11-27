package id.tech.cakra.investamart.web.rest;

import id.tech.cakra.investamart.service.AccountAuthorizeRegisterService;
import id.tech.cakra.investamart.web.rest.errors.BadRequestAlertException;
import id.tech.cakra.investamart.service.dto.AccountAuthorizeRegisterDTO;

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
 * REST controller for managing {@link id.tech.cakra.investamart.domain.AccountAuthorizeRegister}.
 */
@RestController
@RequestMapping("/api")
public class AccountAuthorizeRegisterResource {

    private final Logger log = LoggerFactory.getLogger(AccountAuthorizeRegisterResource.class);

    private static final String ENTITY_NAME = "investaregistersvcAccountAuthorizeRegister";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AccountAuthorizeRegisterService accountAuthorizeRegisterService;

    public AccountAuthorizeRegisterResource(AccountAuthorizeRegisterService accountAuthorizeRegisterService) {
        this.accountAuthorizeRegisterService = accountAuthorizeRegisterService;
    }

    /**
     * {@code POST  /account-authorize-registers} : Create a new accountAuthorizeRegister.
     *
     * @param accountAuthorizeRegisterDTO the accountAuthorizeRegisterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new accountAuthorizeRegisterDTO, or with status {@code 400 (Bad Request)} if the accountAuthorizeRegister has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/account-authorize-registers")
    public ResponseEntity<AccountAuthorizeRegisterDTO> createAccountAuthorizeRegister(@Valid @RequestBody AccountAuthorizeRegisterDTO accountAuthorizeRegisterDTO) throws URISyntaxException {
        log.debug("REST request to save AccountAuthorizeRegister : {}", accountAuthorizeRegisterDTO);
        if (accountAuthorizeRegisterDTO.getId() != null) {
            throw new BadRequestAlertException("A new accountAuthorizeRegister cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AccountAuthorizeRegisterDTO result = accountAuthorizeRegisterService.save(accountAuthorizeRegisterDTO);
        return ResponseEntity.created(new URI("/api/account-authorize-registers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /account-authorize-registers} : Updates an existing accountAuthorizeRegister.
     *
     * @param accountAuthorizeRegisterDTO the accountAuthorizeRegisterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated accountAuthorizeRegisterDTO,
     * or with status {@code 400 (Bad Request)} if the accountAuthorizeRegisterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the accountAuthorizeRegisterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/account-authorize-registers")
    public ResponseEntity<AccountAuthorizeRegisterDTO> updateAccountAuthorizeRegister(@Valid @RequestBody AccountAuthorizeRegisterDTO accountAuthorizeRegisterDTO) throws URISyntaxException {
        log.debug("REST request to update AccountAuthorizeRegister : {}", accountAuthorizeRegisterDTO);
        if (accountAuthorizeRegisterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AccountAuthorizeRegisterDTO result = accountAuthorizeRegisterService.save(accountAuthorizeRegisterDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, accountAuthorizeRegisterDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /account-authorize-registers} : get all the accountAuthorizeRegisters.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of accountAuthorizeRegisters in body.
     */
    @GetMapping("/account-authorize-registers")
    public List<AccountAuthorizeRegisterDTO> getAllAccountAuthorizeRegisters() {
        log.debug("REST request to get all AccountAuthorizeRegisters");
        return accountAuthorizeRegisterService.findAll();
    }

    /**
     * {@code GET  /account-authorize-registers/:id} : get the "id" accountAuthorizeRegister.
     *
     * @param id the id of the accountAuthorizeRegisterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the accountAuthorizeRegisterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/account-authorize-registers/{id}")
    public ResponseEntity<AccountAuthorizeRegisterDTO> getAccountAuthorizeRegister(@PathVariable Long id) {
        log.debug("REST request to get AccountAuthorizeRegister : {}", id);
        Optional<AccountAuthorizeRegisterDTO> accountAuthorizeRegisterDTO = accountAuthorizeRegisterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(accountAuthorizeRegisterDTO);
    }

    /**
     * {@code DELETE  /account-authorize-registers/:id} : delete the "id" accountAuthorizeRegister.
     *
     * @param id the id of the accountAuthorizeRegisterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/account-authorize-registers/{id}")
    public ResponseEntity<Void> deleteAccountAuthorizeRegister(@PathVariable Long id) {
        log.debug("REST request to delete AccountAuthorizeRegister : {}", id);
        accountAuthorizeRegisterService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
