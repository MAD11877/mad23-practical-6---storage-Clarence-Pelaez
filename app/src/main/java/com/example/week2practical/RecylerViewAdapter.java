package com.example.week2practical;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecylerViewAdapter extends RecyclerView.Adapter<RecylerViewAdapter.MyViewHolder> {
    private final RecyclerViewInt recyclerViewInt;
    Context context;
    ArrayList<User> userList;
    String placeName;

    public RecylerViewAdapter(Context context, ArrayList<User> userList, String placeName,
                              RecyclerViewInt recyclerViewInt){
        this.context = context;
        this.userList = userList;
        this.placeName = placeName;
        this.recyclerViewInt = recyclerViewInt;
    }

    @Override
    public int getItemViewType(int position) {
        if (placeName.substring(placeName.length() - 1).equals("7")) {
            return 1;
        }
        return 0;
    }

    @NonNull
    @Override
    public RecylerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Giving the look to rows
        if (this.getItemViewType(viewType) == 0) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.recyler_view_row, parent, false);
            return new RecylerViewAdapter.MyViewHolder(view, recyclerViewInt);
        }
        else {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.recyler_view_new, parent, false);
            return new RecylerViewAdapter.MyViewHolder(view, recyclerViewInt);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecylerViewAdapter.MyViewHolder holder, int position) {
        // assigning values to the views based on position of the recycler view

        holder.name.setText(userList.get(position).getName());
        holder.description.setText(userList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        // the number of items wanted to be displayed
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

        ImageView bigImage;

        public BigImage(@NonNull View itemView) {
            super(itemView);

            bigImage = itemView.findViewById(R.id.imageView4);
        }
    }
}
