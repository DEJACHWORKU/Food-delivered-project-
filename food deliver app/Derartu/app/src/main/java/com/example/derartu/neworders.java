// neworders.java
package com.example.derartu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class neworders extends AppCompatActivity {

    RecyclerView recyclerView2;
    MyAdapter3 adapter;
    DatabaseReference databaseReference;
    List<LocalFoodItem2> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neworders);

        recyclerView2 = findViewById(R.id.recyclerView);
        dataList = new ArrayList<>();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(neworders.this, 1);
        recyclerView2.setLayoutManager(gridLayoutManager);

        adapter = new MyAdapter3(neworders.this, dataList);
        recyclerView2.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("order");

        AlertDialog.Builder builder = new AlertDialog.Builder(neworders.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        // Use ValueEventListener to fetch data once
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataList.clear(); // Clear existing data list before adding new data
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    LocalFoodItem2 item = snapshot.getValue(LocalFoodItem2.class);
                    dataList.add(item);
                }
                adapter.notifyDataSetChanged();
                dialog.dismiss();
                Log.d("neworders", "DataList size: " + dataList.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                dialog.dismiss();
                Log.e("neworders", "Error fetching data: " + databaseError.getMessage());
            }
        });
    }
}
