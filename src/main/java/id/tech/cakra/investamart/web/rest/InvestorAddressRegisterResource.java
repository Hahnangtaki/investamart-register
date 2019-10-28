package id.tech.cakra.investamart.web.rest;

import id.tech.cakra.investamart.service.InvestorAddressRegisterService;
import id.tech.cakra.investamart.web.rest.errors.BadRequestAlertException;
import id.tech.cakra.investamart.service.dto.InvestorAddressRegisterDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link id.tech.cakra.investamart.domain.InvestorAddressRegister}.
 */
@RestController
@RequestMapping("/api")
public class InvestorAddressRegisterResource {

    private final Logger log = LoggerFactory.getLogger(InvestorAddressRegisterResource.class);

    private static final String ENTITY_NAME = "investaregistersvcInvestorAddressRegister";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InvestorAddressRegisterService investorAddressRegisterService;

    public InvestorAddressRegisterResource(InvestorAddressRegisterService investorAddressRegisterService) {
        this.investorAddressRegisterService = investorAddressRegisterService;
    }

    /**
     * {@code POST  /investor-address-registers} : Create a new investorAddressRegister.
     *
     * @param investorAddressRegisterDTO the investorAddressRegisterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new investorAddressRegisterDTO, or with status {@code 400 (Bad Request)} if the investorAddressRegister has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/investor-address-registers")
    public ResponseEntity<InvestorAddressRegisterDTO> createInvestorAddressRegister(@Valid @RequestBody InvestorAddressRegisterDTO investorAddressRegisterDTO) throws URISyntaxException {
        log.debug("REST request to save InvestorAddressRegister : {}", investorAddressRegisterDTO);
        if (investorAddressRegisterDTO.getId() != null) {
            throw new BadRequestAlertException("A new investorAddressRegister cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InvestorAddressRegisterDTO result = investorAddressRegisterService.save(investorAddressRegisterDTO);
        return ResponseEntity.created(new URI("/api/investor-address-registers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /investor-address-registers} : Updates an existing investorAddressRegister.
     *
     * @param investorAddressRegisterDTO the investorAddressRegisterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated investorAddressRegisterDTO,
     * or with status {@code 400 (Bad Request)} if the investorAddressRegisterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the investorAddressRegisterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/investor-address-registers")
    public ResponseEntity<InvestorAddressRegisterDTO> updateInvestorAddressRegister(@Valid @RequestBody InvestorAddressRegisterDTO investorAddressRegisterDTO) throws URISyntaxException {
        log.debug("REST request to update InvestorAddressRegister : {}", investorAddressRegisterDTO);
        if (investorAddressRegisterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InvestorAddressRegisterDTO result = investorAddressRegisterService.save(investorAddressRegisterDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, investorAddressRegisterDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /investor-address-registers} : get all the investorAddressRegisters.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of investorAddressRegisters in body.
     */
    @GetMapping("/investor-address-registers")
    public ResponseEntity<List<InvestorAddressRegisterDTO>> getAllInvestorAddressRegisters(Pageable pageable) {
        log.debug("REST request to get a page of InvestorAddressRegisters");
        Page<InvestorAddressRegisterDTO> page = investorAddressRegisterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /investor-address-registers/:id} : get the "id" investorAddressRegister.
     *
     * @param id the id of the investorAddressRegisterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the investorAddressRegisterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/investor-address-registers/{id}")
    public ResponseEntity<InvestorAddressRegisterDTO> getInvestorAddressRegister(@PathVariable Long id) {
        log.debug("REST request to get InvestorAddressRegister : {}", id);
        Optional<InvestorAddressRegisterDTO> investorAddressRegisterDTO = investorAddressRegisterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(investorAddressRegisterDTO);
    }

    /**
     * {@code DELETE  /investor-address-registers/:id} : delete the "id" investorAddressRegister.
     *
     * @param id the id of the investorAddressRegisterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/investor-address-registers/{id}")
    public ResponseEntity<Void> deleteInvestorAddressRegister(@PathVariable Long id) {
        log.debug("REST request to delete InvestorAddressRegister : {}", id);
        investorAddressRegisterService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
