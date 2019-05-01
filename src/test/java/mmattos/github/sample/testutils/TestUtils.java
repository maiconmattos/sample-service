package mmattos.github.sample.testutils;

import static java.nio.charset.Charset.defaultCharset;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import lombok.experimental.UtilityClass;
import org.apache.commons.io.IOUtils;

@UtilityClass
public final class TestUtils {

  public static String readData(String sourceJsonFile) throws IOException {
    return IOUtils.toString(TestUtils.class.getResourceAsStream(sourceJsonFile), defaultCharset());
  }

  public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    return mapper.writeValueAsBytes(object);
  }

}
