package id.tech.cakra.investamart.web.rest;

import id.tech.cakra.investamart.service.ImageRegisterService;
import id.tech.cakra.investamart.web.rest.errors.BadRequestAlertException;
import id.tech.cakra.investamart.service.dto.ImageRegisterDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link id.tech.cakra.investamart.domain.ImageRegister}.
 */
@RestController
@RequestMapping("/api")
public class ImageRegisterResource {

    private final Logger log = LoggerFactory.getLogger(ImageRegisterResource.class);

    private static final String ENTITY_NAME = "investaregistersvcImageRegister";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ImageRegisterService imageRegisterService;

    public ImageRegisterResource(ImageRegisterService imageRegisterService) {
        this.imageRegisterService = imageRegisterService;
    }

    /**
     * {@code POST  /image-registers} : Create a new imageRegister.
     *
     * @param imageRegisterDTO the imageRegisterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new imageRegisterDTO, or with status {@code 400 (Bad Request)} if the imageRegister has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/image-registers")
    public ResponseEntity<ImageRegisterDTO> createImageRegister(@RequestBody ImageRegisterDTO imageRegisterDTO) throws URISyntaxException {
        log.debug("REST request to save ImageRegister : {}", imageRegisterDTO);
        if (imageRegisterDTO.getId() != null) {
            throw new BadRequestAlertException("A new imageRegister cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ImageRegisterDTO result = imageRegisterService.save(imageRegisterDTO);
        return ResponseEntity.created(new URI("/api/image-registers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /image-registers} : Updates an existing imageRegister.
     *
     * @param imageRegisterDTO the imageRegisterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated imageRegisterDTO,
     * or with status {@code 400 (Bad Request)} if the imageRegisterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the imageRegisterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/image-registers")
    public ResponseEntity<ImageRegisterDTO> updateImageRegister(@RequestBody ImageRegisterDTO imageRegisterDTO) throws URISyntaxException {
        log.debug("REST request to update ImageRegister : {}", imageRegisterDTO);
        if (imageRegisterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ImageRegisterDTO result = imageRegisterService.save(imageRegisterDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, imageRegisterDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /image-registers} : get all the imageRegisters.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of imageRegisters in body.
     */
    @GetMapping("/image-registers")
    public List<ImageRegisterDTO> getAllImageRegisters() {
        log.debug("REST request to get all ImageRegisters");
        return imageRegisterService.findAll();
    }

    /**
     * {@code GET  /image-registers/:id} : get the "id" imageRegister.
     *
     * @param id the id of the imageRegisterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the imageRegisterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/image-registers/{id}")
    public ResponseEntity<ImageRegisterDTO> getImageRegister(@PathVariable Long id) {
        log.debug("REST request to get ImageRegister : {}", id);
        Optional<ImageRegisterDTO> imageRegisterDTO = imageRegisterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(imageRegisterDTO);
    }

    /**
     * {@code DELETE  /image-registers/:id} : delete the "id" imageRegister.
     *
     * @param id the id of the imageRegisterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/image-registers/{id}")
    public ResponseEntity<Void> deleteImageRegister(@PathVariable Long id) {
        log.debug("REST request to delete ImageRegister : {}", id);
        imageRegisterService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
