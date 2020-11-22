package com.example.kissan_setu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class companyLogin extends AppCompatActivity {
    private TextView createCompanyNewAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_login);

        createCompanyNewAccount = (TextView) findViewById(R.id.company_new_account);

        //  Company New Account Redirection to Registration Activity
        createCompanyNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(companyLogin.this, companyRegistration.class);
                startActivity(intent);
            }
        });
    }
}