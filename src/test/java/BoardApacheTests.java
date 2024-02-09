import Endpoints.ApacheHTTPClient.ApacheHTTPClientUtils;
import Utilities.Utility;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.http.util.EntityUtils;
import org.pojoClasses.Boards;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;

public class BoardApacheTests {

    Boards boards = new Boards();
    String id;
    private void setUpData(){
        boards.setName("BoardToTest");
        boards.setDesc("Some Description");
    }

    @Test(priority = 1)
    public void boardPostTest() throws IOException {

        setUpData();

        try{

            CloseableHttpResponse httpResponse = ApacheHTTPClientUtils.httpPostBoard(boards);

            boards.setId(jsonResponseGenerator(httpResponse).get("id").asText());
            System.out.println(boards.getId());

            Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), 200);

        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

    }

    @Test(priority = 2, dependsOnMethods = "boardPostTest")
    public void boardGetTest() throws IOException {

        try{

            CloseableHttpResponse httpResponse = ApacheHTTPClientUtils.httpGetBoard(boards);

            JsonNode jsonResponse = jsonResponseGenerator(httpResponse);

            Assert.assertNotSame(jsonResponse.get("id"), null);
            Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), 200);
            Assert.assertEquals(jsonResponseGenerator(httpResponse).get("name").asText(), boards.getName());

        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

    }

    @Test(priority = 3, dependsOnMethods = "boardPostTest")
    public void boardUpdateTest() {

        try{

            CloseableHttpResponse httpResponse = ApacheHTTPClientUtils.httpUpdateBoard(boards, "name",
                    "New Name");

            Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), 200);
            Assert.assertNotSame(jsonResponseGenerator(httpResponse).get("name").asText(), boards.getName());

        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

    }

    @Test(priority = 4, dependsOnMethods = "boardPostTest")
    public void boardDeleteTest() {

        try{

            CloseableHttpResponse httpResponse = ApacheHTTPClientUtils.httpDeleteBoard(boards);

            Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), 200);
            Assert.assertNull(jsonResponseGenerator(httpResponse).get("id"));

        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

    }


    private static JsonNode jsonResponseGenerator(CloseableHttpResponse httpResponse) throws IOException {

        String responseBody = EntityUtils.toString(httpResponse.getEntity());

        return Utility.stringToJsonMapper(responseBody);
    }

}
