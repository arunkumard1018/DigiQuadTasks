/**
 * The {@code JsonFileHandler$} class represents Functionalities
 *
 * @author ArunKumar D
 */

package task_02;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class JsonFileHandler {

    public static void saveJsonToFile(String outputJsonFilePath, String jsonData) throws IOException {
        ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.writerWithDefaultPrettyPrinter().writeValue(new File(outputJsonFilePath), jsonMapper.readTree(jsonData));
    }
}
