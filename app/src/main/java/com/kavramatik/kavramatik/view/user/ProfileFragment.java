package com.kavramatik.kavramatik.view.user;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.kavramatik.kavramatik.R;
import com.kavramatik.kavramatik.databinding.FragmentProfileBinding;
import com.kavramatik.kavramatik.util.SharedPreferencesManager;

public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;

    public ProfileFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeValues();
        actions();

    }

    private void actions() {
        binding.goToLoginFragment.setOnClickListener(v -> {
            NavController controller = NavHostFragment.findNavController(this);
            controller.navigate(R.id.action_profileFragment_to_loginFragment);
        });
        binding.openGoogleTTSPage.setOnClickListener(v -> {
            try {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=" + "com.google.android.tts")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        binding.openForRateUs.setOnClickListener(v -> {
            try {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=" + requireContext().getPackageName())));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    private void initializeValues() {
        int score = SharedPreferencesManager.getScore(getContext());
        String name = SharedPreferencesManager.getUserName(getContext());
        String email = SharedPreferencesManager.getUserEmail(getContext());

        //Get App Version For Show in TextView
        String version = requireContext().getResources().getString(R.string.app_version);
        try {
            PackageInfo pInfo = requireContext().getPackageManager().getPackageInfo(requireContext().getPackageName(), 0);
            version += pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            version += "0.0";
        }
        //set all views values
        binding.profileUserEmail.setText(email);
        binding.profileUserName.setText(name);
        binding.profileUserScore.setText(String.valueOf(score));
        binding.appVersionText.setText(version);

        //Set TextView status if true don't show and if not show the view
        int result = SharedPreferencesManager.getUserId(getContext());
        if (result == SharedPreferencesManager.defaultID) {
            binding.goToLoginFragment.setVisibility(View.VISIBLE);
        } else {
            binding.goToLoginFragment.setVisibility(View.GONE);
        }

    }

    private void isLogin() {
        NavController controller = NavHostFragment.findNavController(this);
        int result = SharedPreferencesManager.getUserId(getContext());
        if (result == SharedPreferencesManager.defaultID) {
            controller.navigate(R.id.action_profileFragment_to_loginFragment);
        }
    }
}
