package com.example.weather.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.Model.ForecastDayModel;
import com.example.weather.Model.HourModel;
import com.example.weather.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HourAdapter extends RecyclerView.Adapter<HourAdapter.ListViewHolder> {
    ArrayList<HourModel> listhour=new ArrayList<>();

    public HourAdapter(ArrayList<HourModel> listhour) {
        this.listhour = listhour;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hour_row, parent,false);
        ListViewHolder listViewHolder = new ListViewHolder(view);
        return listViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.tv_time.setText(listhour.get(position).getTime());
        holder.tv_temp.setText(Double.toString(listhour.get(position).getTemp_c()));
        Picasso.get().load("https:" + listhour.get(position).getCondition().getIcon()).into(holder.tv_condition);
    }

    @Override
    public int getItemCount() {

        return listhour.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
            TextView tv_time, tv_temp;
            ImageView tv_condition;
            ForecastAdapter.OnForecastListener onForecastListener;
        public ListViewHolder(@NonNull View itemView){
            super(itemView);
            tv_time=itemView.findViewById(R.id.tv_time);
            tv_temp=itemView.findViewById(R.id.tv_temp);
            tv_condition=itemView.findViewById(R.id.tv_condition);
        }

    }


}
