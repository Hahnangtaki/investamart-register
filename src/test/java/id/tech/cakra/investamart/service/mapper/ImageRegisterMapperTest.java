package id.tech.cakra.investamart.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ImageRegisterMapperTest {

    private ImageRegisterMapper imageRegisterMapper;

    @BeforeEach
    public void setUp() {
        imageRegisterMapper = new ImageRegisterMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(imageRegisterMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(imageRegisterMapper.fromId(null)).isNull();
    }
}
