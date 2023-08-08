package com.example.week2practical;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

public class RecyclerViewAdapter2 extends RecyclerView.Adapter {

    private final RecyclerViewInt recyclerViewInt;
    Context context;
    ArrayList<User> userList;

    public RecyclerViewAdapter2(Context context, ArrayList<User> userList,
                                RecyclerViewInt recyclerViewInt){
        this.context = context;
        this.userList = userList;
        this.recyclerViewInt = recyclerViewInt;
    }

    @Override
    public int getItemViewType(int position) {
        String cat = userList.get(position).getName();
        if (cat.substring(cat.length()-1).equals("7")) {
            return 1;
        }
        return 0;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyler_view_row,parent,false);
            MyViewHolder holder = new MyViewHolder(view, recyclerViewInt);
            return holder;
        }
        else{
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyler_view_new,parent,false);
            BigImage holder = new BigImage(view, recyclerViewInt);
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //holder.name.setText(userList.get(position).getName());
        //holder.description.setText(userList.get(position).getDescription());

        if(holder.getItemViewType() == 0){
            MyViewHolder Mholder = (MyViewHolder) holder;
            Mholder.name.setText(userList.get(position).getName());
            Mholder.description.setText(userList.get(position).getDescription());
        }
        else{
            BigImage Bholder = (BigImage) holder;
            Bholder.name.setText(userList.get(position).getName());
            Bholder.description.setText(userList.get(position).getDescription());
        }
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        // grabbing views from layout file

        ImageView imageView;
        TextView name,description;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInt recyclerViewInt) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView2);
            name = itemView.findViewById(R.id.textView3);
            description = itemView.findViewById(R.id.textView4);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recyclerViewInt != null){
                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION){
                            recyclerViewInt.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public static class BigImage extends RecyclerView.ViewHolder{
        // grabbing views from layout file

        ImageView imageView;
        TextView name,description;
        ImageView bigimage;

        public BigImage(@NonNull View itemView, RecyclerViewInt recyclerViewInt) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView6);
            name = itemView.findViewById(R.id.textView6);
            description = itemView.findViewById(R.id.textView5);
            bigimage = itemView.findViewById(R.id.imageView4);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recyclerViewInt != null){
                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION){
                            recyclerViewInt.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
