package com.example.kissan_setu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.kissan_setu.Model.Users;
import com.example.kissan_setu.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class splashScreen extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 4000;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        loadingBar = new ProgressDialog(this);
        Paper.init(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String UserAdharKey = Paper.book().read(Prevalent.UserAdharKey);
                String UserPasswordKey = Paper.book().read(Prevalent.UserPasswordKey);
                if(UserAdharKey != null && UserPasswordKey != null) {
                    if(!TextUtils.isEmpty(UserAdharKey) && !TextUtils.isEmpty(UserPasswordKey)) {
                        AllowAccess(UserAdharKey, UserPasswordKey);
                        loadingBar.setTitle("Already Logged in.");
//                        loadingBar.setMessage("Please wait....");
                        loadingBar.setCanceledOnTouchOutside(false);
                        loadingBar.show();
                    }
                } else {
                    Intent homeIntent = new Intent(splashScreen.this, Login.class);
                    startActivity(homeIntent);
                    finish();
                }
            }
        }, SPLASH_TIME_OUT);
    }

    private void AllowAccess(String adharNumber, String password) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child("Users").child(adharNumber).exists()) {
                    Users usersData = snapshot.child("Users").child(adharNumber).getValue(Users.class);
                    if(usersData.getAdhar().equals(adharNumber)) {
                        if(usersData.getPassword().equals(password)) {
//                            Toast.makeText(splashScreen.this, "Logged in Successful..", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();
                            Intent intent = new Intent(splashScreen.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            loadingBar.dismiss();
                            Toast.makeText(splashScreen.this, "Password is incorrect.", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(splashScreen.this, "Account with this " + adharNumber + " do not exists.", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}