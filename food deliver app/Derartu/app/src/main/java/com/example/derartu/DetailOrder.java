package com.example.derartu;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailOrder extends AppCompatActivity {

    private static final int REQUEST_CODE_LOCATION_PERMISSION = 1;

    private FusedLocationProviderClient fusedLocationClient;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;

    private TextInputEditText currentMap;

    TextInputEditText edtSignUpFullName, edtSignUpMobile;
    TextView detailDesc, detailTitle, detailLang, numbers;
    Button decreaseButton, increaseButton, chapa;
    String key = "";
    String imageUrl = "";
    ImageView detailImage;
    int quantity = 1;
    double foodPrice = 0.0;
    double total=0;
    double originalPrice = 0.0;
    FirebaseDatabase database;
    DatabaseReference reference;
    String loggedInUserId; // Variable to store the logged-in user's ID

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_order);

        detailDesc = findViewById(R.id.detailDesc);
        detailTitle = findViewById(R.id.detailTitle);
        detailLang = findViewById(R.id.detailLang);
        detailImage = findViewById(R.id.detailImage);
        numbers = findViewById(R.id.numbers);
        decreaseButton = findViewById(R.id.button2);
        increaseButton = findViewById(R.id.button3);
        chapa = findViewById(R.id.chapa);
        edtSignUpFullName = findViewById(R.id.edtSignUpFullName);
        edtSignUpMobile = findViewById(R.id.edtSignUpMobile);


        currentMap = findViewById(R.id.currentmap);
        MaterialButton mapButton = findViewById(R.id.map3);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            detailDesc.setText(bundle.getString("campus"));
            foodPrice = Double.parseDouble(bundle.getString("campus")); // Assuming "campus" contains the food price
            originalPrice = foodPrice;
            updatePrice();
            detailTitle.setText(bundle.getString("name"));
            detailLang.setText(bundle.getString("username"));
            key = bundle.getString("Key");
            imageUrl = bundle.getString("image");
            try {
                byte[] bytes = android.util.Base64.decode(imageUrl, android.util.Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                detailImage.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        decreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity == 1) {


                }else {
                    quantity--;
                    foodPrice = foodPrice-originalPrice;

                    detailDesc.setText(String.format("ETB: %.2f", foodPrice));
                    numbers.setText(String.valueOf(quantity));
                }
            }
        });

        increaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity++;
                foodPrice = originalPrice*quantity;

                detailDesc.setText(String.format("ETB: %.2f", foodPrice));
                numbers.setText(String.valueOf(quantity));

            }
        });

        chapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("order"); // Reference to the "order" node

                String price = detailDesc.getText().toString();
                String foodName = detailTitle.getText().toString();
                String description = detailLang.getText().toString();
                String currentMapText = currentMap.getText().toString(); // Additional input
                String fullName = edtSignUpFullName.getText().toString();
                String mobile = edtSignUpMobile.getText().toString();

                // Create a new instance of Order with all the inputs
                order order = new order(price, foodName, description, fullName, mobile, currentMapText);

                // Push the order object to the database
                String key = reference.push().getKey();
                reference.child(key).setValue(order);

                Toast.makeText(DetailOrder.this, "Your food is in pending please pay!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(DetailOrder.this, finalcheckout.class);
                startActivity(intent);
            }
        });

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(DetailOrder.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(DetailOrder.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(DetailOrder.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_LOCATION_PERMISSION);
                    return;
                }
                getLocation();
            }
        });

        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(2000);

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                super.onLocationResult(locationResult);
                if (locationResult.getLastLocation() != null) {
                    Location location = locationResult.getLastLocation();
                    String latitude = String.valueOf(location.getLatitude());
                    String longitude = String.valueOf(location.getLongitude());
                    String address = "Latitude: " + latitude + ", Longitude: " + longitude;
                    currentMap.setText(address);
                }
            }
        };
    }

    private void updatePrice() {
          total = total + foodPrice;
        detailDesc.setText(String.format("ETB: %.2f", total));
        numbers.setText(String.valueOf(quantity));
    }

    private void getLocation() {
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_LOCATION_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLocation();
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (fusedLocationClient != null) {
            fusedLocationClient.removeLocationUpdates(locationCallback);
        }
    }
}
