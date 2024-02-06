package Endpoints;

import Utilities.Utility;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.pojoClasses.Boards;

import java.io.IOException;

public class BoardEndpoints {

    public static CloseableHttpResponse createBoard(Boards board) throws IOException {
//        Response response = RestAssured.given()
//                .contentType(JSON)
//                .accept(JSON)
//                .body(board)
//                .when().post(Routes.baseUrl);
//        return response;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(Routes.baseUrl);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        httpPost.setEntity(Utility.converter(board));

        CloseableHttpResponse responseBody = httpclient.execute(httpPost);
        return responseBody;
    }

    public static CloseableHttpResponse getBoardByID(Boards board){return null;}

//    private static ResponseHandler<String> responseHandler(final int leastStatus, final int mostStatus) {
//        return response -> {
//            int status = response.getStatusLine().getStatusCode();
//            if (status >= leastStatus && status < mostStatus) {
//                HttpEntity entity = response.getEntity();
//                return entity != null ? EntityUtils.toString(entity) : null;
//            } else {
//                throw new ClientProtocolException("Unexpected response status: " + status);
//            }
//        };
//    }

}
