package com.example.derartu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class hoteladmin extends AppCompatActivity {

    ImageView amazonImage;
    ImageButton postnotfication;
    CardView studentcard, logoutcard, system_admin, security, admin_staff, instructor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoteladmin);

        studentcard = findViewById(R.id.studentcard);
        logoutcard = findViewById(R.id.logoutcard);
        system_admin = findViewById(R.id.system_admin);
        security = findViewById(R.id.security);
        admin_staff = findViewById(R.id.admin_staff);
        instructor = findViewById(R.id.instructor);
        postnotfication=findViewById(R.id.postnotfication);
        amazonImage=findViewById(R.id.amazonImage);

        postnotfication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(hoteladmin.this,adddnotficat.class);
                startActivity(intent);
            }
        });

        studentcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(hoteladmin.this, addlocalfoods.class);
                startActivity(intent);
            }
        });
        logoutcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(hoteladmin.this, MainActivity.class);
                startActivity(intent);
                preferences.clearData(hoteladmin.this);
                finish();
                Toast.makeText(hoteladmin.this, "Logout System Admin", Toast.LENGTH_LONG).show();;
            }
        });
        system_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(hoteladmin.this, addnewdrink.class);
                startActivity(intent);
            }
        });
        security.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(hoteladmin.this, addintfoods.class);
                startActivity(intent);
            }
        });
        admin_staff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(hoteladmin.this, neworders.class);
                startActivity(intent);
            }
        });
        instructor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(hoteladmin.this, listavalible.class);
                startActivity(intent);
            }
        });
    }
}
