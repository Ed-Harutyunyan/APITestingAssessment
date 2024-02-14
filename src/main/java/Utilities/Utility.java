package Utilities;

import Endpoints.TrelloAPIUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.apache.http.entity.StringEntity;
import org.pojoClasses.Boards;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

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

    //Helper method to generate the URL for the needed operations
    public static RequestSpecification generateURL(String id) {

        return RestAssured.given()
                .baseUri(TrelloAPIUtil.BASEURI)
                .basePath(getBasePath(id))
                .queryParam("key", TrelloAPIUtil.KEY)
                .queryParam("token", TrelloAPIUtil.TOKEN);

    }

    //Helper method for generateURL() to see weather the ID is given or not and generate the base path
    public static String getBasePath(String id) {

        return Optional
                .ofNullable(id)
                .map(id1 -> TrelloAPIUtil.BOARDS_PATH + "/" + id1)
                .orElse(TrelloAPIUtil.BOARDS_PATH);

    }

    public static void setUpData(Boards boards){
        boards.setName("BoardToTest");
        boards.setDesc("Some Description");
    }

}
