/**
 * The {@code XmlToJsonConverter$} class represents Functionalities
 *
 * @author ArunKumar D
 */

package task_02;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.File;
import java.io.IOException;

public class XmlToJsonConverter {

    public static String convertXmlToJson(String inputXmlFilePath) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        File xmlFile = new File(inputXmlFilePath);
        JsonNode jsonNode = xmlMapper.readTree(xmlFile);

        ObjectMapper jsonMapper = new ObjectMapper();
        return jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
    }
}

