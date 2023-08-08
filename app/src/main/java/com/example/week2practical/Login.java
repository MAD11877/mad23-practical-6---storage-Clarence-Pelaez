package com.example.week2practical;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    EditText username;
    EditText password;
    TextView error;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.editTextText);
        password = findViewById(R.id.editTextText2);
        button = findViewById(R.id.button4);
        error = findViewById(R.id.textView8);
        error.setVisibility(View.INVISIBLE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                database.getReference("Users").child("mad").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String tUser = snapshot.child("username").getValue().toString();
                        String tPass = snapshot.child("password").getValue().toString();
                        Log.v("Login",user);
                        Log.v("Login",pass);
                        if(tUser.equals(user) && tPass.equals(pass)){
                            Intent intent = new Intent(Login.this,ListActivity.class);
                            startActivity(intent);
                        }
                        else{
                            error.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}