package com.timac.pluralsightlp2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.MyViewHolder>{

    private Context context;
    private List<SkillIQ> item;

    public MyAdapter2(Context context, List<SkillIQ> skillIQS) {
        this.context = context;
        this.item = skillIQS;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txtHours, txtCountry, txtName;
        private String imgUrl;
        private ImageView myImage;
        public MyViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.textViewName);
            txtCountry = itemView.findViewById(R.id.textViewCountry);
            txtHours = itemView.findViewById(R.id.textViewHours);
            myImage = itemView.findViewById(R.id.imageViewBadge);

        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_row,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.txtName.setText(item.get(position).getName());
        holder.txtHours.setText(String.format("%d skill IQ Score", item.get(position).getScore()));
        holder.txtCountry.setText(String.format("%s.", String.format(",%s", item.get(position).getCountry())));
        Picasso.get().load(item.get(position).getBadgeUrl()).into(holder.myImage);
    }

    @Override
    public int getItemCount() {
        return item.size();
    }
}
