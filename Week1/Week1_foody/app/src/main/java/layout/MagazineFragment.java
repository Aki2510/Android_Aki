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

import java.util.ArrayList;
import java.util.List;

import adapter.MagazineRecyclerViewAdapter;
import model.MagazineArticle;
import sg.vinova.aki.week1_foody.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MagazineFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MagazineFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MagazineFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public MagazineFragment() {
        // Required empty public constructor
    }

    public static MagazineFragment newInstance() {
        MagazineFragment fragment = new MagazineFragment();
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
        View v = inflater.inflate(R.layout.fragment_magazine, container, false);

        List<MagazineArticle> magazineArticleList = new ArrayList<>();
        magazineArticleList.add(new MagazineArticle(
                "https://fthmb.tqn.com/aFBarnfKqMndmb4P_ELzgc8Esqs=/960x0/filters:no_upscale()/about/Steaks-on-Grill-5855ab4a3df78ce2c3a7cd87.jpg",
                "Via Cookbook",
                "Barbecue Party Tips For A Truly Amazing Event",
                "April, 26"
        ));

        magazineArticleList.add(new MagazineArticle(
                "https://fthmb.tqn.com/aFBarnfKqMndmb4P_ELzgc8Esqs=/960x0/filters:no_upscale()/about/Steaks-on-Grill-5855ab4a3df78ce2c3a7cd87.jpg",
                "Via Cookbook",
                "Barbecue Party Tips For A Truly Amazing Event",
                "April, 26"
        ));
        MagazineRecyclerViewAdapter adapter = new MagazineRecyclerViewAdapter(getContext(), magazineArticleList);
        RecyclerView rvMagazine = (RecyclerView) v.findViewById(R.id.magazine_recycler_view);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getContext());
        rvMagazine.setLayoutManager(lm);
        rvMagazine.setAdapter(adapter);

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
