package com.example.derartu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.derartu.databinding.ActivityAdddnotficatBinding;


public class adddnotficat extends AppCompatActivity {

    ActivityAdddnotficatBinding binding;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddnotficat);
        binding=ActivityAdddnotficatBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());



        binding.btnninsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                databaseReference= FirebaseDatabase.getInstance().getReference("notification");

                addnot not=new addnot(binding.neditTitle.getText().toString(),binding.neditdescription.getText().toString());

                databaseReference.child(databaseReference.push().getKey()).setValue(not)
                        .addOnSuccessListener(suc->{
                            Toast.makeText(adddnotficat.this,"inserted",Toast.LENGTH_LONG).show();
                        }).addOnFailureListener(er->{
                            Toast.makeText(adddnotficat.this,"not inserted"+er.getMessage(),Toast.LENGTH_LONG).show();
                        });
            }
        });
    }
}