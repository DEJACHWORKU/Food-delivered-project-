package com.example.derartu;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter3 extends RecyclerView.Adapter<MyAdapter3.MyViewHolder> {

    private Context context;
    private List<LocalFoodItem2> dataList;

    public MyAdapter3(Context context, List<LocalFoodItem2> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item2, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        LocalFoodItem2 data = dataList.get(position);

        holder.recTitle.setText(data.getFullname());
        holder.recDesc.setText(data.getFoodName());
        holder.recid.setText(data.getPrice());
        holder.recemail.setText(data.getDescription());
        holder.recLang.setText(data.getMobile());
        holder.etb.setText(data.getCurrentMap());
        // Set other views accordingly

        holder.etb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Extract latitude and longitude from the currentMap string
                String currentMap = data.getCurrentMap();
                String[] parts = currentMap.split(", "); // Split the string by ", " to get latitude and longitude separately
                double latitude = 0.0;
                double longitude = 0.0;

                // Ensure correct format and parse latitude and longitude
                if (parts.length == 2) {
                    try {
                        latitude = Double.parseDouble(parts[0].split(": ")[1].trim()); // Get latitude value
                        longitude = Double.parseDouble(parts[1].split(": ")[1].trim()); // Get longitude value
                    } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                        e.printStackTrace();
                        // Handle parsing errors
                        Toast.makeText(context, "Error parsing latitude and longitude", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Open Google Maps or perform any other action with latitude and longitude
                    // For example, you can create an Intent to open Google Maps
                    Uri gmmIntentUri = Uri.parse("geo:" + latitude + "," + longitude + "?q=" + latitude + "," + longitude + "(" + data.getFullname() + ")");
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    context.startActivity(mapIntent);
                } else {
                    // Handle incorrect format of the currentMap string
                    Toast.makeText(context, "Invalid format of location", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView recTitle, recDesc, recid, recemail, recLang, etb;
        View recCard;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            recTitle = itemView.findViewById(R.id.recTitle);
            recDesc = itemView.findViewById(R.id.recDesc);
            recid = itemView.findViewById(R.id.recid);
            recemail = itemView.findViewById(R.id.recemail);
            recLang = itemView.findViewById(R.id.recLang);
            etb = itemView.findViewById(R.id.etb);
            recCard = itemView.findViewById(R.id.recCard2);
        }
    }
}
