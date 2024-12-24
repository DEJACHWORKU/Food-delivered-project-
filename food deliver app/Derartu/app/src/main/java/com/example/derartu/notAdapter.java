package com.example.derartu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.List;

public class notAdapter extends RecyclerView.Adapter<notAdapter.ViewHolder>{

    private final Context context;
    private final List<Notmoadl> items;

    public notAdapter(List<Notmoadl> items,Context context) {
        this.items = items;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.design_notfication,null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Notmoadl notmoadl= items.get(position);

        holder.title.setText(notmoadl.getTitle());
        holder.description.setText(notmoadl.getDescription());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView title,description;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.n_title);
            description=itemView.findViewById(R.id.n_descibtion);
        }
    }
}
