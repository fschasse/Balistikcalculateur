package com.example.balistikcalculater;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.example.balistikcalculater.databinding.MenuHomeBinding;
import com.example.balistikcalculater.databinding.SettingsScopeBinding;

public class SettingScope extends Fragment {
    private SettingsScopeBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = SettingsScopeBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btQuit.setOnClickListener(v ->
                NavHostFragment.findNavController(SettingScope.this)
                        .navigate(R.id.action_SettingScope_to_FirstFragment)
        );

        binding.btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    MainActivity.SetPrefValue("SCOPE_VALUE_UD", binding.tfTurretUD.getText().toString());
                    MainActivity.SetPrefValue("SCOPE_VALUE_LR", binding.tfTurretLR.getText().toString());
                    MainActivity.SetPrefValue("SCOPE_VALUE_HEIGHT", binding.tfScopeHeight.getText().toString());
                    MainActivity.SetPrefValue("SCOPE_VALUE_DISTANCE", binding.tfLbZeroDistance.getText().toString());
                    if (binding.toggleTurretUD.isChecked()) {
                        MainActivity.SetPrefValue("SCOPE_UNIT_UD", binding.toggleTurretUD.getTextOn().toString());
                    } else {
                        MainActivity.SetPrefValue("SCOPE_UNIT_UD", binding.toggleTurretUD.getTextOff().toString());
                    }
                    if (binding.toggleTurretLR.isChecked()) {
                        MainActivity.SetPrefValue("SCOPE_UNIT_LR", binding.toggleTurretLR.getTextOn().toString());
                    } else {
                        MainActivity.SetPrefValue("SCOPE_UNIT_LR", binding.toggleTurretLR.getTextOff().toString());
                    }
                    if (binding.toggleScopeHeight.isChecked()) {
                        MainActivity.SetPrefValue("SCOPE_UNIT_HEIGHT", binding.toggleScopeHeight.getTextOn().toString());
                    } else {
                        MainActivity.SetPrefValue("SCOPE_UNIT_HEIGHT", binding.toggleScopeHeight.getTextOff().toString());
                    }
                    if (binding.toggleZeroDistance.isChecked()) {
                        MainActivity.SetPrefValue("SCOPE_UNIT_ZEROING", binding.toggleZeroDistance.getTextOn().toString());
                    } else {
                        MainActivity.SetPrefValue("SCOPE_UNIT_ZEROING", binding.toggleZeroDistance.getTextOff().toString());
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
        binding.tfTurretUD.setText(MainActivity.GetPrefValue("SCOPE_VALUE_UD"));
        binding.tfTurretLR.setText(MainActivity.GetPrefValue("SCOPE_VALUE_LR"));
        binding.tfScopeHeight.setText(MainActivity.GetPrefValue("SCOPE_VALUE_HEIGHT"));
        binding.tfLbZeroDistance.setText(MainActivity.GetPrefValue("SCOPE_VALUE_DISTANCE"));
        binding.toggleTurretUD.setChecked(true);
        binding.toggleTurretLR.setChecked(true);
        binding.toggleScopeHeight.setChecked(true);
        binding.toggleZeroDistance.setChecked(true);
        if(MainActivity.GetPrefValue("SCOPE_UNIT_UD").equals("MOA"))
        {
            binding.toggleTurretUD.setChecked(false);
        }
        if(MainActivity.GetPrefValue("SCOPE_UNIT_LR").equals("MOA"))
        {
            binding.toggleTurretLR.setChecked(false);
        }
        if(MainActivity.GetPrefValue("SCOPE_UNIT_HEIGHT").equals("CM"))
        {
            binding.toggleScopeHeight.setChecked(false);
        }
        if(MainActivity.GetPrefValue("SCOPE_UNIT_ZEROING").equals("M"))
        {
            binding.toggleZeroDistance.setChecked(false);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}