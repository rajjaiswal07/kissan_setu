package com.example.kissan_setu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
                Intent intent1 = new Intent(MainActivity.this,profile.class);
                startActivity(intent1);
                return true;
            case R.id.settings:
                Toast.makeText(this, "Settings is selected", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(MainActivity.this,settings.class);
                startActivity(intent2);
                return true;
            case R.id.contact_us:
                Toast.makeText(this, "Contact Us is selected", Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(MainActivity.this,contact_us.class);
                startActivity(intent3);
                return true;
            case R.id.products:
                Toast.makeText(this, "Products is selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.vegetables:
                Toast.makeText(this, "Vegetables is selected", Toast.LENGTH_SHORT).show();
                Intent intent5 = new Intent(MainActivity.this,vegetables.class);
                startActivity(intent5);
                return true;
            case R.id.fruits:
                Toast.makeText(this, "Fruits is selected", Toast.LENGTH_SHORT).show();
                Intent intent6 = new Intent(MainActivity.this,fruits.class);
                startActivity(intent6);
                return true;
            case R.id.spices:
                Toast.makeText(this, "Spices is selected", Toast.LENGTH_SHORT).show();
                Intent intent7 = new Intent(MainActivity.this,spices.class);
                startActivity(intent7);
                return true;
            case R.id.grains:
                Toast.makeText(this, "Grains is selected", Toast.LENGTH_SHORT).show();
                Intent intent8 = new Intent(MainActivity.this,grains.class);
                startActivity(intent8);
                return true;
            case R.id.locations:
                Toast.makeText(this, "Locations is selected", Toast.LENGTH_SHORT).show();
                Intent intent9 = new Intent(MainActivity.this,locations.class);
                startActivity(intent9);
                return true;
            case R.id.status:
                Toast.makeText(this, "Status is selected", Toast.LENGTH_SHORT).show();
                Intent intent10 = new Intent(MainActivity.this,status.class);
                startActivity(intent10);
                return true;
            case R.id.about_us:
                Toast.makeText(this, "About Us is selected", Toast.LENGTH_SHORT).show();
                Intent intent11 = new Intent(MainActivity.this,about_us.class);
                startActivity(intent11);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}