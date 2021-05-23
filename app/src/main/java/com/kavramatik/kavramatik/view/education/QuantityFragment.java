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

import com.kavramatik.kavramatik.adapter.QuantityRecyclerView;
import com.kavramatik.kavramatik.databinding.FragmentQuantityBinding;
import com.kavramatik.kavramatik.model.QuantityModel;
import com.kavramatik.kavramatik.util.GoogleTTS;
import com.kavramatik.kavramatik.util.ImageClickInterface;
import com.kavramatik.kavramatik.viewModel.QuantityViewModel;

import java.util.List;

public class QuantityFragment extends Fragment implements ImageClickInterface {
    private FragmentQuantityBinding binding;
    private QuantityViewModel quantityViewModel;
    private TextToSpeech textToSpeech;
    private QuantityRecyclerView adapter;
    private List<QuantityModel> models;
    private int nextOne = 1;
    private int previous;

    public QuantityFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentQuantityBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        quantityViewModel = new ViewModelProvider(requireActivity()).get(QuantityViewModel.class);
        adapter = new QuantityRecyclerView(this);
        quantityViewModel.getDataAPI();
        observeData();
    }

    private void observeData() {
        quantityViewModel.isError.observe(getViewLifecycleOwner(), i -> {
            if (i) {
                binding.quantityErrorText.setVisibility(View.VISIBLE);
            } else {
                binding.quantityErrorText.setVisibility(View.GONE);
            }
        });
        quantityViewModel.isLoading.observe(getViewLifecycleOwner(), i -> {
            if (i) {
                binding.quantityProgress.setVisibility(View.VISIBLE);
            } else {
                binding.quantityProgress.setVisibility(View.GONE);
            }
        });
        quantityViewModel.listMutableLiveData.observe(getViewLifecycleOwner(), model -> {
            binding.quantityNext.setVisibility(View.VISIBLE);
            models = model;
            show(models.get(0));
        });
        actions();
    }

    private void actions() {
        binding.quantityNext.setOnClickListener(v -> {
            if (nextOne < models.size()) {
                show(models.get(nextOne));
                nextOne++;
                binding.quantityBack.setVisibility(View.VISIBLE);
            } else {
                nextOne = 1;
                show(models.get(0));
                binding.quantityBack.setVisibility(View.GONE);
            }
        });
        binding.quantityBack.setOnClickListener(v -> {
            previous = nextOne - 2;
            if (previous >= 0) {
                nextOne--;
                show(models.get(previous));
            } else {
                binding.quantityBack.setVisibility(View.GONE);
            }
        });
    }

    private void show(QuantityModel model) {
        adapter.addNewModel(model);
        binding.quantityRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        GoogleTTS.shotDownTTS(this.textToSpeech);
    }

    @Override
    public void onItemClick(String text) {
        textToSpeech = new TextToSpeech(getContext(), status -> GoogleTTS.getSpeech(text, getContext(), status, this.textToSpeech));
    }
}