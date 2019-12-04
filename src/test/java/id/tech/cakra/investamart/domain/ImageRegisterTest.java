package id.tech.cakra.investamart.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import id.tech.cakra.investamart.web.rest.TestUtil;

public class ImageRegisterTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ImageRegister.class);
        ImageRegister imageRegister1 = new ImageRegister();
        imageRegister1.setId(1L);
        ImageRegister imageRegister2 = new ImageRegister();
        imageRegister2.setId(imageRegister1.getId());
        assertThat(imageRegister1).isEqualTo(imageRegister2);
        imageRegister2.setId(2L);
        assertThat(imageRegister1).isNotEqualTo(imageRegister2);
        imageRegister1.setId(null);
        assertThat(imageRegister1).isNotEqualTo(imageRegister2);
    }
}
