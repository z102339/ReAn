package org.jack.ReAt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jack.ReAt.bean.Person;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    private List<Person> dataset;

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_item, parent, false);
        return new EmployeeViewHolder(itemView);
    }

    public List<Person> getDataset() {
        return dataset;
    }

    public void setDataset(List<Person> dataset) {
        this.dataset = dataset;
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        Person p = dataset.get(position);
        String name = p.getName();
        holder.name.setText(name);
        holder.age.setText(String.valueOf(p.getAge()));
        holder.gender.setText(p.getGender());
    }

    @Override
    public int getItemCount() {
        return dataset == null ? 0 : dataset.size();
    }

   class EmployeeViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView age;
        public TextView gender;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            age = itemView.findViewById(R.id.age);
            gender = itemView.findViewById(R.id.gender);
        }
    }
}

