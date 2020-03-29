package com.example.android.applicationtaskhi2020.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.applicationtaskhi2020.R;
import com.example.android.applicationtaskhi2020.data.Goals;

import java.util.ArrayList;

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.InfoAdapterViewHolder> {

    private ArrayList<Goals> mGoalsData;

    private final InfoAdapterOnClickHandler mClickHandler;

    public interface InfoAdapterOnClickHandler {
        void onClick(Goals goals);
    }

    public InfoAdapter(InfoAdapterOnClickHandler clickHandler) {
        mClickHandler = clickHandler;
    }

    public class InfoAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public final ImageView mImageView;
        public final TextView mTextView;



        public InfoAdapterViewHolder(View view){
            super(view);
            mImageView = (ImageView) view.findViewById(R.id.info_list_item_iv);
            mTextView = (TextView) view.findViewById(R.id.info_list_item_tv);
            view.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            int adapterPosision = getAdapterPosition();
            Goals currentMovie = mGoalsData.get(adapterPosision);
            mClickHandler.onClick(currentMovie);
        }
    }


    @NonNull
    @Override
    public InfoAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.info_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);

        return new InfoAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InfoAdapterViewHolder holder, int position) {

        holder.itemView.setTag(mGoalsData.get(position));
        Goals goals = mGoalsData.get(position);
        int imageR = goals.getImage();
        int goalsR = goals.getGoalString();

        holder.mImageView.setImageResource(imageR);
        holder.mTextView.setText(goalsR);

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
