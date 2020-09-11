package com.example.gads_top20;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SkillIQRecyclerAdapter extends RecyclerView.Adapter<SkillIQRecyclerAdapter.CustomViewHolder>{

    private final Context mContext;
    private final List<SkillIQInfo> mSkillsList;
    private final LayoutInflater mInflater;

    public SkillIQRecyclerAdapter(Context context, List<SkillIQInfo> skillsList) {
        mContext = context;
        mSkillsList = skillsList;
        mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View skillIQItemView = mInflater.inflate(R.layout.skills_iq_list, parent, false);
        return new CustomViewHolder(skillIQItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        SkillIQInfo skills = mSkillsList.get(position);
        holder.mSkillIQName.setText(skills.getSkillName());
        holder.mSkillIQScoreCountry.setText(skills.getSkillScores() + " skill IQ Score, " + skills.getSkillCountry());
    }

    @Override
    public int getItemCount() {
        return mSkillsList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        public final TextView mSkillIQScoreCountry;
        public final TextView mSkillIQName;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            mSkillIQName = itemView.findViewById(R.id.textSkilledName);
            mSkillIQScoreCountry = itemView.findViewById(R.id.textScoreCountry);
        }
    }
}
