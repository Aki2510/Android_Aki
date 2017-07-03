package sg.vinova.aki.webservicejsonretrofit.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import sg.vinova.aki.webservicejsonretrofit.IconGetter;
import sg.vinova.aki.webservicejsonretrofit.R;
import sg.vinova.aki.webservicejsonretrofit.view.adapter.MyAdapter;
import sg.vinova.aki.webservicejsonretrofit.model.entity.DailyForecast;
import sg.vinova.aki.webservicejsonretrofit.presenter.CityWeatherPresenter;
import sg.vinova.aki.webservicejsonretrofit.presenter.CityWeatherPresenterImpl;

public class CityWeatherFragment extends Fragment implements CityWeatherView {
    ProgressBar progressBar;

    ImageView ivTemperatureImage;
    TextView tvCurrentTemperature;
    TextView tvMaxTemperature;
    TextView tvMinTemperature;

    RelativeLayout infoLayout;
    ImageView ivInfoImage;
    TextView tvInfoName;
    TextView tvInfoValue;

    RelativeLayout infoLayout2;
    ImageView ivInfoImage2;
    TextView tvInfoName2;
    TextView tvInfoValue2;

    RelativeLayout infoLayout3;
    ImageView ivInfoImage3;
    TextView tvInfoName3;
    TextView tvInfoValue3;

    RelativeLayout infoLayout4;
    ImageView ivInfoImage4;
    TextView tvInfoName4;
    TextView tvInfoValue4;

    RecyclerView recyclerView;

    private static final String ARG_LOCATION_ID = "locationId";

    private OnFragmentInteractionListener mListener;
    private String locationId;

    MyAdapter adapter = null;
    boolean isLoadedAlready = false;
    DailyForecast today = null;

    public CityWeatherFragment() {
        // Required empty public constructor
    }

    public static CityWeatherFragment newInstance(String locationId) {
        CityWeatherFragment fragment = new CityWeatherFragment();
        Bundle args = new Bundle();
        args.putString(ARG_LOCATION_ID, locationId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            locationId = getArguments().getString(ARG_LOCATION_ID);
        }
        adapter = new MyAdapter(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_city_weather, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);
        if (!isLoadedAlready) {
            showProgressBar();
            CityWeatherPresenter cityWeatherPresenter = new CityWeatherPresenterImpl(this);
            cityWeatherPresenter.getData(locationId);
        } else {
            if (today != null)
                setItemsInfo(today);
        }
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    void init(View v) {
        progressBar = (ProgressBar) v.findViewById(R.id.progress_bar);

        ivTemperatureImage = (ImageView) v.findViewById(R.id.temperature_image);
        tvCurrentTemperature = (TextView) v.findViewById(R.id.current_temperature);
        tvMaxTemperature = (TextView) v.findViewById(R.id.max_temperature);
        tvMinTemperature = (TextView) v.findViewById(R.id.min_temperature);

        infoLayout = (RelativeLayout) v.findViewById(R.id.basic_info_item);
        ivInfoImage = (ImageView) infoLayout.findViewById(R.id.basic_info_item_image);
        tvInfoName = (TextView) infoLayout.findViewById(R.id.basic_info_item_name);
        tvInfoValue = (TextView) infoLayout.findViewById(R.id.basic_info_item_value);

        infoLayout2 = (RelativeLayout) v.findViewById(R.id.basic_info_item_2);
        ivInfoImage2 = (ImageView) infoLayout2.findViewById(R.id.basic_info_item_image);
        tvInfoName2 = (TextView) infoLayout2.findViewById(R.id.basic_info_item_name);
        tvInfoValue2 = (TextView) infoLayout2.findViewById(R.id.basic_info_item_value);

        infoLayout3 = (RelativeLayout) v.findViewById(R.id.basic_info_item_3);
        ivInfoImage3 = (ImageView) infoLayout3.findViewById(R.id.basic_info_item_image);
        tvInfoName3 = (TextView) infoLayout3.findViewById(R.id.basic_info_item_name);
        tvInfoValue3 = (TextView) infoLayout3.findViewById(R.id.basic_info_item_value);

        infoLayout4 = (RelativeLayout) v.findViewById(R.id.basic_info_item_4);
        ivInfoImage4 = (ImageView) infoLayout4.findViewById(R.id.basic_info_item_image);
        tvInfoName4 = (TextView) infoLayout4.findViewById(R.id.basic_info_item_name);
        tvInfoValue4 = (TextView) infoLayout4.findViewById(R.id.basic_info_item_value);

        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
//        recyclerView.post(requestRv());

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
    }

//    private Runnable requestRv(){
//        return new Runnable(){
//            @Override
//            public void run() {
//                if (recyclerView!= null && adapter!= null)
//                    adapter.setPaddings(new int[]{recyclerView.getPaddingLeft(), recyclerView.getPaddingRight()});
//            }
//        };
//    }

    @Override
    public void setTodayTemperature(int icon, String currentTemperature, String maxTemperature, String minTemperature) {
        IconGetter iconGetter = new IconGetter(getContext());
        ivTemperatureImage.setImageDrawable(iconGetter.getIcon(icon));
        tvCurrentTemperature.setText(currentTemperature);
        tvMaxTemperature.setText(maxTemperature);
        tvMinTemperature.setText(minTemperature);
    }

    @Override
    public void setItemsInfo(DailyForecast today) {
        if (!isLoadedAlready)
            this.today = today;

        setTodayTemperature(today.getDay().getIcon(),
                String.valueOf(Math.round(today.getTemperature().getMaximum().getValue())) + "\u00B0c",
                String.valueOf(Math.round(today.getTemperature().getMaximum().getValue())) + "\u00B0",
                String.valueOf(Math.round(today.getTemperature().getMinimum().getValue())) + "\u00B0");

        setItemInfo(1, "RealFeel",
                String.valueOf(Math.round(today.getRealFeelTemperature().getMaximum().getValue())) + "\u00B0", 1);
        setItemInfo(2, "Khả năng mưa",
                today.getDay().getPrecipitationProbability() + "%", 2);

        setItemInfo(3, "Chỉ số UV",
                today.getAirAndPollen().get(5).getCategory(), 3);

        String time = today.getSun().getSet().substring(11, 16);
        setItemInfo(4, "Hoàng hôn", time, 4);
    }

    @Override
    public void setItemInfo(int icon, String name, String value, int index) {
        switch (index) {
            case 1:
                ivInfoImage.setImageDrawable(getResources().getDrawable(R.drawable.real_feel));
                tvInfoName.setText(name);
                if (value != null)
                    tvInfoValue.setText(value);
                else
                    tvInfoValue.setText("N/A");
                break;
            case 2:
                ivInfoImage2.setImageDrawable(getResources().getDrawable(R.drawable.precipitation_probability));
                tvInfoName2.setText(name);
                if (value != null)
                    tvInfoValue2.setText(value);
                else
                    tvInfoValue.setText("N/A");
                break;
            case 3:
                ivInfoImage3.setImageDrawable(getResources().getDrawable(R.drawable.uv_index));
                tvInfoName3.setText(name);
                if (value != null)
                    tvInfoValue3.setText(value);
                else
                    tvInfoValue.setText("N/A");
                break;
            case 4:
                ivInfoImage4.setImageDrawable(getResources().getDrawable(R.drawable.sun_set));
                tvInfoName4.setText(name);
                if (value != null)
                    tvInfoValue4.setText(value);
                else
                    tvInfoValue.setText("N/A");
                break;
            default:
                break;
        }
    }

    @Override
    public void setListDailyForecast(List<DailyForecast> list) {
        adapter.setList(list);
//        recyclerView.post(requestRv());
        isLoadedAlready = true;
    }

    @Override
    public void showSuccessfulMessage() {
        hideProgressBar();
        Toast.makeText(getContext(), "Successful", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailedMessage(String message) {
        hideProgressBar();
        Toast.makeText(getContext(), "Failed: " + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }
}
