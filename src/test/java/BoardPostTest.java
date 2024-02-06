import Endpoints.BoardEndpoints;
import Utilities.BaseTest;
import io.restassured.RestAssured;
import org.pojoClasses.Boards;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;


public class BoardPostTest extends BaseTest {
    Boards boards = new Boards();
    public void setUpData(){
        boards.setName("Edgars Board");
        boards.setDesc("DSomething");

    }

    @Test
    public void testBoardPost() throws IOException {
        setUpData();
//        Response response = BoardEndpoints.createBoard(boards);
//        response.then().log().all();
//
//        Assert.assertEquals(response.getStatusCode(),200);
//        Assert.assertEquals(BoardEndpoints.createBoard(boards).getStatusLine().getStatusCode(),
//                200);

    }
}
