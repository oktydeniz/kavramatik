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

import com.kavramatik.kavramatik.adapter.ColorRecyclerView;
import com.kavramatik.kavramatik.databinding.FragmentColorBinding;
import com.kavramatik.kavramatik.util.GoogleTTS;
import com.kavramatik.kavramatik.util.ImageClickInterface;
import com.kavramatik.kavramatik.viewModel.ColorViewModel;

public class ColorFragment extends Fragment implements ImageClickInterface {
    private ColorViewModel colorViewModel;
    private TextToSpeech textToSpeech;
    private FragmentColorBinding binding;
    private ColorRecyclerView adapterColor;

    public ColorFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentColorBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        colorViewModel = new ViewModelProvider(requireActivity()).get(ColorViewModel.class);

        observeData();
    }

    private void observeData() {
        colorViewModel.loading.observe(getViewLifecycleOwner(), i -> {
            if (i) {
                binding.progressBar.setVisibility(View.VISIBLE);
            } else {
                binding.progressBar.setVisibility(View.GONE);
            }
        });
        colorViewModel.error.observe(getViewLifecycleOwner(), i -> {
            if (i) {
                binding.colorErrorText.setVisibility(View.VISIBLE);
            } else {
                binding.colorErrorText.setVisibility(View.GONE);
            }
        });
        colorViewModel.getDataAPI().observe(getViewLifecycleOwner(), model -> {
            adapterColor = new ColorRecyclerView(model, this);
            binding.colorRecyclerView.setAdapter(adapterColor);
        });
    }

    @Override
    public void onItemClick(String text) {
        textToSpeech = new TextToSpeech(getContext(), status -> GoogleTTS.getSpeech(text, getContext(), status, this.textToSpeech));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        GoogleTTS.shotDownTTS(this.textToSpeech);
    }
}