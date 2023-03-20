package com.nabilRadioFrance.radio;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nabilRadioFrance.radio.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {


    private Context context;
    private List<Radio> listitems;

    public MyAdapter(Context context , List listitem) {
        this.context = context;
        this.listitems = listitem;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.radiolist,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {

       Radio item = listitems.get(position);
        holder.name.setText(item.getNomRadio());
        holder.image.setImageResource(item.getImageId());

    }

    @Override
    public int getItemCount() {
        return listitems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView name;
        private ImageView image;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            name = itemView.findViewById(R.id.radioName);
            image = itemView.findViewById(R.id.radioImage);

        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();
            Radio item = listitems.get(position);

            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra("url",item.getUrl());
            intent.putExtra("nomRadio",item.getNomRadio());
            context.startActivity(intent);

        }
    }
}