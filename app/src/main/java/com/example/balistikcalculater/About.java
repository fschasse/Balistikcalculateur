package com.example.balistikcalculater;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.balistikcalculater.databinding.SettingsAboutBinding;
import com.example.balistikcalculater.databinding.SettingsDonateBinding;

public class About extends Fragment {
    private SettingsAboutBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = SettingsAboutBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btQuit.setOnClickListener(v ->
                NavHostFragment.findNavController(About.this)
                        .navigate(R.id.action_About_to_FirstFragment)
        );

        binding.webViewAbout.loadUrl("file:///android_asset/about.html");
        binding.webViewAbout.setBackgroundColor(Color.WHITE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
