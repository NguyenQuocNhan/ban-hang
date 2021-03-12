package com.example.xyz.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.xyz.Model.CardShop;
import com.example.xyz.Model.Order;
import com.example.xyz.Model.Product;
import com.example.xyz.Model.User;
import com.example.xyz.R;
import com.example.xyz.UpdateManagerProductActivity;
import com.example.xyz.VNCharacterUtils;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ManagerProductAdapter extends FirebaseRecyclerAdapter<Product, ManagerProductAdapter.ViewHolder> {

    public ManagerProductAdapter(@NonNull FirebaseRecyclerOptions<Product> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Product model) {
        Glide.with(holder.imageProduct.getContext()).load(model.getImage()).into(holder.imageProduct);
        holder.nameProduct.setText(model.getName());
        holder.ratingBar.setRating(model.getRating());

        holder.buttonRemove.setOnClickListener(v -> {
            try {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Products").child(model.getID());
                databaseReference.removeValue();
            } catch (Exception ex) {

            }
        });

        holder.buttonUpdate.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(holder.imageProduct.getContext(), UpdateManagerProductActivity.class);
                intent.putExtra("productID", model.getID());
                holder.imageProduct.getContext().startActivity(intent);
            } catch (Exception e) {

            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_manager, parent, false));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageButton imageProduct;
        private TextView nameProduct;
        private Button buttonRemove, buttonUpdate;
        private RatingBar ratingBar;

        public ViewHolder(View itemView) {
            super(itemView);

            imageProduct = itemView.findViewById(R.id.imageProduct);
            nameProduct = itemView.findViewById(R.id.nameProduct);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            buttonRemove = itemView.findViewById(R.id.buttonRemove);
            buttonUpdate = itemView.findViewById(R.id.buttonUpdate);

        }
    }

}
