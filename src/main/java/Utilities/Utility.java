package Utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.entity.StringEntity;
import org.pojoClasses.Boards;

import java.io.UnsupportedEncodingException;

public class Utility{

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static StringEntity serialization(Boards board) throws UnsupportedEncodingException, JsonProcessingException {

        String JSON = objectMapper.writeValueAsString(board);
        return new StringEntity(JSON);

    }

    public static Boards deserialization(String JSON) throws JsonProcessingException {

        return objectMapper.readValue(JSON,Boards.class);

    }

    public static JsonNode stringToJsonMapper (String responseBody) throws JsonProcessingException {

        return objectMapper.readTree(responseBody);

    }

}
