package com.example.kissan_setu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class contact_us extends AppCompatActivity {
    EditText etSubject, etMessage;
    Button btSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        setTitle("Contact Us");

//        etTo = findViewById(R.id.et_to);
        etSubject = findViewById(R.id.et_subject);
        etMessage = findViewById(R.id.et_message);
        btSend = findViewById(R.id.bt_send);

        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = "contact@kissansetu.com";
                String subject = etSubject.getText().toString().trim();
                String message = etMessage.getText().toString().trim();

                if(subject.isEmpty()) {
                    Toast.makeText(contact_us.this, "Please add Subject", Toast.LENGTH_SHORT).show();
                } else if(message.isEmpty()) {
                    Toast.makeText(contact_us.this, "Please add some Message", Toast.LENGTH_SHORT).show();
                } else {
                    String mail = "mailto:" + email +
                            "?&subject=" + Uri.encode(subject) +
                            "&body=" + Uri.encode(message);

                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse(mail));

                    try {
                        startActivity(Intent.createChooser(intent, "Send email..."));
                    } catch (Exception e) {
                        Toast.makeText(contact_us.this, "Exception: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}