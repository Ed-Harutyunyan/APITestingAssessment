import Endpoints.RestAssured.RestAssuredUtils;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.pojoClasses.Boards;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;

public class BoardRestAssuredTests {

    Boards boards = new Boards();
    String id;
    private void setUpData(){
        boards.setName("BoardToTest");
        boards.setDesc("Some Description");
    }

    @Test(priority = 1)
    public void testBoardPost() throws IOException {

        setUpData();
        Response response = RestAssuredUtils.createBoard(boards);
        //Boards responseBoard = response.getBody().as(Boards.class);


        //Fetch the id of the newly created board
        JsonPath path = new JsonPath(response.then().extract().asString());
        this.id = path.get("id");
        boards.setId(id);

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertNotNull(response.jsonPath().get("id"));

    }
    @Test(priority = 2, dependsOnMethods = "testBoardPost")
    public void testBoardGet() {

        Response response = RestAssuredUtils.getBoard(boards);
        response.then().log().body();

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.jsonPath().getString("id"), boards.getId());

    }
    @Test(priority = 3, dependsOnMethods = "testBoardPost")
    public void testBoardPut() {

        Response response = RestAssuredUtils.updateBoard(boards,"name","New Name");
        response.then().log().body();

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertNotSame(response.jsonPath().get("name"), boards.getName());

    }
    @Test(priority = 4, dependsOnMethods = "testBoardPost")
    public void testBoardDelete() {

        Response response = RestAssuredUtils.deleteBoard(boards);
        response.then().log().body();

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertNull(response.jsonPath().get("id"));

    }

}