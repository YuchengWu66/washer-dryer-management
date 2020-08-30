package com.laioffer.washerdrymanagement.ui.home;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.laioffer.washerdrymanagement.R;
import com.laioffer.washerdrymanagement.database.Item;
import com.laioffer.washerdrymanagement.databinding.ElementLayoutBinding;
import com.laioffer.washerdrymanagement.ui.detail.DetailActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder>{
    public List<Item> washers = new ArrayList<>();
    public int filter;
    private Context context;
    private Map<Integer, String> map;
    public HomeAdapter(Context context, int filter) {
        this.context = context;
        this.filter = filter;
        this.map = new HashMap<>();
        map.put(0, "All");
        map.put(1, "available");
        map.put(2, "reserve");
        map.put(3, "Finished");
        map.put(4, "Damaged");
    }
    public void setWashers(List<Item> newList) {
        washers.clear();
        Collections.sort(newList, (a, b)-> {
            return a.item_id.compareTo(b.item_id);
        });
        washers.addAll(newList);
        notifyDataSetChanged();
    }
    public void clear() {
        washers.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HomeAdapter.HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_layout, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.HomeViewHolder holder, int position) {
        Item washer = washers.get(position);
        holder.washerTextView.setText(washer.item_id);
        holder.typeTextView.setText(washer.type);
        holder.conditionTextView.setText(washer.condition);
        if (washer.condition.equals("available")) {
            holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, DetailActivity.class));
            }
        });
            holder.conditionTextView.setTextColor(0xff1296db);
            if (washer.type.equals("washer")) {
                holder.washerImageView.setImageResource(R.drawable.ic_washer_available);
            }
            else {
                holder.washerImageView.setImageResource(R.drawable.ic_dryer_available);
            }
        }
        else if (washer.condition.equals("reserve")){
            holder.conditionTextView.setTextColor(0xfff4ea2a);
            if (washer.type.equals("washer")) {
                holder.washerImageView.setImageResource(R.drawable.ic_washer_using);
            }
            else {
                holder.washerImageView.setImageResource(R.drawable.ic_dryer_using);
            }
        }
        else if (washer.condition.equals("finished")){
            holder.conditionTextView.setTextColor(0xffd4237a);
            if (washer.type.equals("washer")) {
                holder.washerImageView.setImageResource(R.drawable.ic_washer_finished);
            }
            else {
                holder.washerImageView.setImageResource(R.drawable.ic_dryer_finished);
            }
        }
        else {
            holder.conditionTextView.setTextColor(0xff707070);
            if (washer.type.equals("washer")) {
                holder.washerImageView.setImageResource(R.drawable.ic_washer_occupied);
            }
            else {
                holder.washerImageView.setImageResource(R.drawable.ic_dryer_occupied);
            }
        }
        holder.cardView.setVisibility(View.INVISIBLE);
        Log.d("aaaa", "filter" + filter);
        if (map.get(filter).equals("All")) {
            holder.cardView.setVisibility(View.VISIBLE);
        }
        else if (map.get(filter).equals(washer.condition)) {
            holder.cardView.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (washers == null) {
            return count;
        }
        if (filter == 0) {
            return washers.size();
        }
        int i = 0;
        while(i < washers.size()) {
            if (washers.get(i).condition.equals(map.get(filter))) {
                i++;
            }
            else {
                washers.remove(i);
            }
        }
        return washers.size();
    }
    public static class HomeViewHolder extends RecyclerView.ViewHolder {
        ImageView washerImageView;
        TextView washerTextView;
        TextView conditionTextView;
        TextView brandTextView;
        TextView typeTextView;
        TextView modelTextView;
        TextView addressTextView;
        ConstraintLayout cardView;
        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            ElementLayoutBinding binding = ElementLayoutBinding.bind(itemView);
            washerImageView = binding.washerCardImage;
            washerTextView = binding.washerCardName;
            cardView = binding.selectableWasherCard;
            conditionTextView = binding.conditionText;
            typeTextView = binding.typeText;
        }
    }
}
