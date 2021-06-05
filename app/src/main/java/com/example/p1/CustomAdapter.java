package com.example.p1;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private List<String> courseTitles;
    private List<String> courseNames;
    private OnCardListener mOnCardListener;

    public CustomAdapter(List<String> courseTitles, List<String> courseNames, OnCardListener mOnCardListener) {

        this.courseTitles = courseTitles;
        this.courseNames = courseNames;
        this.mOnCardListener = mOnCardListener;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view, parent, false);
        return new CustomViewHolder(view, mOnCardListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        holder.textView1.setText(courseTitles.get(position));
        holder.textView2.setText(courseNames.get(position));
//        if (position % 2 == 0) {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#093558"));
            holder.textView1.setTextColor(Color.parseColor("#bba26a"));
            holder.textView1.setShadowLayer(1.6f, 1.5f, 1.3f, Color.parseColor("#ffffff"));
            holder.textView2.setTextColor(Color.parseColor("#bba26a"));
//        }
    }

    @Override
    public int getItemCount() {

        return courseTitles.size();
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardView cardView;
        TextView textView1, textView2;
        OnCardListener onCardListener;

        public CustomViewHolder(@NonNull View itemView, OnCardListener onCardListener) {

            super(itemView);
            cardView = itemView.findViewById(R.id.card);
            textView1 = itemView.findViewById(R.id.textView1);
            textView2 = itemView.findViewById(R.id.textView2);
            this.onCardListener = onCardListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onCardListener.onCardClick(getBindingAdapterPosition());
        }
    }

    public interface OnCardListener {
        void onCardClick(int position);
    }
}
