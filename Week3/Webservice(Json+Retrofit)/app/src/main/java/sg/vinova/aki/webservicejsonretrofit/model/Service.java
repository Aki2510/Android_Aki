package sg.vinova.aki.webservicejsonretrofit.model;

import android.support.annotation.NonNull;
import android.util.Log;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sg.vinova.aki.webservicejsonretrofit.model.entity.City;
import sg.vinova.aki.webservicejsonretrofit.model.entity.DailyForecast;
import sg.vinova.aki.webservicejsonretrofit.model.entity.SampleData;

/**
 * Created by Aki on 7/3/2017.
 */

public class Service {
    private static Service service = null;

    public Service() {

    }

    public static Service newInstance() {
        if (service == null)
            service = new Service();
        return service;
    }

    public void getDailyForecasts(String locationId, String apikey, final OnGetDailyForecastsListener listener) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://dataservice.accuweather.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DataService dataService = retrofit.create(DataService.class);
        Call<SampleData> result = dataService.listData(locationId, apikey);

        result.enqueue(new Callback<SampleData>()

        {
            @Override
            public void onResponse
                    (@NonNull Call<SampleData> call, @NonNull Response<SampleData> response) {
                if (response.isSuccessful()) {
                    List<DailyForecast> list = response.body().getDailyForecasts();

                    listener.onSuccessful(list);
                    Log.i("forecast", "Get daily forecasts successful");
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        listener.onFailure(jObjError.getString("Message"));
                        Log.e("forecast", jObjError.getString("Message"));
                    } catch (Exception e) {
                        Log.e("forecast", e.getMessage());
                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<SampleData> call, @NonNull Throwable t) {
                listener.onFailure(t.getMessage());
                Log.e("forecast", t.getMessage());
            }
        });
    }

    public void getCities(String q, String apikey, final OnGetCityListener listener) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://dataservice.accuweather.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DataService dataService = retrofit.create(DataService.class);
        Call<List<City>> result = dataService.listCities(q, apikey);

        result.enqueue(new Callback<List<City>>() {
            @Override
            public void onResponse
                    (@NonNull Call<List<City>> call, @NonNull Response<List<City>> response) {
                if (response.isSuccessful()) {
                    City city = response.body().get(0);

                    listener.onSuccessful(city);
                    Log.i("city", "Get city successful");
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        listener.onFailure(jObjError.getString("Message"));
                        Log.e("city", jObjError.getString("Message"));
                    } catch (Exception e) {
                        Log.e("city", e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<City>> call, @NonNull Throwable t) {
                listener.onFailure(t.getMessage());
                Log.e("city", t.getMessage());
            }
        });
    }

    public interface OnGetDailyForecastsListener {
        void onSuccessful(List<DailyForecast> list);

        void onFailure(String message);
    }

    public interface OnGetCityListener {
        void onSuccessful(City city);

        void onFailure(String message);
    }
}
