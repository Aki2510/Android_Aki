package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import adapter.DeliveryAdapter;
import model.Delivery;
import sg.vinova.aki.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Deliveries.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Deliveries#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Deliveries extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final String ARG_POSITION = "position";
    private static final String ARG_LIST_DELIVERY = "listdelivery";

    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
    private int position;
    private List<Delivery> deliveries;

    private OnFragmentInteractionListener mListener;

    public Deliveries() {
        // Required empty public constructor
    }

    public static Deliveries newInstance(List<Delivery> deliveries) {
        Deliveries fragment = new Deliveries();
        Bundle args = new Bundle();
        args.putSerializable(ARG_LIST_DELIVERY, (Serializable) deliveries);
        fragment.setArguments(args);
        return fragment;
    }

    public static Deliveries newInstance(int position, List<Delivery> deliveries) {
        Deliveries fragment = new Deliveries();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        args.putSerializable(ARG_LIST_DELIVERY, (Serializable) deliveries);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            position = getArguments().getInt(ARG_POSITION);
            deliveries = (List<Delivery>) getArguments().getSerializable(ARG_LIST_DELIVERY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_deliveries, container, false);

        RecyclerView myRecyclerView = (RecyclerView) rootView.findViewById(R.id.deliveries_recycler_view);
        myRecyclerView.setHasFixedSize(true);

        LinearLayoutManager myLinearLayoutManager = new LinearLayoutManager(getActivity());
        myRecyclerView.setLayoutManager(myLinearLayoutManager);

        //TO-DO
        DeliveryAdapter adapter = new DeliveryAdapter(getContext(), deliveries);
        myRecyclerView.setAdapter(adapter);

        return rootView;
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
