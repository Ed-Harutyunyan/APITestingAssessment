package Endpoints.ApacheHTTPClient;

import Endpoints.TrelloAPIUtil;
import Utilities.Utility;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.pojoClasses.Boards;

import java.io.IOException;
import java.util.Optional;

public class ApacheHTTPClientUtils {


    static CloseableHttpClient httpclient = HttpClients.createDefault();

    public static CloseableHttpResponse httpPostBoard(Boards boards) throws IOException {

        HttpPost httpPost = new HttpPost(generateURL(null));
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        httpPost.setEntity(Utility.serialization(boards));

        CloseableHttpResponse response = httpclient.execute(httpPost);

        return response;
    }

    public static CloseableHttpResponse httpGetBoard(Boards boards) throws IOException {

        HttpGet httpGet = new HttpGet(generateURL(boards.getId()));

        CloseableHttpResponse response = httpclient.execute(httpGet);

        return response;
    }

    public static CloseableHttpResponse httpUpdateBoard(Boards boards, String key, String value) throws IOException {

        HttpPut httpPut = new HttpPut(generateURL(boards.getId()));
        httpPut.setHeader("Accept", "application/json");
        httpPut.setHeader("Content-type", "application/json");
        String updateData = "{\"" + key + "\": \"" + value + "\"}";


        StringEntity entity = new StringEntity(updateData);
        httpPut.setEntity(entity);

        CloseableHttpResponse response = httpclient.execute(httpPut);

        return response;
    }

    public static CloseableHttpResponse httpDeleteBoard(Boards boards) throws IOException {

        HttpDelete httpDelete = new HttpDelete(generateURL(boards.getId()));

        CloseableHttpResponse response = httpclient.execute(httpDelete);

        return response;
    }

    public static JsonNode jsonResponseGenerator(CloseableHttpResponse httpResponse) throws IOException {

        String responseBody = EntityUtils.toString(httpResponse.getEntity());

        return Utility.stringToJsonMapper(responseBody);
    }

    //Helper Methods
    private static String generateURL(String id){

        return TrelloAPIUtil.BASEURI + Utility.getBasePath(id) +
                "?key=" + TrelloAPIUtil.KEY + "&" +
                "token=" + TrelloAPIUtil.TOKEN;
    }

}
