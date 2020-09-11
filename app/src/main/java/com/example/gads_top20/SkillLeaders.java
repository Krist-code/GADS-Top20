package com.example.gads_top20;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class SkillLeaders extends Fragment {

    private static String mJsonString;

    public SkillLeaders() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_skill_leaders, container, false);
        final RecyclerView skillIQRecycler = fragmentView.findViewById(R.id.skill_iq_recycler_list);
        final LinearLayoutManager skillsLayoutManager = new LinearLayoutManager(getContext());
        skillIQRecycler.setLayoutManager(skillsLayoutManager);

//        Loading data from ArrayList<SkillIQInfo> into recyclerView i.e connecting Data to Adapter
        List<SkillIQInfo> skillLeaders = DataManager.getInstance().getSkillIQList();
        final SkillIQRecyclerAdapter skillIQRecyclerAdapter = new SkillIQRecyclerAdapter(getContext(), skillLeaders);
        skillIQRecycler.setAdapter(skillIQRecyclerAdapter);

        return fragmentView;
    }

    public void setJsonString(String s){
        mJsonString = s;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
