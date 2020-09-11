package com.example.gads_top20;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LearnersRecyclerAdapter extends RecyclerView.Adapter<LearnersRecyclerAdapter.ViewHolder>{

    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private final List<LearnersInfo> mTopLearners;

    public LearnersRecyclerAdapter(Context context, List<LearnersInfo> topLearners) {
        mContext = context;
        mTopLearners = topLearners;
        mLayoutInflater = LayoutInflater.from(mContext);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.learners_list, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LearnersInfo learner = mTopLearners.get(position);
        holder.mLearnerName.setText(learner.getFullName());
        holder.mLearnedHoursCountry.setText(learner.getLearnedHours() + " learning hours, " + learner.getCountry());
    }

    @Override
    public int getItemCount() {
        return mTopLearners.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public final TextView mLearnerName;
        public final TextView mLearnedHoursCountry;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mLearnerName = itemView.findViewById(R.id.textLearnerName);
            mLearnedHoursCountry = itemView.findViewById(R.id.textHoursCountry);
        }
    }
}
