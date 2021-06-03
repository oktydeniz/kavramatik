package com.kavramatik.kavramatik.view.education;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kavramatik.kavramatik.databinding.FragmentSpeechListBinding;
import com.kavramatik.kavramatik.viewModel.ColorViewModel;
import com.kavramatik.kavramatik.viewModel.DirectionViewModel;
import com.kavramatik.kavramatik.viewModel.EmotionViewModel;
import com.kavramatik.kavramatik.viewModel.NumberViewModel;
import com.kavramatik.kavramatik.viewModel.ShapeViewModel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SpeechListFragment extends Fragment {
    private FragmentSpeechListBinding binding;
    private ColorViewModel colorViewModel;
    private ShapeViewModel shapeViewModel;
    private NumberViewModel numberViewModel;
    private EmotionViewModel emotionViewModel;
    private DirectionViewModel directionViewModel;

    public SpeechListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSpeechListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       /* vModels();
        getDataAsync();
        actions();*/
    }

    private void getDataAsync() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(this::fetchFata);

    }

    private void actions() {
        binding.colorsSTT.setOnClickListener(v -> {

        });
    }

    private void vModels() {
        colorViewModel = new ViewModelProvider(requireActivity()).get(ColorViewModel.class);
        emotionViewModel = new ViewModelProvider(requireActivity()).get(EmotionViewModel.class);
        numberViewModel = new ViewModelProvider(requireActivity()).get(NumberViewModel.class);
        shapeViewModel = new ViewModelProvider(requireActivity()).get(ShapeViewModel.class);
        directionViewModel = new ViewModelProvider(requireActivity()).get(DirectionViewModel.class);
    }

    private void fetchFata() {
        colorViewModel.getData();
        emotionViewModel.getData();
        numberViewModel.getData();
        shapeViewModel.getData();
        directionViewModel.getData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}