package com.karam.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.karam.rest.rest_messages.requests.Laborer;
import com.karam.view.activity.R;

import java.util.ArrayList;

public class LaborerListAdapter extends RecyclerView.Adapter<LaborerListAdapter.ViewHolder> {
    private ArrayList<Laborer> laborers;
    private Context mContext;


    public LaborerListAdapter(ArrayList<Laborer> l, Context c) {
        this.laborers = l;
        this.mContext = c;
    }

    public void addItems(ArrayList<Laborer> l) {
        this.laborers.addAll(l);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.laborer_listview, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Toast.makeText(mContext, "Position" + position,
                Toast.LENGTH_LONG).show();
        Laborer l = this.laborers.get(position);
        holder.skills.setText(l.getSkills());
        holder.name.setText(l.getFname());
        holder.phoneNo.setText(l.getPhno());
        holder.preferred_location.setText(l.getPreferred_location());

    }

    @Override
    public int getItemCount() {
        return this.laborers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout parentRelativeLayout;
        TextView name;
        TextView phoneNo;
        TextView preferred_location;
        TextView skills;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            parentRelativeLayout = itemView.findViewById(R.id.laborer_listview_relative_layout);
            name = itemView.findViewById(R.id.laborer_listview_name);
            phoneNo = itemView.findViewById(R.id.labprer_listview_phoneno);
            preferred_location = itemView.findViewById(R.id.laborer_listview_location);
            skills = itemView.findViewById(R.id.laborer_listview_skills);
        }
    }
}
