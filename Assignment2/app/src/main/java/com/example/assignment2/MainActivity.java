package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvRestaurants;
    RestaurantAdapter myAdapter;
    ArrayList<Restaurant> list;
    Button btnAddNewRestaurant ,btnRegister, btnSearch;
    EditText etName, etLocation, etRating, etPhone, etDescription, etSearch;
    View register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        rvRestaurants.setHasFixedSize(true);

        // Initialize adapter with the original list
        myAdapter = new RestaurantAdapter(list);

        rvRestaurants.setLayoutManager(new LinearLayoutManager(this));
        rvRestaurants.setAdapter(myAdapter);

        loadDataFromSharedPreferences();

        btnAddNewRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Registration.class);
                startActivity(intent);
                finish();
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = etSearch.getText().toString().trim();
                Log.d("MainActivity", "Search query: " + query);
                filterList(query);
            }
        });
    }
    private void init()
    {
        list = new ArrayList<>();
        list.add(new Restaurant("Lily By deja", "MM Alam Road Lahore", "123 456 789", "An italian restaurant", "5"));
        list.add(new Restaurant("Howdy", "MM Alam/ Johar town", "84993284726", "A Fast Food chain", "4"));
        list.add(new Restaurant("Sollis", "Mall 1 MM Alam Road lahore", "723 456 223", "An Italian Pizza Place", "3"));
        list.add(new Restaurant("Urban Kitchen", "MM Alam Road Lahore", "123 456 789", "Every type of food in a single place", "5"));

        rvRestaurants = findViewById(R.id.rvRestaurants);
        btnAddNewRestaurant = findViewById(R.id.btnAddNewRestaurant);
        etSearch = findViewById(R.id.etSearch);

        btnSearch = findViewById(R.id.btnSearch);

    }
    private void filterList(String query) {
        ArrayList<Restaurant> filteredList = new ArrayList<>();
        for (Restaurant restaurant : list) {
            if (restaurant.getName().toLowerCase().contains(query.toLowerCase()) ||
                    restaurant.getLocation().toLowerCase().contains(query.toLowerCase()) ||
                    restaurant.getRating().equals(query)) {

                filteredList.add(restaurant);
            }
        }

        myAdapter.filterList(filteredList);
    }

    private void loadDataFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("restaurant_data", MODE_PRIVATE);
        int count = sharedPreferences.getInt("count", 0);
        for (int i = 0; i < count; i++) {
            String name = sharedPreferences.getString("name_" + i, "");
            String location = sharedPreferences.getString("location_" + i, "");
            String phone = sharedPreferences.getString("phone_" + i, "");
            String description = sharedPreferences.getString("description_" + i, "");
            String rating = sharedPreferences.getString("rating_" + i, "");

            if (!name.isEmpty() && !location.isEmpty() && !phone.isEmpty() && !description.isEmpty() && !rating.isEmpty()) {
                list.add(new Restaurant(name, location, phone, description, rating));
            }
        }
        myAdapter.notifyDataSetChanged();
    }


}