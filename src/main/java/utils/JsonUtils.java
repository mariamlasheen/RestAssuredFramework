package utils;
import java.io.FileReader;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
public class JsonUtils {
   private JSONObject jsonObject;
    private static Map<String, Map<String, String>> allUsersData;

        private static final String JSON_FILE_PATH = "src/test/resources/UsersData.json";

        public static void loadTestData() {
            //lazy loader
            if (allUsersData != null){
                return;
            }
            try {
                FileReader reader = new FileReader(JSON_FILE_PATH);
                ObjectMapper mapper = new ObjectMapper();
                allUsersData = mapper.readValue(reader, new TypeReference<>() {});
            } catch (Exception e) {
                throw new RuntimeException("Failed to load JSON file", e);
            }
        }

        public static Map<String, String> getUsersData(String testCaseKey) {
            loadTestData();
            if (allUsersData == null) {
                throw new IllegalStateException("JSON data not loaded. Call loadTestData() first.");
            }
            return allUsersData.get(testCaseKey);

    }

    public static Map<String, String> parseJsonToMap(String json){
            try {
                return new ObjectMapper().readValue(json, new TypeReference<Map<String, String>>() {
                });
            } catch (Exception e) {
                throw new RuntimeException("Failed to parse JSON to Map", e);
            }
        } }
