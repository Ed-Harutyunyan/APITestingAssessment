package Endpoints.RestAssured;

import Endpoints.TrelloAPIUtil;
import Utilities.Utility;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.pojoClasses.Boards;
import java.io.IOException;
import java.util.Optional;

import static io.restassured.http.ContentType.JSON;

public class RestAssuredUtils {

    /**
     * Creates a board with API calls.
     * Uses helper method <code>generateURL()</code>
     * @param boards
     * @return Response Type
     * @throws IOException
     */
    public static Response createBoard(Boards boards) throws IOException {

        Response response = RestAssured.given()
                .spec(Utility.generateURL(null))
                .contentType(JSON)
                .accept(JSON)
                .queryParam("name",boards.getName())
                .queryParam("desc",boards.getDesc())
                .post();
        return response;

    }

    /**
     * Gets a board given the ID
     * @param boards
     * @return Response Type
     */
    public static Response getBoard(Boards boards){

        Response response = RestAssured.given(generateURL(boards.getId()))
                .get();
        return response;

    }

    /**
     * Updates the board given the board and the new parameter
     * @param boards
     * @param value new parameter
     * @param key key to be changed
     * @return Response Type
     */
    public static Response updateBoard(Boards boards, String key, String value) {

        Response response = RestAssured.given(generateURL(boards.getId()))
                .queryParam(key, value)
                .put();
        return response;

    }

    /**
     * Deletes the board with given ID
     * @param boards
     * @return Response Type
     */
    public static Response deleteBoard(Boards boards){

        Response response = RestAssured.given(generateURL(boards.getId()))
                .delete();
        return response;

    }


    //Helper method to generate the URL for the needed operations
    private static RequestSpecification generateURL(String id) {

        return RestAssured.given()
                .baseUri(TrelloAPIUtil.BASEURI)
                .basePath(Utility.getBasePath(id))
                .queryParam("key", TrelloAPIUtil.KEY)
                .queryParam("token", TrelloAPIUtil.TOKEN);

    }

}
