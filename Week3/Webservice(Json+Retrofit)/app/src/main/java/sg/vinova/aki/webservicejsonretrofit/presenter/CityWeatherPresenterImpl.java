package sg.vinova.aki.webservicejsonretrofit.presenter;

import java.util.List;

import sg.vinova.aki.webservicejsonretrofit.model.Service;
import sg.vinova.aki.webservicejsonretrofit.model.entity.DailyForecast;
import sg.vinova.aki.webservicejsonretrofit.view.CityWeatherView;

/**
 * Created by Aki on 6/29/2017.
 */

public class CityWeatherPresenterImpl implements CityWeatherPresenter, Service.OnGetDailyForecastsListener {
    private static final String apikey = "bXlKlJaOrvVFbSxsIYEh8wa7eGBGpqnH";
    private CityWeatherView cityWeatherView;
    private Service service;

    public CityWeatherPresenterImpl(CityWeatherView mainView) {
        this.cityWeatherView = mainView;
        service = Service.newInstance();
    }

    @Override
    public void getData(String locationId) {
        service.getDailyForecasts(locationId, apikey, this);
    }

    private void setItemsDefaultInfo() {
        cityWeatherView.setTodayTemperature(1, "N/A", "N/A", "N/A");
        cityWeatherView.setItemInfo(1, "RealFeel", "N/A", 1);
        cityWeatherView.setItemInfo(2, "Khả năng mưa", "N/A", 2);
        cityWeatherView.setItemInfo(3, "Chỉ số UV", "N/A", 3);
        cityWeatherView.setItemInfo(4, "Hoàng hôn", "N/A", 4);
    }

    @Override
    public void onSuccessful(List<DailyForecast> list) {
        DailyForecast today = list.remove(0);
        cityWeatherView.setItemsInfo(today);

        cityWeatherView.setListDailyForecast(list);
        cityWeatherView.showSuccessfulMessage();
    }

    @Override
    public void onFailure(String message) {
        setItemsDefaultInfo();

        cityWeatherView.showFailedMessage(message);
    }
}
