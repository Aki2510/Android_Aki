package sg.vinova.aki.webservicejsonretrofit.view;

import java.util.List;

import sg.vinova.aki.webservicejsonretrofit.model.entity.DailyForecast;

/**
 * Created by Aki on 6/29/2017.
 */

public interface CityWeatherView {
    void setTodayTemperature(int icon, String currentTemperature, String maxTemperature, String minTemperature);

    void setItemsInfo(DailyForecast today);
    void setItemInfo(int icon, String name, String value, int index);

    void setListDailyForecast(List<DailyForecast> list);

    void showSuccessfulMessage();
    void showFailedMessage(String message);

    void showProgressBar();
    void hideProgressBar();
}
