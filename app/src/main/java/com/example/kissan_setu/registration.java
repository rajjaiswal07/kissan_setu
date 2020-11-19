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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rey.material.drawable.CircularProgressDrawable;

import java.util.HashMap;

public class registration extends AppCompatActivity {

    private Button registerBtn;
    private EditText fullName, phoneNumber, adharNumber, registerPassword, registerAddress;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        registerBtn = (Button) findViewById(R.id.register_btn);
        fullName = (EditText) findViewById(R.id.register_full_name_input);
        phoneNumber = (EditText) findViewById(R.id.register_phone_number_input);
        adharNumber = (EditText) findViewById(R.id.register_adhar_number_input);
        registerPassword = (EditText) findViewById(R.id.register_password_input);
        registerAddress = (EditText) findViewById(R.id.register_address_input);
        loadingBar = new ProgressDialog(this);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateAccount();
            }
        });

    }

    private void CreateAccount() {
        String name = fullName.getText().toString();
        String phone = phoneNumber.getText().toString();
        String adhar = adharNumber.getText().toString();
        String password = registerPassword.getText().toString();
        String address = registerAddress.getText().toString();

        if(TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Please write your name", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "Please write your phone number", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(adhar)) {
            Toast.makeText(this, "Please write your adhar number", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please write your password", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(address)) {
            Toast.makeText(this, "Please write your address", Toast.LENGTH_SHORT).show();
        } else {

            loadingBar.setTitle("Create Account");
            loadingBar.setMessage("Please wait, while we are cheking the credentials");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            ValidateAdharNumber(name, phone, adhar, password, address);

        }

    }

    private void ValidateAdharNumber(String name, String phone, String adhar, String password, String address) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!(snapshot.child("Users").child(adhar).exists())) {
                    HashMap<String, Object> userDataMap = new HashMap<>();
                    userDataMap.put("name", name);
                    userDataMap.put("phone", phone);
                    userDataMap.put("adhar", adhar);
                    userDataMap.put("password", password);
                    userDataMap.put("address", address);
                    RootRef.child("Users").child(adhar).updateChildren(userDataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()) {
                                        Toast.makeText(registration.this, "Congratulations, Registration Successful..!", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();
                                        Intent intent = new Intent(registration.this, Login.class);
                                        startActivity(intent);
                                    } else {
                                        loadingBar.dismiss();
                                        Toast.makeText(registration.this, "Network Error: Please try again later..!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    Toast.makeText(registration.this, "This" + adhar + " already exists", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(registration.this, "Please try using another adhar number..!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(registration.this, splashScreen.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}