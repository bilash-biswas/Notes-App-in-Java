package com.example.notesappjava.Activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.notesappjava.Model.Notes;
import com.example.notesappjava.R;
import com.example.notesappjava.ViewModel.NotesViewModel;
import com.example.notesappjava.databinding.FragmentUpdateNotesBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class UpdateNotesFragment extends Fragment {
    FragmentUpdateNotesBinding binding;
    String priority;
    Notes notes;
    NotesViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUpdateNotesBinding.inflate(inflater, container, false);

        viewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        notes = (Notes) getArguments().getSerializable("note");
        binding.title.setText(notes.getTitle());
        binding.subTitle.setText(notes.getSubTitle());
        binding.notes.setText(notes.getNote());
        priority = notes.getPriority();

        setupMenuProvider();

        switch (priority) {
            case "1":
                binding.yellowDot.setImageResource(R.drawable.baseline_done_24);
                break;
            case "2":
                binding.greenDot.setImageResource(R.drawable.baseline_done_24);
                break;
            case "3":
                binding.redDot.setImageResource(R.drawable.baseline_done_24);
                break;
        }

        binding.yellowDot.setOnClickListener(v -> {
            priority = "1";
            binding.yellowDot.setImageResource(R.drawable.baseline_done_24);
            binding.greenDot.setImageResource(0);
            binding.redDot.setImageResource(0);
        });

        binding.greenDot.setOnClickListener(v -> {
            priority = "2";
            binding.greenDot.setImageResource(R.drawable.baseline_done_24);
            binding.yellowDot.setImageResource(0);
            binding.redDot.setImageResource(0);
        });

        binding.redDot.setOnClickListener(v -> {
            priority = "3";
            binding.redDot.setImageResource(R.drawable.baseline_done_24);
            binding.greenDot.setImageResource(0);
            binding.yellowDot.setImageResource(0);
        });

        binding.updateNotes.setOnClickListener(v -> {
            updateNotes();
        });

        return binding.getRoot();
    }

    private void updateNotes() {
        Notes note = new Notes();
        note.setId(notes.getId());
        note.setTitle(binding.title.getText().toString());
        note.setSubTitle(binding.subTitle.getText().toString());
        note.setNote(binding.notes.getText().toString());
        note.setPriority(priority);
        note.setDate(notes.getDate());
        viewModel.updateNote(note);
        Toast.makeText(requireContext(), "Notes Updated Successfully", Toast.LENGTH_SHORT).show();
        Navigation.findNavController(binding.getRoot()).navigate(R.id.action_updateNotesFragment_to_homeFragment);
    }

    private void setupMenuProvider() {
        requireActivity().addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(Menu menu, MenuInflater menuInflater) {
                menuInflater.inflate(R.menu.delete_menu, menu);
            }

            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                if(menuItem.getItemId() == R.id.delete) {
                    bottomSheet();
                    return true;
                }
                return false;
            }
        }, getViewLifecycleOwner());
    }

    private void bottomSheet() {
        BottomSheetDialog dialog = new BottomSheetDialog(requireContext());
        dialog.setContentView(R.layout.delete_dialog);
        dialog.setCancelable(false);
        dialog.findViewById(R.id.yesButton).setOnClickListener(v ->{
            viewModel.deleteNote(notes.getId());
            Toast.makeText(requireContext(), "Notes Deleted Successfully", Toast.LENGTH_SHORT).show();
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_updateNotesFragment_to_homeFragment);
            dialog.dismiss();
        });
        dialog.findViewById(R.id.noButton).setOnClickListener(v ->{
            dialog.dismiss();
        });
        dialog.show();
    }

}