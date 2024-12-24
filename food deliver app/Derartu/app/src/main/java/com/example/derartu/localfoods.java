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

public class localfoods extends AppCompatActivity {

    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    List<LocalFoodItem> dataList;
    MyAdapter adapter;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localfoods);

        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.search);
        searchView.clearFocus();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(localfoods.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder = new AlertDialog.Builder(localfoods.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        dataList = new ArrayList<>();

        adapter = new MyAdapter(localfoods.this, dataList);
        recyclerView.setAdapter(adapter);

        // Adjusting the reference to point to "localfoods" node under "foods"
        databaseReference = FirebaseDatabase.getInstance().getReference("foods").child("localfoods");

        // Adding ChildEventListener to listen for changes in the "localfoods" node
        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                LocalFoodItem dataClass = dataSnapshot.getValue(LocalFoodItem.class);
                dataClass.setKey(dataSnapshot.getKey());

                // Retrieve the image from Firebase Realtime Database
                if (dataSnapshot.child("image").exists()) {
                    String imageString = dataSnapshot.child("image").getValue(String.class);
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
