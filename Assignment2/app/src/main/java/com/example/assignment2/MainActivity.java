package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.assignment2.Restaurant;
import com.example.assignment2.RestaurantAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvRestaurants;
    RestaurantAdapter myAdapter;
    ArrayList<Restaurant> list;
    Button btnAddNewRestaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        rvRestaurants.setHasFixedSize(true);
        myAdapter = new RestaurantAdapter(list);

        rvRestaurants.setLayoutManager(new LinearLayoutManager(this));
        //rvChats.setLayoutManager(new GridLayoutManager(this,4));
        rvRestaurants.setAdapter(myAdapter);

        btnAddNewRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, Registeration.class);
                startActivity(intent);
                finish();
                myAdapter.notifyDataSetChanged();
            }
        });

    }

    private void init()
    {
        list = new ArrayList<>();
        list.add(new Restaurant("Ayesha", "20 ka load kara do", "01:20 PM", "3", "5"));
        list.add(new Restaurant("Ayesha", "20 ka load kara do", "01:20 PM", "3", "5"));
        list.add(new Restaurant("Ayesha", "20 ka load kara do", "01:20 PM", "3", "5"));
        list.add(new Restaurant("Ayesha", "20 ka load kara do", "01:20 PM", "3", "5"));
        list.add(new Restaurant("Ayesha", "20 ka load kara do", "01:20 PM", "3", "5"));
        list.add(new Restaurant("Ayesha", "20 ka load kara do", "01:20 PM", "3", "5"));
        rvRestaurants = findViewById(R.id.rvRestaurants);
        btnAddNewRestaurant = findViewById(R.id.btnAddNewRestaurant);






    }
}