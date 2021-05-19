package com.kavramatik.kavramatik.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kavramatik.kavramatik.databinding.FragmentEducationCategoriesBinding;

public class EducationCategories extends Fragment {

    private FragmentEducationCategoriesBinding binding;

    public EducationCategories() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentEducationCategoriesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        actions();
    }

    private void actions() {
        binding.colors.setOnClickListener(v -> {
            NavDirections directions = EducationCategoriesDirections.actionEducationCategoriesToColorFragment();
            Navigation.findNavController(v).navigate(directions);
        });
        binding.shapes.setOnClickListener(v -> {
            NavDirections directions = EducationCategoriesDirections.actionEducationCategoriesToShapeFragment();
            Navigation.findNavController(v).navigate(directions);
        });
        binding.dimensions.setOnClickListener(v -> {
            NavDirections directions = EducationCategoriesDirections.actionEducationCategoriesToDimensionFragment();
            Navigation.findNavController(v).navigate(directions);
        });
        binding.directions.setOnClickListener(v -> {
            NavDirections directions = EducationCategoriesDirections.actionEducationCategoriesToDirectionFragment();
            Navigation.findNavController(v).navigate(directions);
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}