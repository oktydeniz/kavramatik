package com.kavramatik.kavramatik.view.education;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kavramatik.kavramatik.adapter.ShapeRecyclerView;
import com.kavramatik.kavramatik.databinding.FragmentShapeBinding;
import com.kavramatik.kavramatik.util.GoogleTTS;
import com.kavramatik.kavramatik.util.ImageClickInterface;
import com.kavramatik.kavramatik.viewModel.ShapeViewModel;

public class ShapeFragment extends Fragment implements ImageClickInterface {
    private FragmentShapeBinding binding;
    private TextToSpeech textToSpeech;
    private ShapeViewModel shapeViewModel;
    private ShapeRecyclerView adapter;

    public ShapeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentShapeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        shapeViewModel = new ViewModelProvider(requireActivity()).get(ShapeViewModel.class);
        observeData();
    }

    private void observeData() {
        shapeViewModel.loading.observe(getViewLifecycleOwner(), i -> {
            if (i) {
                binding.progressBarShape.setVisibility(View.VISIBLE);
            } else {
                binding.progressBarShape.setVisibility(View.GONE);
            }
        });
        shapeViewModel.getDataAPI().observe(getViewLifecycleOwner(), model -> {
            adapter = new ShapeRecyclerView(model, this);
            binding.shapeRecyclerView.setAdapter(adapter);
        });
    }

    @Override
    public void onItemClick(String text) {
        textToSpeech = new TextToSpeech(getContext(), status -> GoogleTTS.getSpeech(text, getContext(), status, this.textToSpeech));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
        shapeViewModel.destroy();
    }


}