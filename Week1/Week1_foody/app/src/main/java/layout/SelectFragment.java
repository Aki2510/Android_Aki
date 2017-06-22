package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adapter.HotDealRecyclerViewAdapter;
import model.HotDeal;
import sg.vinova.aki.week1_foody.R;

public class SelectFragment extends Fragment {
    private OnFragmentInteractionListener mListener;

    public SelectFragment() {
        // Required empty public constructor
    }

    public static SelectFragment newInstance() {
        SelectFragment fragment = new SelectFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_select, container, false);

        List<HotDeal> deals = new ArrayList<>();
        deals.add(new HotDeal("https://fthmb.tqn.com/aFBarnfKqMndmb4P_ELzgc8Esqs=/960x0/filters:no_upscale()/about/Steaks-on-Grill-5855ab4a3df78ce2c3a7cd87.jpg",
                "Wednesday",
                "25% off for a table of two",
                "Dinner 25% off, and only 68 USD/each in Solois Coffee"));

        deals.add(new HotDeal("https://fthmb.tqn.com/aFBarnfKqMndmb4P_ELzgc8Esqs=/960x0/filters:no_upscale()/about/Steaks-on-Grill-5855ab4a3df78ce2c3a7cd87.jpg",
                "Wednesday",
                "25% off for a table of two",
                "Dinner 25% off, and only 68 USD/each in Solois Coffee"));

        deals.add(new HotDeal("https://fthmb.tqn.com/aFBarnfKqMndmb4P_ELzgc8Esqs=/960x0/filters:no_upscale()/about/Steaks-on-Grill-5855ab4a3df78ce2c3a7cd87.jpg",
                "Wednesday",
                "25% off for a table of two",
                "Dinner 25% off, and only 68 USD/each in Solois Coffee"));

        deals.add(new HotDeal("https://fthmb.tqn.com/aFBarnfKqMndmb4P_ELzgc8Esqs=/960x0/filters:no_upscale()/about/Steaks-on-Grill-5855ab4a3df78ce2c3a7cd87.jpg",
                "Wednesday",
                "25% off for a table of two",
                "Dinner 25% off, and only 68 USD/each in Solois Coffee"));

        deals.add(new HotDeal("https://fthmb.tqn.com/aFBarnfKqMndmb4P_ELzgc8Esqs=/960x0/filters:no_upscale()/about/Steaks-on-Grill-5855ab4a3df78ce2c3a7cd87.jpg",
                "Wednesday",
                "25% off for a table of two",
                "Dinner 25% off, and only 68 USD/each in Solois Coffee"));

        HotDealRecyclerViewAdapter adapter = new HotDealRecyclerViewAdapter(getContext(), deals);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        final RecyclerView rvHotDeal = (RecyclerView) v.findViewById(R.id.select_suggest_recycler_view);
        rvHotDeal.setLayoutManager(lm);
        rvHotDeal.setAdapter(adapter);



        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
