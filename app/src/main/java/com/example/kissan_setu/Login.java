package com.example.kissan_setu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kissan_setu.Model.Users;
import com.example.kissan_setu.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rey.material.drawable.CircularProgressDrawable;
import com.rey.material.widget.CheckBox;

import io.paperdb.Paper;

public class Login extends AppCompatActivity {

    private TextView createNewAccount;
    private EditText loginAdharNumber, loginPassword;
    private Button loginButton;
    private ProgressDialog loadingBar;
    private String parentDbName = "Users";
    private CheckBox chkBoxRememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        chkBoxRememberMe = (CheckBox) findViewById(R.id.remember_me_chkb);
        Paper.init(this);

        //   Create Account Link
        createNewAccount = (TextView) findViewById(R.id.new_account);
        createNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, registration.class);
                startActivity(intent);
            }
        });

        // Login code
        loginButton = (Button) findViewById(R.id.login_btn);
        loginAdharNumber = (EditText) findViewById(R.id.login_adhaar_number_input);
        loginPassword = (EditText) findViewById(R.id.login_password_input);
        loadingBar = new ProgressDialog(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });

    }

    private void loginUser() {
        String adharNumber = loginAdharNumber.getText().toString();
        String password = loginPassword.getText().toString();
        if(TextUtils.isEmpty(adharNumber)) {
            Toast.makeText(this, "Please enter your adhar number", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show();
        } else {
            loadingBar.setTitle("Login Account");
            loadingBar.setMessage("Please wait, while we are cheking the credentials");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();
            
            AllowAccessToAccount(adharNumber, password);
        }
    }

    private void AllowAccessToAccount(String adharNumber, String password) {
        if(chkBoxRememberMe.isChecked()) {
            Paper.book().write(Prevalent.UserAdharKey, adharNumber);
            Paper.book().write(Prevalent.UserPasswordKey, password);
        }

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(parentDbName).child(adharNumber).exists()) {
                    Users usersData = snapshot.child(parentDbName).child(adharNumber).getValue(Users.class);
                    if(usersData.getAdhar().equals(adharNumber)) {
                        if(usersData.getPassword().equals(password)) {
                            Toast.makeText(Login.this, "Logged in Successful..", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();
                            Intent intent = new Intent(Login.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            loadingBar.dismiss();
                            Toast.makeText(Login.this, "Password is incorrect.", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(Login.this, "Account with this " + adharNumber + " do not exists.", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}