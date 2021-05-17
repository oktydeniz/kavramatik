package com.kavramatik.kavramatik.view.user;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kavramatik.kavramatik.databinding.FragmentSendMailBinding;
import com.kavramatik.kavramatik.model.StatusModel;
import com.kavramatik.kavramatik.network.ManagerAll;
import com.kavramatik.kavramatik.util.AppAlertDialogs;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SendMailFragment extends Fragment {
    private FragmentSendMailBinding binding;
    private String userMail;
    private AppAlertDialogs appAlertDialogs;

    public SendMailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSendMailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        appAlertDialogs = new AppAlertDialogs(getActivity());
        actions();
    }

    private void actions() {
        binding.sendMailButton.setOnClickListener(v -> sendMail());

    }

    private void sendMail() {
        appAlertDialogs.startLoadingDialog();
        userMail = binding.mailEmailEditText.getText().toString();
        Call<StatusModel> call = ManagerAll.getInstance().sendMail(userMail);
        call.enqueue(new Callback<StatusModel>() {
            @Override
            public void onResponse(@NonNull Call<StatusModel> call, @NonNull Response<StatusModel> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getStatus()) {
                            binding.openForNewPassword.setVisibility(View.VISIBLE);
                            binding.sendMailArea.setVisibility(View.GONE);
                            appAlertDialogs.dismissLoading();
                        }
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<StatusModel> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void changePassword() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}