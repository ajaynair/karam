package com.karam.view.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.karam.db.pojo.Laborer;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;

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
    holder.skills.setText(l.getSkill());
    holder.name.setText(l.getName());
    holder.phoneNo.setText(l.getPhoneNo());
    holder.preferredLocation.setText(l.getLocation());

    }

    @Override
    public int getItemCount() {
    return this.laborers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout parentRelativeLayout;
        TextView name;
        TextView phoneNo;
        TextView preferredLocation;
        TextView skills;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            parentRelativeLayout = itemView.findViewById(R.id.parentRelativeLayout);
            name = itemView.findViewById(R.id.name);
            phoneNo = itemView.findViewById(R.id.phoneno);
            preferredLocation = itemView.findViewById(R.id.location);
            skills = itemView.findViewById(R.id.skills);
        }
    };
}
