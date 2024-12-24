package com.example.derartu;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.checkerframework.checker.nullness.qual.NonNull;

public class DetailActivity extends AppCompatActivity {

    TextView detailDesc, detailTitle, detailLang;
    FloatingActionButton deleteButton, editButton;
    String key = "";
    String imageUrl = "";
    ImageView detailImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail2);

        detailDesc = findViewById(R.id.detailDesc);
        detailTitle = findViewById(R.id.detailTitle);
        detailLang = findViewById(R.id.detailLang);
        deleteButton = findViewById(R.id.deleteButton);
        editButton = findViewById(R.id.editButton);
        detailImage = findViewById(R.id.detailImage);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            detailDesc.setText(bundle.getString("password"));
            detailTitle.setText(bundle.getString("name"));
            detailLang.setText(bundle.getString("username"));
            key = bundle.getString("Key");
            imageUrl = bundle.getString("image");
            byte[] bytes = android.util.Base64.decode(imageUrl, android.util.Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            detailImage.setImageBitmap(bitmap);
        }

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (key != null && !key.isEmpty()) {
                    final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("foods").child(key);
                    reference.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            // Delete successful
                            Toast.makeText(DetailActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), addlocalfoods.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Delete failed
                            Toast.makeText(DetailActivity.this, "Failed to delete item", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    // Key is null or empty, show error message
                    Toast.makeText(DetailActivity.this, "Invalid key", Toast.LENGTH_SHORT).show();
                }
            }
        });


        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this, addlocalfoods.class)
                        .putExtra("Title", detailTitle.getText().toString())
                        .putExtra("Description", detailDesc.getText().toString())
                        .putExtra("Language", detailLang.getText().toString())
                        .putExtra("image", key)
                        .putExtra("qrimage", imageUrl);
                startActivity(intent);
            }
        });
    }
}
