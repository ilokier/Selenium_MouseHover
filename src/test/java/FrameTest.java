import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class FrameTest extends BaseTest {
    private static Logger log = LoggerFactory.getLogger("FormTest.class");
    Map<String, String> srcValues;

    @Test
    public void shouldMatchChosenIconWithMainPicture() throws InterruptedException {
        homePage.goBlouses();
        homePage.goToCategoryFrame();
        srcValues = homePage.getMapOfLittleIconSrc();
        for (Map.Entry<String, String> pair : srcValues.entrySet()) {
            assertThat(pair.getKey(), equalTo(pair.getValue()));
        }
    }
}
