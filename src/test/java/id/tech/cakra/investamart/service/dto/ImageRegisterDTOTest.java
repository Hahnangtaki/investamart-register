package id.tech.cakra.investamart.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import id.tech.cakra.investamart.web.rest.TestUtil;

public class ImageRegisterDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ImageRegisterDTO.class);
        ImageRegisterDTO imageRegisterDTO1 = new ImageRegisterDTO();
        imageRegisterDTO1.setId(1L);
        ImageRegisterDTO imageRegisterDTO2 = new ImageRegisterDTO();
        assertThat(imageRegisterDTO1).isNotEqualTo(imageRegisterDTO2);
        imageRegisterDTO2.setId(imageRegisterDTO1.getId());
        assertThat(imageRegisterDTO1).isEqualTo(imageRegisterDTO2);
        imageRegisterDTO2.setId(2L);
        assertThat(imageRegisterDTO1).isNotEqualTo(imageRegisterDTO2);
        imageRegisterDTO1.setId(null);
        assertThat(imageRegisterDTO1).isNotEqualTo(imageRegisterDTO2);
    }
}
