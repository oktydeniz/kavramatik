package com.kavramatik.kavramatik.view.education;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kavramatik.kavramatik.adapter.NumberRecyclerView;
import com.kavramatik.kavramatik.databinding.FragmentNumberBinding;
import com.kavramatik.kavramatik.model.NumberModel;
import com.kavramatik.kavramatik.util.GoogleTTS;
import com.kavramatik.kavramatik.util.ImageClickInterface;
import com.kavramatik.kavramatik.viewModel.NumberViewModel;

import java.util.List;

public class NumberFragment extends Fragment implements ImageClickInterface {
    private static final String TAG = "NumberFragment";
    private FragmentNumberBinding binding;
    private NumberViewModel viewModel;
    private TextToSpeech textToSpeech;
    private int nextOne = 1;
    private int previous;
    private List<NumberModel> numberModels;
    private NumberRecyclerView numberAdapter;

    public NumberFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNumberBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(NumberViewModel.class);
        numberAdapter = new NumberRecyclerView(this);
        observeData();
    }

    private void observeData() {
        viewModel.loading.observe(getViewLifecycleOwner(), i -> {
            if (i) {
                binding.numberProgress.setVisibility(View.VISIBLE);
            } else {
                binding.numberProgress.setVisibility(View.GONE);
            }
        });
        viewModel.isError.observe(getViewLifecycleOwner(), i -> {
            if (i) {
                binding.numberErrorText.setVisibility(View.VISIBLE);
            } else {
                binding.numberErrorText.setVisibility(View.GONE);
            }
        });
        viewModel.getDataAPI().observe(getViewLifecycleOwner(), model -> {
            binding.numberNext.setVisibility(View.VISIBLE);
            Log.i(TAG, "observeData: " + model.size());
            numberModels = model;
            show(numberModels.get(0));
        });
        actions();
    }

    private void actions() {
        binding.numberNext.setOnClickListener(v -> {
            if (nextOne < numberModels.size()) {
                show(numberModels.get(nextOne));
                nextOne++;
                binding.numberBack.setVisibility(View.VISIBLE);
            } else {
                show(numberModels.get(0));
                nextOne = 1;
                binding.numberBack.setVisibility(View.GONE);
            }
        });
        binding.numberBack.setOnClickListener(v -> {
            previous = nextOne - 2;
            if (previous >= 0) {
                show(numberModels.get(previous));
                nextOne--;
            } else {
                binding.numberBack.setVisibility(View.GONE);
            }
        });
    }

    private void show(NumberModel model) {
        numberAdapter.addItem(model);
        binding.numberRecyclerView.setAdapter(numberAdapter);
    }

    @Override
    public void onItemClick(String text) {
        textToSpeech = new TextToSpeech(getContext(), status -> GoogleTTS.getSpeech(text, getContext(), status, this.textToSpeech));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        viewModel.onDestroy();
        GoogleTTS.shotDownTTS(this.textToSpeech);
    }
}