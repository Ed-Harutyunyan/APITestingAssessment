package Utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.entity.StringEntity;
import org.pojoClasses.Boards;

import java.io.UnsupportedEncodingException;

public class Utility {
    public static StringEntity converter(Boards board) throws UnsupportedEncodingException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(board);

        return new StringEntity(json);
    }
}
