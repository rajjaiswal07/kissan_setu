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

import java.util.HashMap;

public class companyRegistration extends AppCompatActivity {

    private Button registerCompanyBtn;
    private EditText companyName, companyDomain, companyRegisteredId, companyContactNumber, companyAddress, companyPassword;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_registration);

        registerCompanyBtn = (Button) findViewById(R.id.register_company_btn);
        companyName = (EditText) findViewById(R.id.register_company_name);
        companyDomain = (EditText) findViewById(R.id.register_company_domain);
        companyRegisteredId = (EditText) findViewById(R.id.register_company_registered_id);
        companyContactNumber = (EditText) findViewById(R.id.register_company_contact_number);
        companyAddress = (EditText) findViewById(R.id.register_company_address);
        companyPassword = (EditText) findViewById(R.id.register_company_password);
        loadingBar = new ProgressDialog(this);

        registerCompanyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateCompanyAccount();
            }
        });

    }

    private void CreateCompanyAccount() {
        String cmpName = companyName.getText().toString();
        String cmpDomain = companyDomain.getText().toString();
        String cmpRegisteredId = companyRegisteredId.getText().toString();
        String cmpPhone = companyContactNumber.getText().toString();
        String cmpAddress = companyAddress.getText().toString();
        String cmpPassword = companyPassword.getText().toString();

        if(TextUtils.isEmpty(cmpName)) {
            Toast.makeText(this, "Please write your company name", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(cmpPhone)) {
            Toast.makeText(this, "Please write your phone number", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(cmpDomain)) {
            Toast.makeText(this, "Please write your company domain", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(cmpRegisteredId)) {
            Toast.makeText(this, "Please write your registered Id", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(cmpAddress)) {
            Toast.makeText(this, "Please write your address", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(cmpPassword)) {
            Toast.makeText(this, "Please write your password", Toast.LENGTH_SHORT).show();
        }
        else {

            loadingBar.setTitle("Create Account");
            loadingBar.setMessage("Please wait, while we are checking the credentials");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            ValidateCompanyRegisteredID(cmpName, cmpPhone, cmpDomain, cmpRegisteredId, cmpAddress, cmpPassword);

        }

    }

    private void ValidateCompanyRegisteredID(String cmpName, String cmpPhone, String cmpDomain, String cmpRegisteredId, String cmpAddress, String cmpPassword) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!(snapshot.child("Company").child(cmpRegisteredId).exists())) {
                    HashMap<String, Object> userDataMap = new HashMap<>();
                    userDataMap.put("name", cmpName);
                    userDataMap.put("phone", cmpPhone);
                    userDataMap.put("domain", cmpDomain);
                    userDataMap.put("registeredID", cmpRegisteredId);
                    userDataMap.put("address", cmpAddress);
                    userDataMap.put("password", cmpPassword);
                    RootRef.child("Company").child(cmpRegisteredId).updateChildren(userDataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()) {
                                        Toast.makeText(companyRegistration.this, "Congratulations, Registration Successful..!", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();
                                        Intent intent = new Intent(companyRegistration.this, companyLogin.class);
                                        startActivity(intent);
                                    } else {
                                        loadingBar.dismiss();
                                        Toast.makeText(companyRegistration.this, "Network Error: Please try again later..!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    Toast.makeText(companyRegistration.this, "This" + cmpRegisteredId + " already exists", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(companyRegistration.this, "Please try using another registered ID..!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(companyRegistration.this, splashScreen.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}