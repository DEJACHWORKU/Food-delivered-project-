package com.example.derartu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class listavalible extends AppCompatActivity {

    FloatingActionButton fab;
    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    List<LocalFoodItem> dataList;
    MyAdapter2 adapter;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listavalible);

        recyclerView = findViewById(R.id.recyclerView);
        fab = findViewById(R.id.fab);
        searchView = findViewById(R.id.search);
        searchView.clearFocus();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(listavalible.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder = new AlertDialog.Builder(listavalible.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        dataList = new ArrayList<>();

        adapter = new MyAdapter2(listavalible.this, dataList);
        recyclerView.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("foods");

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                // Get the type of food (drinks, intfoods, localfoods)
                String foodType = dataSnapshot.getKey();

                // Iterate through the children under the specific food type
                for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {
                    LocalFoodItem dataClass = itemSnapshot.getValue(LocalFoodItem.class);
                    dataClass.setKey(itemSnapshot.getKey());

                    // Retrieve the image from Firebase Realtime Database
                    if (itemSnapshot.child("image").exists()) {
                        String imageString = itemSnapshot.child("image").getValue(String.class);
                        dataClass.setImage(imageString);

                        // Decoding the Base64 image string
                        byte[] imageBytes = Base64.decode(imageString, Base64.DEFAULT);
                        Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                        dataClass.setBitmap(bitmap);
                    }

                    dataList.add(dataClass);
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                // Handle changes in child nodes
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                // Handle removal of child nodes
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                // Handle when a child node changes position
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors in database operations
            }
        };

        databaseReference.addChildEventListener(childEventListener);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return true;
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(listavalible.this, hoteladmin.class);
                startActivity(intent);
            }
        });
    }

    public void searchList(String text) {
        ArrayList<LocalFoodItem> searchList = new ArrayList<>();
        for (LocalFoodItem dataClass : dataList) {
            if (dataClass.getUsername().toLowerCase().contains(text.toLowerCase())) {
                searchList.add(dataClass);
            }
        }
        adapter.searchDataList(searchList);
    }
}
