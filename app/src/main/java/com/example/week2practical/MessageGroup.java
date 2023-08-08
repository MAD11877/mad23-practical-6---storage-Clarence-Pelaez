package com.example.week2practical;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MessageGroup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_group);
        Button button1 = findViewById(R.id.button);
        Button button2 = findViewById(R.id.button3);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(1);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(2);
            }
        });

        Intent getIntent = getIntent();

    }

    private void switchFragment(int intWhich){
        Fragment fragment = null;


        switch (intWhich){
            case 1:
            {
                fragment = new FirstFragment();
                break;
            }
            case 2:
            {
                fragment = new Fragment2();
                break;
            }

        }

        Bundle args = new Bundle();
        args.putString("param1","Passed Data");

        fragment.setArguments(args);
        FragmentTransaction fragmentTransition = getSupportFragmentManager().beginTransaction();
        fragmentTransition.replace(R.id.FrameLayout, fragment);
        fragmentTransition.addToBackStack(null);
        fragmentTransition.commit();
    }
}