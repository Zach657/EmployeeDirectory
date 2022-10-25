package com.example.employeedirectory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class EmployeeItemAdapter extends RecyclerView.Adapter<EmployeeItemAdapter.ViewHolder>{

    private ArrayList<EmployeeEntity> employees;

    public EmployeeItemAdapter(ArrayList<EmployeeEntity> employees){
        this.employees = employees;
    }

    public ArrayList<EmployeeEntity> getEmployeeEntityArrayList(){
        return employees;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Name
        ((TextView) holder.itemView.findViewById(R.id.name)).setText(employees.get(position).getFullName());
        //Email
        ((TextView) holder.itemView.findViewById(R.id.emailAddress)).setText(employees.get(position).getEmailAddress());
        //Team
        ((TextView) holder.itemView.findViewById(R.id.team)).setText(employees.get(position).getTeam());
        //Employment Type
        ((TextView) holder.itemView.findViewById(R.id.employmentType)).setText(employees.get(position).getEmployeeType());
        //Image
        String smallPhotoURL = employees.get(position).getPhotoURLSmall();
        if (!smallPhotoURL.isEmpty()){
            Glide.with(holder.itemView.findViewById(R.id.headshot)).load(smallPhotoURL).placeholder(R.drawable.placeholder_headshot).into((ImageView)holder.itemView.findViewById(R.id.headshot));
        }
        else{
            ((ImageView)holder.itemView.findViewById(R.id.headshot)).setImageResource(R.drawable.placeholder_headshot);
        }
    }

    @Override
    public int getItemCount() {
        if(employees != null){
            return employees.size();
        }
        return 0;
    }

}
