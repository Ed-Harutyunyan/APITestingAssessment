package Endpoints.RetroFit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.Map;

public class RetroFitConfig {

    public static final String BASE_URI = "https://api.trello.com/1/";
    public static Map<String, String> authorizationMap = Map.of("key", "e17066fdcb09f46d1799b507d0ae3a60",
            "token", "ATTAa1bad9f4fad205eb9c1c62f6d7b89b42f0b106a5d87f5a98d2e9f4e2c58c41325F410BDC");

    public static RetroFitUtils setUp(RetroFitUtils retroFitUtils) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetroFitConfig.BASE_URI)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(RetroFitUtils.class);
    }
}
