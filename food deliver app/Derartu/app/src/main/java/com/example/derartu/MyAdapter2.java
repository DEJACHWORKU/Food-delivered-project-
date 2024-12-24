package com.example.derartu;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter2 extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    private List<LocalFoodItem> dataList;

    public MyAdapter2(Context context, List<LocalFoodItem> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        LocalFoodItem data = dataList.get(position);

        holder.recImage.setImageBitmap(data.getBitmap());
        holder.qrimag.setImageBitmap(getBitmapFromString(data.getQrimage()));
        holder.recTitle.setText(data.getName());
        holder.recDesc.setText(data.getPassword());
        holder.recid.setText(data.getCampus());
        holder.recemail.setText(data.getUsername());

        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("image", data.getImage());
                intent.putExtra("qrimage", data.getQrimage());
                intent.putExtra("username", data.getUsername());
                intent.putExtra("password", data.getPassword());
                intent.putExtra("campus", data.getCampus());
                intent.putExtra("Key", data.getKey());
                intent.putExtra("name", data.getName());
                context.startActivity(intent);
            }
        });
    }

    private Bitmap getBitmapFromString(String encodedString){
        if (encodedString != null) {
            byte[] decodedString = Base64.decode(encodedString, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        } else {
            // Handle the case where the encodedString is null
            return null;
        }
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void searchDataList(ArrayList<LocalFoodItem> searchList) {
        dataList = searchList;
        notifyDataSetChanged();
    }

    private Bitmap getUserImage(String encodedImage) {
        byte[] bytes = android.util.Base64.decode(encodedImage, android.util.Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}

class MyViewHolder extends RecyclerView.ViewHolder {

    ImageView recImage, qrimag;
    TextView recTitle, recDesc, recLang, recemail, recid;
    CardView recCard;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        qrimag = itemView.findViewById(R.id.qrimg);
        recImage = itemView.findViewById(R.id.recImage);
        recCard = itemView.findViewById(R.id.recCard);
        recDesc = itemView.findViewById(R.id.recDesc);
        recLang = itemView.findViewById(R.id.recLang);
        recTitle = itemView.findViewById(R.id.recTitle);
        recemail = itemView.findViewById(R.id.recemail);
        recid = itemView.findViewById(R.id.recid);
    }
}