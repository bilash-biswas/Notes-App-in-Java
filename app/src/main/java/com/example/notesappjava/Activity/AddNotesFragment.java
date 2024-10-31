package com.example.notesappjava.Activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.notesappjava.Model.Notes;
import com.example.notesappjava.R;
import com.example.notesappjava.ViewModel.NotesViewModel;
import com.example.notesappjava.databinding.FragmentAddNotesBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddNotesFragment extends Fragment {

    FragmentAddNotesBinding binding;
    NotesViewModel viewModel;
    String priority = "1";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddNotesBinding.inflate(inflater, container, false);

        viewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        binding.yellowDot.setOnClickListener(v ->{
            priority = "1";
            binding.yellowDot.setImageResource(R.drawable.baseline_done_24);
            binding.greenDot.setImageResource(0);
            binding.redDot.setImageResource(0);
        });

        binding.greenDot.setOnClickListener(v ->{
            priority = "2";
            binding.greenDot.setImageResource(R.drawable.baseline_done_24);
            binding.yellowDot.setImageResource(0);
            binding.redDot.setImageResource(0);
        });

        binding.redDot.setOnClickListener(v ->{
            priority = "3";
            binding.redDot.setImageResource(R.drawable.baseline_done_24);
            binding.greenDot.setImageResource(0);
            binding.yellowDot.setImageResource(0);
        });


        binding.addNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = new SimpleDateFormat("MMMM dd, yyyy ", Locale.getDefault()).format(new Date());
                Notes notes = new Notes(
                        binding.title.getText().toString(),
                        binding.subTitle.getText().toString(),
                        binding.notes.getText().toString(),
                        date,
                        priority
                );
                viewModel.insertNote(notes);
                Toast.makeText(requireContext(), "Notes Created Successfully", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(v).navigate(R.id.action_addNotesFragment_to_homeFragment);
            }
        });


        return binding.getRoot();
    }
}