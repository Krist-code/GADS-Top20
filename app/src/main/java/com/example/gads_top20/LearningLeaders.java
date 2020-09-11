package com.example.gads_top20;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class LearningLeaders extends Fragment {

    public static String mJsonString;

    public LearningLeaders() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        Collecting json string resources from MainActivity

        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_learning_leaders, container, false);
        final RecyclerView learnersRecycler = (RecyclerView) fragmentView.findViewById(R.id.learner_recycler_list);
        final LinearLayoutManager learnersLayoutManager = new LinearLayoutManager(getContext());
        learnersRecycler.setLayoutManager(learnersLayoutManager);

//        Loading data from ArrayList<LearnerInfo> into recyclerView i.e connecting Data to Adapter
        List<LearnersInfo> learnersLeaders = DataManager.getInstance().getLearnersList();

        final LearnersRecyclerAdapter learnersRecyclerAdapter = new LearnersRecyclerAdapter(getContext(), learnersLeaders);
        learnersRecycler.setAdapter(learnersRecyclerAdapter);

        return fragmentView;

    }

    public void setJsonString(String s){
        mJsonString = s;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
