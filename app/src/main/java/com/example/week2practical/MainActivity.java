package com.example.week2practical;

import static android.provider.MediaStore.MediaColumns.TITLE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final String TITLE = "Main Activity";
    MyDBHandler dbHandler;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v(TITLE, "On Create!");
        dbHandler = new MyDBHandler(MainActivity.this);

        Intent getIntent = getIntent();
        String name = getIntent.getStringExtra("Name");
        String desc = getIntent.getStringExtra("Description");
        int id = getIntent.getIntExtra("Id",1);

        User testUser1 = dbHandler.findUser(name);

        TextView editName = findViewById(R.id.textView);
        TextView editDesc = findViewById(R.id.textView2);
        editName.setText(name);
        editDesc.setText(desc);

        Button myButton = findViewById(R.id.followbutton);
        Button myButton2 = findViewById(R.id.button2);


        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!testUser1.isFollowed()) {
                    myButton.setText("Unfollow");
                    Toast.makeText(getApplicationContext(),"Followed",Toast.LENGTH_SHORT).show();
                    dbHandler.updateUser(name,1);
                    testUser1.setFollowed(true);
                } else if (testUser1.isFollowed()) {
                    myButton.setText("Follow");
                    Toast.makeText(getApplicationContext(),"Unfollowed",Toast.LENGTH_SHORT).show();
                    dbHandler.updateUser(name,0);
                    testUser1.setFollowed(false);
                }
            }
        });

        myButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goMessage = new Intent(MainActivity.this, MessageGroup.class);
                startActivity(goMessage);
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.v(TITLE, "On Start!");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.v(TITLE, "On Pause!");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.v(TITLE, "On Resume!");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.v(TITLE, "On Stop!");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.v(TITLE, "On Destroy!");
    }
}