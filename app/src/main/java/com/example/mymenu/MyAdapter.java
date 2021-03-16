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

    public MyAdapter(Context ct, List<String> ingredient, List<String> measurement){
        list1 = ingredient;
        list2 = measurement;
        context = ct;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
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

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView myText1, myText2;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myText1 = itemView.findViewById(R.id.myTextView2);
            myText2 = itemView.findViewById(R.id.myTextView1);
        }
    }
}
