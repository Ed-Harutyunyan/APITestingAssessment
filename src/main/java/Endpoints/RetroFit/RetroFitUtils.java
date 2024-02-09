package Endpoints.RetroFit;

import org.pojoClasses.Boards;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Map;

public interface RetroFitUtils {
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("boards")
    Call<Boards> createPost(@Body Boards boards, @QueryMap Map<String, String> params);

    @GET("boards/{id}")
    Call<Boards> getBoard(@Path("id") String id, @QueryMap Map<String, String> params);

    @PUT("boards/{id}")
    Call<Boards> updateBoard(@Path("id") String id, @Body Boards board, @QueryMap Map<String, String> params);

    @DELETE("boards/{id}")
    Call<Boards> deleteBoard(@Path("id") String id, @QueryMap Map<String, String> params);

}
