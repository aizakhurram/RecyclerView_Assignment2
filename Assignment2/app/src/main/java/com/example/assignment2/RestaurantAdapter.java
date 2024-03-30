package com.example.assignment2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment2.R;
import com.example.assignment2.Restaurant;

import java.util.ArrayList;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {

    ArrayList<Restaurant> restaurants;
    private ArrayList<Restaurant> filteredList;
    public RestaurantAdapter(ArrayList<Restaurant> list)
    {
        restaurants = list;
        filteredList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.activity_single_item, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Restaurant restaurant = filteredList.get(position);
        holder.tvContactName.setText(restaurant.getName());
        holder.tvPhone.setText(restaurant.getPhone());
        holder.tvLocation.setText(restaurant.getLocation());
        holder.tvDescription.setText(restaurant.getDescription());
        holder.tvRating.setText(restaurant.getRating());
    }
    public void filterList(ArrayList<Restaurant> filteredList) {
        this.filteredList.clear();
        this.filteredList.addAll(filteredList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return this.filteredList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvContactName, tvPhone, tvLocation, tvDescription, tvRating;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvContactName = itemView.findViewById(R.id.tvName);
            tvPhone = itemView.findViewById(R.id.tvPhone);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvRating = itemView.findViewById(R.id.tvRating);


        }
    }
}