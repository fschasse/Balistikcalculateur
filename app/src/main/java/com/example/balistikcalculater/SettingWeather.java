package com.example.balistikcalculater;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.balistikcalculater.databinding.SettingsWeatherBinding;

public class SettingWeather extends Fragment {
    private SettingsWeatherBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = SettingsWeatherBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btQuit.setOnClickListener(v ->
                NavHostFragment.findNavController(SettingWeather.this)
                        .navigate(R.id.action_SettingWeather_to_FirstFragment)
        );

        binding.btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    MainActivity.SetPrefValue("WEATHER_VALUE_TEMPERATURE", binding.tfTemperature.getText().toString());
                    MainActivity.SetPrefValue("WEATHER_VALUE_HUMIDITY", binding.tfHumidity.getText().toString());
                    MainActivity.SetPrefValue("WEATHER_VALUE_ALTITUDE", binding.tfAltitude.getText().toString());
                    MainActivity.SetPrefValue("WEATHER_VALUE_PRESSURE", binding.tfPressure.getText().toString());
                    if (binding.toggleTemperature.isChecked()) {
                        MainActivity.SetPrefValue("WEATHER_UNIT_TEMPERATURE", binding.toggleTemperature.getTextOn().toString());
                    } else {
                        MainActivity.SetPrefValue("WEATHER_UNIT_TEMPERATURE", binding.toggleTemperature.getTextOff().toString());
                    }
                    if (binding.toggleHumidity.isChecked()) {
                        MainActivity.SetPrefValue("WEATHER_UNIT_HUMIDITY", binding.toggleHumidity.getTextOn().toString());
                    } else {
                        MainActivity.SetPrefValue("WEATHER_UNIT_HUMIDITY", binding.toggleHumidity.getTextOff().toString());
                    }
                    if (binding.toggleAltitude.isChecked()) {
                        MainActivity.SetPrefValue("WEATHER_UNIT_ALTITUDE", binding.toggleAltitude.getTextOn().toString());
                    } else {
                        MainActivity.SetPrefValue("WEATHER_UNIT_ALTITUDE", binding.toggleAltitude.getTextOff().toString());
                    }
                    if (binding.togglePressure.isChecked()) {
                        MainActivity.SetPrefValue("WEATHER_UNIT_PRESSURE", binding.togglePressure.getTextOn().toString());
                    } else {
                        MainActivity.SetPrefValue("WEATHER_UNIT_PRESSURE", binding.togglePressure.getTextOff().toString());
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
        binding.tfTemperature.setText(MainActivity.GetPrefValue("WEATHER_VALUE_TEMPERATURE"));
        binding.tfHumidity.setText(MainActivity.GetPrefValue("WEATHER_VALUE_HUMIDITY"));
        binding.tfAltitude.setText(MainActivity.GetPrefValue("WEATHER_VALUE_ALTITUDE"));
        binding.tfPressure.setText(MainActivity.GetPrefValue("WEATHER_VALUE_PRESSURE"));
        binding.toggleTemperature.setChecked(true);
        binding.toggleHumidity.setChecked(true);
        binding.toggleAltitude.setChecked(true);
        binding.togglePressure.setChecked(true);
        if(MainActivity.GetPrefValue("WEATHER_UNIT_TEMPERATURE").equals("°C"))
        {
            binding.toggleTemperature.setChecked(false);
        }
        if(MainActivity.GetPrefValue("WEATHER_UNIT_HUMIDITY").equals("%"))
        {
            binding.toggleHumidity.setChecked(false);
        }
        if(MainActivity.GetPrefValue("WEATHER_UNIT_ALTITUDE").equals("M"))
        {
            binding.toggleAltitude.setChecked(false);
        }
        if(MainActivity.GetPrefValue("WEATHER_UNIT_PRESSURE").equals("HPA"))
        {
            binding.togglePressure.setChecked(false);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
