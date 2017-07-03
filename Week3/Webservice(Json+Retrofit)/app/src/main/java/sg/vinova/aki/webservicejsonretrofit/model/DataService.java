package sg.vinova.aki.webservicejsonretrofit.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import sg.vinova.aki.webservicejsonretrofit.model.entity.City;
import sg.vinova.aki.webservicejsonretrofit.model.entity.SampleData;

/**
 * Created by Aki on 6/29/2017.
 */

public interface DataService {
    @GET("forecasts/v1/daily/5day/{locationId}?language=vi&details=true&metric=true")
    Call<SampleData> listData(@Path("locationId") String locationId, @Query("apikey") String apikey);

    @GET("locations/v1/cities/VN/search?")
    Call<List<City>> listCities(@Query("q") String q, @Query("apikey") String apikey);
}
