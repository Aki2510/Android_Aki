package sg.vinova.aki.animation;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewFragment extends Fragment implements MyAdapter.onItemClickListener {
    private OnFragmentInteractionListener mListener;

    public RecyclerViewFragment() {
        // Required empty public constructor
    }

    public static RecyclerViewFragment newInstance() {
        RecyclerViewFragment fragment = new RecyclerViewFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<SampleData> data = SampleData.getSampleData();
        MyAdapter adapter = new MyAdapter(getContext(), data, this);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager lm = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(lm);
        recyclerView.setAdapter(adapter);


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
    public void onClick(ImageView imageView, TextView textView, SampleData data, int position) {
        DetailFragment detailFragment = DetailFragment.newInstance(data, position, imageView);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setSharedElementReturnTransition(TransitionInflater.from(getContext())
                    .inflateTransition(R.transition.trans));
            setExitTransition(TransitionInflater.from(getContext())
                    .inflateTransition(android.R.transition.fade));
            detailFragment.setSharedElementEnterTransition(TransitionInflater.from(getContext())
                    .inflateTransition(R.transition.trans));
            detailFragment.setEnterTransition(TransitionInflater.from(getContext())
                    .inflateTransition(android.R.transition.fade));
        }

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_placeholder, detailFragment)
                .addSharedElement(imageView, position + "_image")
                .addSharedElement(textView, position + "_title")
                .addToBackStack("REcyclerView")
                .commit();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
