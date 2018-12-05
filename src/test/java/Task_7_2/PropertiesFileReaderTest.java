package Task_7_2;

import org.junit.Test;
import java.io.IOException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PropertiesFileReaderTest {

    @Test
    public void should_load_properties_from_file_and_find_value_bu_key() throws IOException {
        String properties = "/Users/aevtushenko/Downloads/EpamTask/Task07/src/test/resources/prop.properties";

        PropertiesFileReader propertiesReader = new PropertiesFileReader();
        propertiesReader.getProperties(properties);

        assertThat("Don't find the value", "alex@mail.ru", is(propertiesReader.getValueByKey("email")));
    }
}
