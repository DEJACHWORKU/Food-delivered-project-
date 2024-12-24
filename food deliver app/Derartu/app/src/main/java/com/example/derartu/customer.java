package com.example.derartu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

public class customer extends AppCompatActivity {

    CardView clothingCard,library,library1, insviewbook2, insviewbook, insviewbook1;
    ImageView notfication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        clothingCard = findViewById(R.id.clothingCard);
        library1 =  findViewById(R.id.library1);
        library = findViewById(R.id.library);
        notfication = findViewById(R.id.notfication);
        insviewbook2 = findViewById(R.id.insviewbook2);
        insviewbook = findViewById(R.id.insviewbook);
        insviewbook1 = findViewById(R.id.insviewbook1);



        notfication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(customer.this,notification_recyclre.class);
                startActivity(intent);
            }
        });

        insviewbook2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(customer.this, MainActivity.class);
                startActivity(intent);
                preferences.clearData(customer.this);
                finish();
                Toast.makeText(customer.this, "Logout Customer", Toast.LENGTH_LONG).show();

            }
        });
        insviewbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(customer.this, "Coming soon", Toast.LENGTH_LONG).show();
            }
        });

        insviewbook1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(customer.this, "Coming soon", Toast.LENGTH_LONG).show();
            }
        });


        clothingCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(customer.this,localfoods.class);
                startActivity(intent);
            }
        });

        library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(customer.this,burgerpiza.class);
                startActivity(intent);
            }
        });
        library1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(customer.this,drinks.class);
                startActivity(intent);
            }
        });

        // Retrieve the username from the intent
        String name = getIntent().getStringExtra("name");

        // Display the greeting message with the username
        TextView welcomeTextView = findViewById(R.id.welcomeTextView);
        if (name != null) {
            welcomeTextView.setText("Hi, " + name);
        } else {
            welcomeTextView.setText("Welcome Customer");
        }

        // Add ImageSlider
        ArrayList<SlideModel> imageList = new ArrayList<>(); // Create image list

        // Load images from URLs or drawables and add them to the list
        // Add an image from the drawable resources
        imageList.add(new SlideModel(R.drawable.onboardo, ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.onboardoo, ScaleTypes.CENTER_CROP));

        ImageSlider imageSlider = findViewById(R.id.image_slider);
        imageSlider.setImageList(imageList);
    }
}
