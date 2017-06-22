package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import adapter.ExploreAdapter;
import model.Delivery;
import sg.vinova.aki.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Explore.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Explore#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Explore extends Fragment implements ExploreAdapter.RecyclerViewOnClickListener {
    private static final String ARG_LIST_EXPLORE = "listexplore";
    private List<Delivery> explores;
    private OnFragmentInteractionListener mListener;

    public Explore() {
        // Required empty public constructor
    }

    public static Explore newInstance(List<Delivery> explores) {
        Explore fragment = new Explore();
        Bundle args = new Bundle();
        args.putSerializable(ARG_LIST_EXPLORE, (Serializable) explores);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            explores = (List<Delivery>) getArguments().getSerializable(ARG_LIST_EXPLORE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_explore, container, false);

        RecyclerView myRecyclerView = (RecyclerView) rootView.findViewById(R.id.explore_recycler_view);
        myRecyclerView.setHasFixedSize(true);

        LinearLayoutManager myLinearLayoutManager = new LinearLayoutManager(getActivity());
        myRecyclerView.setLayoutManager(myLinearLayoutManager);

        //TO-DO

        ExploreAdapter adapter = new ExploreAdapter(getContext(), explores, this);
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

    @Override
    public void onClickListener(View v, int position) {
        NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);
        navigationView.getMenu().performIdentifierAction(R.id.nav_deliveries, 0);
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
