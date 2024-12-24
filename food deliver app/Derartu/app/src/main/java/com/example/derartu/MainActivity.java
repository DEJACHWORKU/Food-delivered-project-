package com.example.derartu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView txtSignUp;
    EditText edtSignInEmail;
    EditText edtSignInPassword;
    Button btnSignIn;
    Switch active;
    String loggedInUserId; // Variable to store the logged-in user's ID
    public static final String[] languages = {"Language", "English", "Amharic", "Oromo"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtSignUp = findViewById(R.id.txtSignUp);
        edtSignInEmail = findViewById(R.id.edtSignInEmail);
        edtSignInPassword = findViewById(R.id.edtSignInPassword);
        btnSignIn = findViewById(R.id.btnSignIn);
        active = findViewById(R.id.active);
        Spinner spinnerLanguages = findViewById(R.id.spinner_languages);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, languages);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLanguages.setAdapter(adapter);
        spinnerLanguages.setSelection(0);
        spinnerLanguages.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedLang = adapterView.getItemAtPosition(i).toString();
                if (!selectedLang.equals("Language")) {
                    setLocale(MainActivity.this, getLanguageCode(selectedLang));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, signup.class);
                startActivity(intent);
                finish();
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String input1 = edtSignInEmail.getText().toString();
                        String input2 = edtSignInPassword.getText().toString();

                        if (dataSnapshot.child(input1).exists()) {
                            if (dataSnapshot.child(input1).child("password").getValue(String.class).equals(input2)) {
                                loggedInUserId = input1; // This assumes the email is used as the user ID
                                // Start the DetailOrder activity and pass the logged-in user's ID
                                Intent intent = new Intent(MainActivity.this, DetailOrder.class);
                                intent.putExtra("loggedInUserId", loggedInUserId);
                                startActivity(intent);
                                finish();
                                String name = dataSnapshot.child(input1).child("name").getValue(String.class);

                                // Remove the unnecessary Object preferences;
                                if (active.isChecked()) {  // Assuming 'active' is a Switch
                                    if (dataSnapshot.child(input1).child("campus").getValue(String.class).equals("admin")) {
                                        preferences.setDataLogin(MainActivity.this, true);
                                        preferences.setDataAs(MainActivity.this, "admin");
                                        startActivity(new Intent(MainActivity.this, hoteladmin.class));
                                        finish();
                                    } else if (dataSnapshot.child(input1).child("campus").getValue(String.class).equals("student")) {
                                        preferences.setDataLogin(MainActivity.this, true);
                                        preferences.setDataAs(MainActivity.this, "users");

                                        // Pass the username to the customer activity
                                        Intent customerIntent = new Intent(MainActivity.this, customer.class);
                                        customerIntent.putExtra("name", name);
                                        startActivity(customerIntent);

                                        finish();
                                    }
                                    else if (dataSnapshot.child(input1).child("campus").getValue(String.class).equals("delivery")) {
                                        preferences.setDataLogin(MainActivity.this, true);
                                        preferences.setDataAs(MainActivity.this, "delivery");
                                        startActivity(new Intent(MainActivity.this, deliveryboy.class));
                                        finish();
                                    }
                                } else {
                                    if (dataSnapshot.child(input1).child("campus").getValue(String.class).equals("admin")) {
                                        preferences.setDataLogin(MainActivity.this, false);
                                        startActivity(new Intent(MainActivity.this, hoteladmin.class));
                                        finish();
                                    } else if (dataSnapshot.child(input1).child("campus").getValue(String.class).equals("student")) {
                                        preferences.setDataLogin(MainActivity.this, false);
                                        startActivity(new Intent(MainActivity.this, customer.class));

                                        // Pass the username to the customer activity
                                        Intent customerIntent = new Intent(MainActivity.this, customer.class);
                                        customerIntent.putExtra("name", name);
                                        startActivity(customerIntent);
                                        finish();
                                    }
                                    else if (dataSnapshot.child(input1).child("campus").getValue(String.class).equals("delivery")) {
                                        preferences.setDataLogin(MainActivity.this, false);
                                        startActivity(new Intent(MainActivity.this, deliveryboy.class));
                                        finish();
                                    }
                                }
                            } else {
                                Toast.makeText(MainActivity.this, "No Role Please Contact us", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Username and Password Error", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Handle database error if needed
                    }
                });
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (preferences.getDataLogin(this)) {
            if (preferences.getDataAs(this).equals("admin")) {
                startActivity(new Intent(this, hoteladmin.class));
                finish();
            } else if (preferences.getDataAs(this).equals("student")) {
                startActivity(new Intent(this, customer.class));
                finish();
            } else if (preferences.getDataAs(this).equals("delivery")) {
                startActivity(new Intent(this, deliveryboy.class));
                finish();
            }
        }
    }

    public void setLocale(Activity activity, String langCode) {
        Locale locale = new Locale(langCode);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        activity.getResources().updateConfiguration(config, activity.getResources().getDisplayMetrics());
        recreate(); // Recreate the activity to apply language changes
    }

    public String getLanguageCode(String language) {
        switch (language) {
            case "English":
                return "en";
            case "Amharic":
                return "am";
            case "Oromo":
                return "om";
            default:
                return "en";
        }
    }
}
