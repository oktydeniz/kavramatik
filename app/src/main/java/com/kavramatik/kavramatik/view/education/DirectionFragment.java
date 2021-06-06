package com.kavramatik.kavramatik.view.education;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kavramatik.kavramatik.R;
import com.kavramatik.kavramatik.adapter.DirectionRecyclerView;
import com.kavramatik.kavramatik.databinding.FragmentDirectionBinding;
import com.kavramatik.kavramatik.model.DirectionModel;
import com.kavramatik.kavramatik.util.AppAlertDialogs;
import com.kavramatik.kavramatik.util.GoogleTTS;
import com.kavramatik.kavramatik.util.ImageClickInterface;
import com.kavramatik.kavramatik.util.SharedPreferencesManager;
import com.kavramatik.kavramatik.viewModel.DirectionViewModel;

import java.util.List;

public class DirectionFragment extends Fragment implements ImageClickInterface {

    private FragmentDirectionBinding binding;
    private DirectionViewModel viewModel;
    private TextToSpeech textToSpeech;
    private int nextOne = 1;
    private int previous;
    private List<DirectionModel> models;
    private DirectionRecyclerView adapter;

    public DirectionFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDirectionBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        boolean isFirst = SharedPreferencesManager.getEducationAssistantD(requireContext());
        if (isFirst) {
            textToSpeech = new TextToSpeech(getContext(), status -> GoogleTTS.getSpeech(getResources().getString(R.string.education_assistant), getContext(), status, this.textToSpeech));
            AppAlertDialogs.educationAssistant(requireContext());
            SharedPreferencesManager.setEducationAssistantD(requireContext(), false);
        }
        viewModel = new ViewModelProvider(requireActivity()).get(DirectionViewModel.class);
        adapter = new DirectionRecyclerView(this);
        viewModel.getData();
        observeData();

    }

    private void observeData() {
        viewModel.loading.observe(getViewLifecycleOwner(), i -> {
            if (i) {
                binding.directionProgress.setVisibility(View.VISIBLE);
            } else {
                binding.directionProgress.setVisibility(View.GONE);
            }
        });
        viewModel.isFailed.observe(getViewLifecycleOwner(), i -> {
            if (i) {
                binding.directionErrorText.setVisibility(View.VISIBLE);
            } else {
                binding.directionErrorText.setVisibility(View.GONE);
            }
        });
        viewModel.directionModel.observe(getViewLifecycleOwner(), model -> {
            if (model.size() >= 1) {
                binding.directionNext.setVisibility(View.VISIBLE);
                models = model;
                show(models.get(0));
            }
        });
        actions();
    }

    private void actions() {
        binding.directionNext.setOnClickListener(v -> {
            if (nextOne < models.size()) {
                show(models.get(nextOne));
                nextOne++;
                binding.directionBack.setVisibility(View.VISIBLE);
            } else {
                show(models.get(0));
                nextOne = 1;
                binding.directionBack.setVisibility(View.GONE);
            }
        });
        binding.directionBack.setOnClickListener(v -> {
            previous = nextOne - 2;
            if (previous >= 0) {
                show(models.get(previous));
                nextOne--;
            } else {
                binding.directionBack.setVisibility(View.GONE);
            }
        });
    }

    private void show(DirectionModel model) {
        adapter.addItem(model);
        binding.directionRecyclerView.setAdapter(adapter);
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