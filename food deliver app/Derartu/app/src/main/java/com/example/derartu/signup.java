package com.example.derartu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.github.muddz.styleabletoast.StyleableToast;

public class signup extends AppCompatActivity {

    TextView txtSignIn;
    EditText edtSignUpFullName,edtSignUpEmail,edtSignUpMobile,edtSignUpPassword,edtSignUpConfirmPassword;
    Button btnSignUp;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        txtSignIn = findViewById(R.id.txtSignIn);
        edtSignUpFullName = findViewById(R.id.edtSignUpFullName);
        edtSignUpEmail = findViewById(R.id.edtSignUpEmail);
        edtSignUpMobile = findViewById(R.id.edtSignUpMobile);
        edtSignUpPassword = findViewById(R.id.edtSignUpPassword);
        edtSignUpConfirmPassword = findViewById(R.id.edtSignUpConfirmPassword);
        btnSignUp = findViewById(R.id.btnSignUp);

        txtSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signup.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");
                final String name = edtSignUpFullName.getText().toString();
                final String email = edtSignUpEmail.getText().toString();
                final String username = edtSignUpMobile.getText().toString();
                final String password = edtSignUpPassword.getText().toString();
                final String copass = edtSignUpConfirmPassword.getText().toString();

                // Check if username already exists in the database
                reference.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            // Username already exists, show toast and stop registration
                            StyleableToast.makeText(signup.this, "Username already exists in the database",R.style.errorToast, Toast.LENGTH_SHORT).show();
                        } else {
                            // Username doesn't exist, proceed with registration

                            // Check if password and confirm password are the same
                            if (!password.equals(copass)) {
                                Toast.makeText(signup.this, "Password and Confirm Password do not match", Toast.LENGTH_SHORT).show();
                                return; // Stop the registration process
                            }

                            // Check if the password meets the criteria
                            if (password.length() < 8 || !hasSpecialCharacter(password)) {
                                Toast.makeText(signup.this, "Password must be at least 8 characters long and contain a special character", Toast.LENGTH_SHORT).show();
                                return; // Stop the registration process
                            }

                            // Use the HelperClass constructor with default "student" value for campus
                            HelperClass helperClass = new HelperClass(name, email, username, password, copass);
                            reference.child(username).setValue(helperClass);

                            Toast.makeText(signup.this, "Your account signup successfully!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(signup.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Handle the error if needed
                    }
                });
            }
        });
    }

    // Check if the password contains at least one special character
    private boolean hasSpecialCharacter(String password) {
        String specialChars = "~`!@#$%^&*()-_=+[{]}\\|;:'\",<.>/?";
        for (char c : specialChars.toCharArray()) {
            if (password.contains(String.valueOf(c))) {
                return true;
            }
        }
        Toast.makeText(signup.this, "Password must contain at least one special character", Toast.LENGTH_SHORT).show();
        return false;
    }
}
