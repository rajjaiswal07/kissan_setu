package com.example.kissan_setu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.kissan_setu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile:
                Toast.makeText(this, "Profile is selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.settings:
                Toast.makeText(this, "Settings is selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.contact_us:
                Toast.makeText(this, "Contact Us is selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.products:
                Toast.makeText(this, "Products is selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.vegetables:
                Toast.makeText(this, "Vegetables is selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.fruits:
                Toast.makeText(this, "Fruits is selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.spices:
                Toast.makeText(this, "Spices is selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.grains:
                Toast.makeText(this, "Grains is selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.locations:
                Toast.makeText(this, "Locations is selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.status:
                Toast.makeText(this, "Status is selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.about_us:
                Toast.makeText(this, "About Us is selected", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}