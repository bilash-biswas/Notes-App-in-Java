package com.example.notesappjava.Activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.notesappjava.Adapter.CustomAdapter;
import com.example.notesappjava.Model.Notes;
import com.example.notesappjava.R;
import com.example.notesappjava.ViewModel.NotesViewModel;
import com.example.notesappjava.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;
    NotesViewModel viewModel;
    CustomAdapter adapter;
    ArrayList<Notes> allNotes = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        viewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        setupMenuProvider();

        binding.addButton.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_homeFragment_to_addNotesFragment);
        });

        binding.filter.setOnClickListener(v -> {
            viewModel.getAllNotes.observe(getViewLifecycleOwner(), notes -> {
                allNotes.clear();
                allNotes.addAll(notes);
                setupRecyclerView(allNotes);
            });
        });

        binding.highNotes.setOnClickListener(v -> {
            viewModel.getHighNotes.observe(getViewLifecycleOwner(), notes -> {
                allNotes.clear();
                allNotes.addAll(notes);
                setupRecyclerView(allNotes);
            });
        });

        binding.mediumNotes.setOnClickListener(v -> {
            viewModel.getMediumNotes.observe(getViewLifecycleOwner(), notes -> {
                allNotes.clear();
                allNotes.addAll(notes);
                setupRecyclerView(allNotes);
            });
        });

        binding.lowNotes.setOnClickListener(v -> {
            viewModel.getLowNotes.observe(getViewLifecycleOwner(), notes -> {
                allNotes.clear();
                allNotes.addAll(notes);
                setupRecyclerView(allNotes);
            });
        });

        viewModel.getAllNotes.observe(getViewLifecycleOwner(), notes -> {
            allNotes.clear();
            allNotes.addAll(notes);
            setupRecyclerView(allNotes);
        });


        return binding.getRoot();
    }

    private void setupRecyclerView(ArrayList<Notes> newNotes) {
        binding.recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        binding.recyclerView.setHasFixedSize(true);
        adapter = new CustomAdapter(requireContext(), newNotes);
        binding.recyclerView.setAdapter(adapter);
    }

    private void setupMenuProvider() {
        requireActivity().addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                menuInflater.inflate(R.menu.search_menu, menu);
                MenuItem searchItem = menu.findItem(R.id.app_bar_search);
                SearchView searchView = (SearchView) searchItem.getActionView();
                searchView.setQueryHint("Search Notes...");
                searchView.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.edit_back));
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        filterNotes(newText);
                        return true;
                    }
                });
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                return menuItem.getItemId() == R.id.app_bar_search;
            }
        }, getViewLifecycleOwner());
    }

    private void filterNotes(String newText) {
        ArrayList<Notes> filteredNotes = new ArrayList<>();
        for (Notes note : viewModel.getAllNotes.getValue()) {
            if (note.getTitle().toLowerCase().contains(newText.toLowerCase()) ||
                    note.getSubTitle().toLowerCase().contains(newText.toLowerCase()) ||
                    note.getNote().toLowerCase().contains(newText.toLowerCase())) {
                filteredNotes.add(note);
            }
        }
        adapter.filterNotes(filteredNotes);
    }
}