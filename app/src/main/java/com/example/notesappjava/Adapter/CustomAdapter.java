package com.example.notesappjava.Adapter;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesappjava.Activity.HomeFragmentDirections;
import com.example.notesappjava.Model.Notes;
import com.example.notesappjava.R;
import com.example.notesappjava.databinding.ItemBinding;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    Context context;
    ArrayList<Notes> notes;
    public CustomAdapter(Context context, ArrayList<Notes> notes) {
        this.context = context;
        this.notes = notes;
    }
    public void filterNotes(ArrayList<Notes> filteredNotes) {
        notes = filteredNotes;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Notes note = notes.get(position);
        holder.binding.title.setText(note.getTitle());
        holder.binding.note.setText(note.getNote());
        holder.binding.date.setText(note.getDate());
        switch (note.getPriority()){
            case "1":
                holder.binding.dot.setImageResource(R.drawable.yellow_dot);
                break;
            case "2":
                holder.binding.dot.setImageResource(R.drawable.green_dot);
                break;
            case "3":
                holder.binding.dot.setImageResource(R.drawable.red_dot);
                break;
        }

        holder.itemView.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getSerializable("note", Notes.class);
            }
            HomeFragmentDirections.ActionHomeFragmentToUpdateNotesFragment action = HomeFragmentDirections.actionHomeFragmentToUpdateNotesFragment(note);
            Navigation.findNavController(v).navigate(action);
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ItemBinding binding;
        public ViewHolder(View itemView) {
            super(itemView);

            binding = ItemBinding.bind(itemView);
        }
    }
}
