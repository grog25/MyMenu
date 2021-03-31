package com.example.mymenu;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    List<String> list1, list2;
    Context context;
    private OnRecipeListener mOnRecipeListener;

    public MyAdapter(Context ct, List<String> leftSide, List<String> rightSide, OnRecipeListener onRecipeListener){
        list1 = rightSide;
        list2 = leftSide;
        context = ct;
        this.mOnRecipeListener = onRecipeListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view, mOnRecipeListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.myText1.setText(list1.get(position));
        holder.myText2.setText(list2.get(position));

    }

    @Override
    public int getItemCount() {
        Log.d("getItemCount: ", String.valueOf(list1.size()));
        return list1.size();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView myText1, myText2;
        OnRecipeListener onRecipeListener;

        public MyViewHolder(@NonNull View itemView, OnRecipeListener onRecipeListener) {
            super(itemView);
            myText1 = itemView.findViewById(R.id.myTextView2);
            myText2 = itemView.findViewById(R.id.myTextView1);
            this.onRecipeListener = onRecipeListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onRecipeListener.onRecipeClick(getAdapterPosition());
        }
    }

    public interface OnRecipeListener{
        void onRecipeClick(int position);
    }
}
