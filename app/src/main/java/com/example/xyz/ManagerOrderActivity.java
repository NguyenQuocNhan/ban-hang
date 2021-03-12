package com.example.xyz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.xyz.Adapter.OrderAdapter;
import com.example.xyz.Model.Order;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ManagerOrderActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    private OrderAdapter orderAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_order);

        recyclerView = findViewById(R.id.RecyclerView);
        //GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        //recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Order> options =
                new FirebaseRecyclerOptions.Builder<Order>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("Orders"), Order.class)
                        .build();

        orderAdapter = new OrderAdapter(options);
        orderAdapter.startListening();
        recyclerView.setAdapter(orderAdapter);
    }
}