package com.example.android.applicationtaskhi2020.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.applicationtaskhi2020.data.Goals;
import com.example.android.applicationtaskhi2020.R;

import java.util.ArrayList;

public class GoalsAdapter extends RecyclerView.Adapter<GoalsAdapter.GoalsAdapterViewHolder> {

    private ArrayList<Goals> mGoalsData;

    private final GoalsAdapterOnClickHandler mClickHandler;

    public interface GoalsAdapterOnClickHandler {
        void onClick(Goals goals);
    }

    public GoalsAdapter(GoalsAdapterOnClickHandler clickHandler) {
        mClickHandler = clickHandler;
    }

    public class GoalsAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public final ImageView mImageView;
        public final TextView mTextView;



        public GoalsAdapterViewHolder(View view){
            super(view);
            mImageView = (ImageView) view.findViewById(R.id.goals_iv);
            mTextView = (TextView) view.findViewById(R.id.goals_tv);
            view.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
                int adapterPosision = getAdapterPosition();
                Goals currentMovie = mGoalsData.get(adapterPosision);
                mClickHandler.onClick(currentMovie);
                removeAt(adapterPosision);
        }
    }


    @NonNull
    @Override
    public GoalsAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);

        return new GoalsAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GoalsAdapterViewHolder holder, int position) {

        holder.itemView.setTag(mGoalsData.get(position));
        Goals goals = mGoalsData.get(position);
        int imageR = goals.getImage();
        int goalsR = goals.getGoalString();

        holder.mImageView.setImageResource(imageR);
        holder.mTextView.setText(goalsR);

    }

    public void removeAt(int position) {
        mGoalsData.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mGoalsData.size());
    }


    @Override
    public int getItemCount() {
        if (null == mGoalsData) return 0;
        return mGoalsData.size();
    }

    public void setGoalsData(ArrayList<Goals> arrayList){
        mGoalsData = arrayList;
    }

}
