package id.tech.cakra.investamart.service.impl;

import id.tech.cakra.investamart.service.ImageRegisterService;
import id.tech.cakra.investamart.domain.ImageRegister;
import id.tech.cakra.investamart.repository.ImageRegisterRepository;
import id.tech.cakra.investamart.service.dto.ImageRegisterDTO;
import id.tech.cakra.investamart.service.mapper.ImageRegisterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ImageRegister}.
 */
@Service
@Transactional
public class ImageRegisterServiceImpl implements ImageRegisterService {

    private final Logger log = LoggerFactory.getLogger(ImageRegisterServiceImpl.class);

    private final ImageRegisterRepository imageRegisterRepository;

    private final ImageRegisterMapper imageRegisterMapper;

    public ImageRegisterServiceImpl(ImageRegisterRepository imageRegisterRepository, ImageRegisterMapper imageRegisterMapper) {
        this.imageRegisterRepository = imageRegisterRepository;
        this.imageRegisterMapper = imageRegisterMapper;
    }

    /**
     * Save a imageRegister.
     *
     * @param imageRegisterDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ImageRegisterDTO save(ImageRegisterDTO imageRegisterDTO) {
        log.debug("Request to save ImageRegister : {}", imageRegisterDTO);
        ImageRegister imageRegister = imageRegisterMapper.toEntity(imageRegisterDTO);
        imageRegister = imageRegisterRepository.save(imageRegister);
        return imageRegisterMapper.toDto(imageRegister);
    }

    /**
     * Get all the imageRegisters.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ImageRegisterDTO> findAll() {
        log.debug("Request to get all ImageRegisters");
        return imageRegisterRepository.findAll().stream()
            .map(imageRegisterMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one imageRegister by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ImageRegisterDTO> findOne(Long id) {
        log.debug("Request to get ImageRegister : {}", id);
        return imageRegisterRepository.findById(id)
            .map(imageRegisterMapper::toDto);
    }

    /**
     * Delete the imageRegister by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ImageRegister : {}", id);
        imageRegisterRepository.deleteById(id);
    }
}
