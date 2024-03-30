package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity {
    Button btnBack, btnRegister;
    EditText etName, etLocation, etPhone, etDescription, etRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        init();

       btnBack.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(Registration.this, MainActivity.class);
               startActivity(intent);
               finish();
           }
       });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve data from EditText fields
                String name = etName.getText().toString();
                String location = etLocation.getText().toString();
                String phone = etPhone.getText().toString();
                String description = etDescription.getText().toString();
                String rating = etRating.getText().toString();

                // Save data to SharedPreferences
                saveDataToSharedPreferences(name, location, phone, description, rating);

                Toast.makeText(Registration.this, "New restaurant added!", Toast.LENGTH_SHORT).show();

                // Clear EditText fields for entering another restaurant
                etName.setText("");
                etLocation.setText("");
                etPhone.setText("");
                etDescription.setText("");
                etRating.setText("");
            }
        });
    }

    private void init() {
        btnRegister =findViewById(R.id.btnRegister);
        btnBack =findViewById(R.id.btnBack);
        etName = findViewById(R.id.etName);
        etLocation = findViewById(R.id.etLocation);
        etPhone = findViewById(R.id.etPhone);
        etDescription = findViewById(R.id.etDescription);
        etRating = findViewById(R.id.etRating);
    }

    private void saveDataToSharedPreferences(String name, String location, String phone, String description, String rating) {
        SharedPreferences sharedPreferences = getSharedPreferences("restaurant_data", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Get the current count of records
        int count = sharedPreferences.getInt("count", 0);

        // Append the new record with a unique key (using the count as the key)
        editor.putString("name_" + count, name);
        editor.putString("location_" + count, location);
        editor.putString("phone_" + count, phone);
        editor.putString("description_" + count, description);
        editor.putString("rating_" + count, rating);

        // Increment the count
        editor.putInt("count", count + 1);

        editor.apply();
    }
}
