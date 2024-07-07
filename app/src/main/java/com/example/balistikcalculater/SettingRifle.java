package com.example.balistikcalculater;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.balistikcalculater.databinding.SettingsRifleBinding;
import com.example.balistikcalculater.databinding.SettingsScopeBinding;

public class SettingRifle extends Fragment {
    private SettingsRifleBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = SettingsRifleBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btQuit.setOnClickListener(v ->
                NavHostFragment.findNavController(SettingRifle.this)
                        .navigate(R.id.action_SettingsRifle_to_FirstFragment)
        );

        binding.btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    MainActivity.SetPrefValue("BULLET_VALUE_SPEED", binding.tfBulletSpeed.getText().toString());
                    MainActivity.SetPrefValue("BULLET_VALUE_WEIGHT", binding.tfBulletWeight.getText().toString());
                    MainActivity.SetPrefValue("BULLET_VALUE_BC", binding.tfBC.getText().toString());
                    MainActivity.SetPrefValue("BULLET_NAME", binding.tfLbRifleBulletName.getText().toString());
                    if (binding.toggleBulletSpeedtUnit.isChecked()) {
                        MainActivity.SetPrefValue("BULLET_UNIT_SPEED", binding.toggleBulletSpeedtUnit.getTextOn().toString());
                    } else {
                        MainActivity.SetPrefValue("BULLET_UNIT_SPEED", binding.toggleBulletSpeedtUnit.getTextOff().toString());
                    }
                    if (binding.toggleBulletWeightUnit.isChecked()) {
                        MainActivity.SetPrefValue("BULLET_UNIT_WEIGHT", binding.toggleBulletWeightUnit.getTextOn().toString());
                    } else {
                        MainActivity.SetPrefValue("BULLET_UNIT_WEIGHT", binding.toggleBulletWeightUnit.getTextOff().toString());
                    }
                    if (binding.toggleBcUnit.isChecked()) {
                        MainActivity.SetPrefValue("BULLET_UNIT_BC", binding.toggleBcUnit.getTextOn().toString());
                    } else {
                        MainActivity.SetPrefValue("BULLET_UNIT_BC", binding.toggleBcUnit.getTextOff().toString());
                    }
                    Toast myToast = Toast.makeText(getActivity(), getString(R.string.configIsSave), Toast.LENGTH_LONG);
                    myToast.show();
                } catch (Exception e) {
                    Toast myToast = Toast.makeText(getActivity(), getString(R.string.erreurSaisieChamps), Toast.LENGTH_LONG);
                    myToast.show();
                }
            }
        });

        //Ajout des paramètres
        binding.tfBulletSpeed.setText(MainActivity.GetPrefValue("BULLET_VALUE_SPEED"));
        binding.tfBulletWeight.setText(MainActivity.GetPrefValue("BULLET_VALUE_WEIGHT"));
        binding.tfBC.setText(MainActivity.GetPrefValue("BULLET_VALUE_BC"));
        binding.tfLbRifleBulletName.setText(MainActivity.GetPrefValue("BULLET_NAME"));
        binding.toggleBulletSpeedtUnit.setChecked(true);
        binding.toggleBulletWeightUnit.setChecked(true);
        binding.toggleBcUnit.setChecked(true);
        if (MainActivity.GetPrefValue("BULLET_UNIT_SPEED").equals("M/S")) {
            binding.toggleBulletSpeedtUnit.setChecked(false);
        }
        if (MainActivity.GetPrefValue("BULLET_UNIT_WEIGHT").equals("G")) {
            binding.toggleBulletWeightUnit.setChecked(false);
        }
        if (MainActivity.GetPrefValue("BULLET_UNIT_BC").equals("G1")) {
            binding.toggleBcUnit.setChecked(false);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
