package com.kavramatik.kavramatik.view.education;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kavramatik.kavramatik.databinding.FragmentColorMatchBinding;
import com.kavramatik.kavramatik.viewModel.ColorMatchViewModel;

public class ColorMatchFragment extends Fragment {
    private FragmentColorMatchBinding binding;
    private ColorMatchViewModel colorMatchViewModel;

    private static final String TAG = "ColorMatchFragment";

    public ColorMatchFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentColorMatchBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        colorMatchViewModel = new ViewModelProvider(requireActivity()).get(ColorMatchViewModel.class);
        colorMatchViewModel.getData();
        observeData();
    }

    private void observeData() {

        colorMatchViewModel.errorColors.observe(getViewLifecycleOwner(), i -> {
            if (i) {
                binding.errorTextViewColor.setVisibility(View.VISIBLE);
            } else binding.errorTextViewColor.setVisibility(View.GONE);
        });
        colorMatchViewModel.loadingColors.observe(getViewLifecycleOwner(), i -> {
            if (i) binding.machColorsProgress.setVisibility(View.VISIBLE);
            else binding.machColorsProgress.setVisibility(View.GONE);
        });
        colorMatchViewModel.mutableLiveDataColors.observe(getViewLifecycleOwner(), models -> {
            if (models.size() >= 1) {
                Log.i(TAG, "observeData: Model Size :  " + models.size());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}