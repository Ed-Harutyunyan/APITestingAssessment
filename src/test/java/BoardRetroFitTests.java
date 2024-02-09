import Endpoints.RetroFit.RetroFitConfig;
import Endpoints.RetroFit.RetroFitUtils;
import org.pojoClasses.Boards;
import org.testng.Assert;
import org.testng.annotations.*;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;


public class BoardRetroFitTests {

    private RetroFitUtils retroFitUtils;
    private Boards board = new Boards();

    @BeforeClass
    public void setUp() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.trello.com/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retroFitUtils = retrofit.create(RetroFitUtils.class);
    }

    @Test(priority = 1)
    public void testCreateBoard() throws IOException {
        
        board.setName("BoardToAdd");
        board.setDesc("BoardToAddDesc");

        Call<Boards> call = retroFitUtils.createPost(board, RetroFitConfig.authorizationMap);
        Response<Boards> response = call.execute();
        board.setId(response.body().getId());

        Assert.assertEquals(response.code(), 200);
    }

    @Test(priority = 2, dependsOnMethods = "testCreateBoard")
    public void testGetBoard() throws IOException {

        Call<Boards> call = retroFitUtils.getBoard(board.getId(), RetroFitConfig.authorizationMap);
        Response<Boards> response = call.execute();

        Assert.assertEquals(response.code(), 200);
    }

    @Test(priority = 3, dependsOnMethods = "testCreateBoard")
    public void testUpdateBoard() throws IOException {

        Boards updatedBoard = new Boards();
        updatedBoard.setName("newName");

        Call<Boards> call = retroFitUtils.updateBoard(board.getId(), updatedBoard, RetroFitConfig.authorizationMap);
        Response<Boards> response = call.execute();

        Assert.assertEquals(response.code(), 200);
    }

    @Test(priority = 4, dependsOnMethods = "testCreateBoard")
    public void testDeleteBoard() throws IOException {

        Call<Boards> call = retroFitUtils.deleteBoard(board.getId(), RetroFitConfig.authorizationMap);
        Response<Boards> response = call.execute();

        Assert.assertEquals(response.code(), 200);
    }
}
