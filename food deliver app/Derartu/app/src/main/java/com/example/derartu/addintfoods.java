package com.example.derartu;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class addintfoods extends AppCompatActivity {

    EditText studname, studidno, studcamp, studpass;
    Button saveButton;
    FirebaseDatabase database;
    DatabaseReference reference;
    private String encodedImage;
    private String encodedImage1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addintfoods);
        studname = findViewById(R.id.studname);
        studidno = findViewById(R.id.studidno);
        studcamp = findViewById(R.id.studcamp);
        studpass = findViewById(R.id.studpass);


        saveButton = findViewById(R.id.saveButton);

        CircleImageView circleImageView1=findViewById(R.id.qrimg);
        CircleImageView circleImageView=findViewById(R.id.imageprofile);
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                pickImage.launch(intent);
            }
        });

        circleImageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                pickImage1.launch(intent);
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("foods");

                String name = studname.getText().toString();
                String username = studidno.getText().toString();
                String password = studpass.getText().toString();
                String campus = studcamp.getText().toString();
                String image = encodedImage1;
                String qrimage = encodedImage;

                // Create a new instance of LocalFoodItem
                LocalFoodItem localFoodItem = new LocalFoodItem(name, username, password, campus, image, qrimage);

                // Use push() to generate a unique key for each entry
                String key = reference.child("food").push().getKey();
                reference.child("intfoods").child(key).setValue(localFoodItem);

                Toast.makeText(addintfoods.this, "Your food added successfully!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(addintfoods.this, hoteladmin.class);
                startActivity(intent);
            }
        });
    }
    private String encodedImage(Bitmap bitmap){
        int previewWidth= 150;
        int previewHight = bitmap.getHeight() * previewWidth / bitmap.getWidth();
        Bitmap previewBitmap = Bitmap.createScaledBitmap(bitmap,previewWidth,previewHight,false);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        previewBitmap.compress(Bitmap.CompressFormat.JPEG,50,byteArrayOutputStream);
        byte[] bytes =byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(bytes,Base64.DEFAULT);

    }

    private final ActivityResultLauncher<Intent> pickImage = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if(result.getResultCode() == RESULT_OK){
            if (result.getData() != null){
                Uri imageUri = result.getData().getData();
                try {
                    InputStream inputStream= getContentResolver().openInputStream(imageUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                    CircleImageView circleImageView=findViewById(R.id.imageprofile);
                    circleImageView.setImageBitmap(bitmap);
                    encodedImage1=encodedImage(bitmap);
                }catch (FileNotFoundException e){
                    e.printStackTrace();
                }
            }
        }
    });
    private final ActivityResultLauncher<Intent> pickImage1 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if(result.getResultCode() == RESULT_OK){
            if (result.getData() != null){
                Uri imageUri = result.getData().getData();
                try {
                    InputStream inputStream= getContentResolver().openInputStream(imageUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);


                    CircleImageView circleImageView1=findViewById(R.id.qrimg);
                    circleImageView1.setImageBitmap(bitmap);
                    encodedImage=encodedImage(bitmap);
                }catch (FileNotFoundException e){
                    e.printStackTrace();
                }
            }
        }
    });






}
