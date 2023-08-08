package com.example.week2practical;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class ListActivity extends AppCompatActivity implements RecyclerViewInt{

    String placeName;
    String placeDesc;
    MyDBHandler dbHandler;
    ArrayList<User> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        dbHandler = new MyDBHandler(ListActivity.this);

        RecyclerView recyclerView = findViewById(R.id.RecyList);
        getThings();
        userList = dbHandler.getUsers();

        RecyclerView.Adapter adapter = new RecyclerViewAdapter2(this,userList,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume(){
        super.onResume();


        /*ImageView imageView = findViewById(R.id.imageViewclick);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            ProfileAlert();
            }
        });*/
    }

    private void getThings(){
        for(int i = 0;i < 20;i++) {
            placeName = "Name: " + RandomInt();
            placeDesc = "Description: " + RandomInt();
            User user = new User(placeName, placeDesc);
            dbHandler.addUser(user);
        }
    }

    /*private void ProfileAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Profile");
        builder.setMessage("MADness");
        builder.setCancelable(false);
        builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int number = RandomInt();
                Intent intent = new Intent(ListActivity.this,MainActivity.class);
                intent.putExtra("123", number);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            finish();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }*/

    private int RandomInt(){
        Random ran = new Random();
        int myNum = ran.nextInt(999999999);
        return myNum;
    };

    @Override
    public void onItemClick(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Profile");
        builder.setMessage(userList.get(position).getName());
        builder.setCancelable(false);

        builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int number = RandomInt();
                Intent intent = new Intent(ListActivity.this,MainActivity.class);

                intent.putExtra("Name", userList.get(position).getName());
                intent.putExtra("Description",userList.get(position).getDescription());
                intent.putExtra("Id",userList.get(position).getId());
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}