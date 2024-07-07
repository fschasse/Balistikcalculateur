package com.example.balistikcalculater;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.balistikcalculater.databinding.SettingsGeneralBinding;

public class SettingsGeneral extends Fragment {
    private SettingsGeneralBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = SettingsGeneralBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btQuit.setOnClickListener(v ->
                NavHostFragment.findNavController(SettingsGeneral.this)
                        .navigate(R.id.action_SettingsGeneral_to_FirstFragment)
        );

        binding.btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (binding.toggleSpeedResultUnit.isChecked()) {
                        MainActivity.SetPrefValue("SETTING_UNIT_SPEED", binding.toggleSpeedResultUnit.getTextOn().toString());
                    } else {
                        MainActivity.SetPrefValue("SETTING_UNIT_SPEED", binding.toggleSpeedResultUnit.getTextOff().toString());
                    }
                    if (binding.toggleDistanceResultUnit.isChecked()) {
                        MainActivity.SetPrefValue("SETTING_UNIT_DISTANCE", binding.toggleDistanceResultUnit.getTextOn().toString());
                    } else {
                        MainActivity.SetPrefValue("SETTING_UNIT_DISTANCE", binding.toggleDistanceResultUnit.getTextOff().toString());
                    }
                    if (binding.toggleMesureResultUnit.isChecked()) {
                        MainActivity.SetPrefValue("SETTING_UNIT_MEASURE", binding.toggleMesureResultUnit.getTextOn().toString());
                    } else {
                        MainActivity.SetPrefValue("SETTING_UNIT_MEASURE", binding.toggleMesureResultUnit.getTextOff().toString());
                    }
                    Toast myToast = Toast.makeText(getActivity(), getString(R.string.configIsSave), Toast.LENGTH_LONG);
                    myToast.show();
                }
                catch(Exception e)
                {
                    Toast myToast = Toast.makeText(getActivity(),  getString(R.string.erreurSaisieChamps), Toast.LENGTH_LONG);
                    myToast.show();
                }
            }
        });

        //Ajout des paramètres
        binding.toggleSpeedResultUnit.setChecked(true);
        binding.toggleDistanceResultUnit.setChecked(true);
        binding.toggleMesureResultUnit.setChecked(true);
        if(MainActivity.GetPrefValue("SETTING_UNIT_SPEED").equals("M/S"))
        {
            binding.toggleSpeedResultUnit.setChecked(false);
        }
        if(MainActivity.GetPrefValue("SETTING_UNIT_DISTANCE").equals("M"))
        {
            binding.toggleDistanceResultUnit.setChecked(false);
        }
        if(MainActivity.GetPrefValue("SETTING_UNIT_MEASURE").equals("CM"))
        {
            binding.toggleMesureResultUnit.setChecked(false);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
