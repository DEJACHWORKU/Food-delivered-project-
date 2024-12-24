package com.example.derartu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class deliveryboy extends AppCompatActivity {

    CardView insviewbook1, delivorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliveryboy);

        insviewbook1 = findViewById(R.id.insviewbook1);
        delivorder = findViewById(R.id.delivorder);


        insviewbook1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(deliveryboy.this, MainActivity.class);
                startActivity(intent);
                preferences.clearData(deliveryboy.this);
                finish();
                Toast.makeText(deliveryboy.this, "Logout Delivery Boy", Toast.LENGTH_LONG).show();

            }
        });
        delivorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(deliveryboy.this, neworders.class);
                startActivity(intent);
            }
        });
    }
}
