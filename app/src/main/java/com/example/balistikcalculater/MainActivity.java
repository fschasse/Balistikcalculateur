package com.example.balistikcalculater;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.balistikcalculater.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {
    //On ajout les variables globales

    //Variables pour les paramètres de lunette
    private static final String scopeUnitUD = "scope_unit_ud";
    private static final String scopeUnitLR = "scope_unit_lr";
    private static final String scopeValueUD = "scope_value_ud";
    private static final String scopeValueLR = "scope_value_lr";
    private static final String scopeUnitHeight = "scope_unit_height";
    private static final String scopeValueHeight = "scope_value_height";
    private static final String scopeUnitZero = "scope_unit_zeroing";
    private static final String scopeValueZero = "scope_value_zeroing";
    private static final String systemUnit = "system_unit";
    private static final String systemDistance = "system_distance";
    private static String scopeUnitUDParam;
    private static String scopeUnitLRParam;
    private static String systemUnitParam;
    private static String systemDistanceParam;
    private static float scopeValueUDParam;
    private static float scopeValueLRParam;
    private static String scopeUnitHeightParam;
    private static float scopeValueHeightParam;
    private static String scopeUnitZeroParam;
    private static float scopeValueZeroParam;

    //Variables pour les paramètres de temps
    private static final String weatherUnitTemperature = "weather_unit_temperature";
    private static final String weatherValueTemperature = "weather_value_temperature";
    private static String weatherUnitTemperatureParam;
    private static float weatherValueTemperatureParam;
    private static final String weatherUnitHumidity = "weather_unit_humidity";
    private static final String weatherValueHumidity = "weather_value_humidity";
    private static String weatherUnitHumidityParam;
    private static float weatherValueHumidityParam;
    private static final String weatherUnitAltitude = "weather_unit_altitude";
    private static final String weatherValueAltitude = "weather_value_altitude";
    private static String weatherUnitAltitudeParam;
    private static float weatherValueAltitudeParam;
    private static final String weatherUnitPressure = "weather_unit_pressure";
    private static final String weatherValuePressure = "weather_value_pressure";
    private static String weatherUnitPressureParam;
    private static float weatherValuePressureParam;

    //Variables pour les paramètres généreaux
    private static final String settingUnitSpeed = "setting_unit_speed";
    private static final String settingUnitMeasure = "setting_unit_measure";
    private static final String settingUnitDistance = "setting_unit_distance";
    private static String settingUnitSpeedParam;
    private static String settingUnitMeasureParam;
    private static String settingUnitDistanceParam;

    //Variables pour les paramètres de bullet
    private static final String bulletUnitSpeed = "bullet_unit_speed";
    private static final String bulletUnitWeight = "bullet_unit_weight";
    private static final String bulletUnitBC = "bullet_unit_bc";
    private static final String bulletValueSpeed = "bullet_value_speed";
    private static final String bulletValueWeight = "bullet_value_weight";
    private static final String bulletValueBC = "bullet_value_bc";
    private static final String bulletName = "bullet_name";
    private static String bulletUnitSpeedParam;
    private static String bulletUnitWeightParam;
    private static String bulletUnitBCParam;
    private static float bulletValueSpeedParam;
    private static float bulletValueWeightParam;
    private static float bulletValueBCParam;
    private static String bulletNameParam;

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private static SharedPreferences sharedpreferences;
    private static final String mypreference = "settingsValues";

    private Drawable resize(Drawable image) {
        Bitmap b = ((BitmapDrawable)image).getBitmap();
        Bitmap bitmapResized = Bitmap.createScaledBitmap(b, 64, 64, false);
        return new BitmapDrawable(getResources(), bitmapResized);
    }

    private CharSequence menuIconWithText(Drawable r, String title) {
        r = resize(r);
        r.setBounds(0, 0, r.getIntrinsicWidth(), r.getIntrinsicHeight());
        SpannableString sb = new SpannableString("    " + title);
        ImageSpan imageSpan = new ImageSpan(r, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        return sb;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        if(sharedpreferences.getString(scopeUnitUD, "").isEmpty()) {
            editor.putString(scopeUnitUD, "MOA");
            editor.commit();
        }
        if(sharedpreferences.getString(scopeUnitLR, "").isEmpty()) {
            editor.putString(scopeUnitLR, "MOA");
            editor.commit();
        }
        if(sharedpreferences.getString(scopeValueUD, "").isEmpty()) {
            editor.putString(scopeValueUD, "0.25");
            editor.commit();
        }
        if(sharedpreferences.getString(scopeValueLR, "").isEmpty()) {
            editor.putString(scopeValueLR, "0.25");
            editor.commit();
        }
        if(sharedpreferences.getString(scopeUnitHeight, "").isEmpty()) {
            editor.putString(scopeUnitHeight, "CM");
            editor.commit();
        }
        if(sharedpreferences.getString(scopeValueHeight, "").isEmpty()) {
            editor.putString(scopeValueHeight, "5.0");
            editor.commit();
        }
        if(sharedpreferences.getString(scopeUnitZero, "").isEmpty()) {
            editor.putString(scopeUnitZero, "M");
            editor.commit();
        }
        if(sharedpreferences.getString(scopeValueZero, "").isEmpty()) {
            editor.putString(scopeValueZero, "100");
            editor.commit();
        }
        if(sharedpreferences.getString(systemUnit, "").isEmpty()) {
            editor.putString(systemUnit, "METRIC");
            editor.commit();
        }
        if(sharedpreferences.getString(systemDistance, "").isEmpty()) {
            editor.putString(systemDistance, "METERS");
            editor.commit();
        }
        if(sharedpreferences.getString(weatherValueTemperature, "").isEmpty()) {
            editor.putString(weatherValueTemperature, "17.5");
            editor.commit();
        }
        if(sharedpreferences.getString(weatherUnitTemperature, "").isEmpty()) {
            editor.putString(weatherUnitTemperature, "°C");
            editor.commit();
        }
        if(sharedpreferences.getString(weatherValueHumidity, "").isEmpty()) {
            editor.putString(weatherValueHumidity, "65.00");
            editor.commit();
        }
        if(sharedpreferences.getString(weatherUnitHumidity, "").isEmpty()) {
            editor.putString(weatherUnitHumidity, "%");
            editor.commit();
        }
        if(sharedpreferences.getString(weatherValueAltitude, "").isEmpty()) {
            editor.putString(weatherValueAltitude, "298.5");
            editor.commit();
        }
        if(sharedpreferences.getString(weatherUnitAltitude, "").isEmpty()) {
            editor.putString(weatherUnitAltitude, "M");
            editor.commit();
        }
        if(sharedpreferences.getString(weatherValuePressure, "").isEmpty()) {
            editor.putString(weatherValuePressure, "1013.00");
            editor.commit();
        }
        if(sharedpreferences.getString(weatherUnitPressure, "").isEmpty()) {
            editor.putString(weatherUnitPressure, "HPA");
            editor.commit();
        }
        if(sharedpreferences.getString(settingUnitSpeed, "").isEmpty()) {
            editor.putString(settingUnitSpeed, "M/S");
            editor.commit();
        }
        if(sharedpreferences.getString(settingUnitDistance, "").isEmpty()) {
            editor.putString(settingUnitDistance, "M");
            editor.commit();
        }
        if(sharedpreferences.getString(settingUnitMeasure, "").isEmpty()) {
            editor.putString(settingUnitMeasure, "CM");
            editor.commit();
        }
        if(sharedpreferences.getString(weatherValuePressure, "").isEmpty()) {
            editor.putString(weatherValuePressure, "1013.00");
            editor.commit();
        }
        //BULLET FIELD
        if(sharedpreferences.getString(bulletUnitSpeed, "").isEmpty()) {
            editor.putString(bulletUnitSpeed, "M/S");
            editor.commit();
        }
        if(sharedpreferences.getString(bulletValueSpeed, "").isEmpty()) {
            editor.putString(bulletValueSpeed, "940.0");
            editor.commit();
        }
        if(sharedpreferences.getString(bulletUnitWeight, "").isEmpty()) {
            editor.putString(bulletUnitWeight, "GR");
            editor.commit();
        }
        if(sharedpreferences.getString(bulletValueWeight, "").isEmpty()) {
            editor.putString(bulletValueWeight, "11.7");
            editor.commit();
        }
        if(sharedpreferences.getString(bulletUnitBC, "").isEmpty()) {
            editor.putString(bulletUnitBC, "G1");
            editor.commit();
        }
        if(sharedpreferences.getString(bulletValueBC, "").isEmpty()) {
            editor.putString(bulletValueBC, "0.353");
            editor.commit();
        }
        if(sharedpreferences.getString(bulletName, "").isEmpty()) {
            editor.putString(bulletName, "SB 300wby - SPCE 180gr");
            editor.commit();
        }
        //SCOPE
        systemUnitParam = sharedpreferences.getString(systemUnit, "");
        systemDistanceParam = sharedpreferences.getString(systemDistance, "");
        scopeUnitLRParam = sharedpreferences.getString(scopeUnitLR, "");
        scopeUnitUDParam = sharedpreferences.getString(scopeUnitUD, "");
        scopeValueLRParam = Float.parseFloat(sharedpreferences.getString(scopeValueLR, ""));
        scopeValueUDParam = Float.parseFloat(sharedpreferences.getString(scopeValueUD, ""));
        scopeUnitHeightParam = sharedpreferences.getString(scopeUnitHeight, "");
        scopeValueHeightParam = Float.parseFloat(sharedpreferences.getString(scopeValueHeight, ""));
        scopeUnitZeroParam = sharedpreferences.getString(scopeUnitZero, "");
        scopeValueZeroParam = Float.parseFloat(sharedpreferences.getString(scopeValueZero, ""));
        //WEATHER
        weatherUnitTemperatureParam = sharedpreferences.getString(weatherUnitTemperature, "");
        weatherValueTemperatureParam = Float.parseFloat(sharedpreferences.getString(weatherValueTemperature, ""));
        weatherUnitHumidityParam = sharedpreferences.getString(weatherUnitHumidity, "");
        weatherValueHumidityParam = Float.parseFloat(sharedpreferences.getString(weatherValueHumidity, ""));
        weatherUnitAltitudeParam = sharedpreferences.getString(weatherUnitAltitude, "");
        weatherValueAltitudeParam = Float.parseFloat(sharedpreferences.getString(weatherValueAltitude, ""));
        weatherUnitPressureParam = sharedpreferences.getString(weatherUnitPressure, "");
        weatherValuePressureParam = Float.parseFloat(sharedpreferences.getString(weatherValuePressure, ""));
        //GENERAL SETTING
        settingUnitSpeedParam = sharedpreferences.getString(settingUnitSpeed, "");
        settingUnitDistanceParam = sharedpreferences.getString(settingUnitDistance, "");
        settingUnitMeasureParam = sharedpreferences.getString(settingUnitMeasure, "");
        //RIFLE SETTING
        bulletUnitSpeedParam = sharedpreferences.getString(bulletUnitSpeed, "");
        bulletValueSpeedParam = Float.parseFloat(sharedpreferences.getString(bulletValueSpeed, ""));
        bulletUnitWeightParam = sharedpreferences.getString(bulletUnitWeight, "");
        bulletValueWeightParam = Float.parseFloat(sharedpreferences.getString(bulletValueWeight, ""));
        bulletUnitBCParam = sharedpreferences.getString(bulletUnitBC, "");
        bulletValueBCParam = Float.parseFloat(sharedpreferences.getString(bulletValueBC, ""));
        bulletNameParam = sharedpreferences.getString(bulletName, "");

        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }

    //On récupère les préférences
    public static String GetPrefValue(String valueKey)
    {
        String prefValue = "";
        switch(valueKey) {
            case "SCOPE_UNIT_UD" :
                prefValue = scopeUnitUDParam;
                break;
            case "SCOPE_UNIT_LR" :
                prefValue = scopeUnitLRParam;
                break;
            case "SCOPE_VALUE_UD" :
                prefValue = String.valueOf(scopeValueUDParam);
                break;
            case "SCOPE_VALUE_LR" :
                prefValue = String.valueOf(scopeValueLRParam);
                break;
            case "SYSTEM_UNIT" :
                prefValue = String.valueOf(systemUnitParam);
                break;
            case "SCOPE_VALUE_HEIGHT" :
                prefValue = String.valueOf(scopeValueHeightParam);
                break;
            case "SCOPE_UNIT_HEIGHT" :
                prefValue = String.valueOf(scopeUnitHeightParam);
                break;
            case "SCOPE_VALUE_DISTANCE" :
                prefValue = String.valueOf(scopeValueZeroParam);
                break;
            case "SCOPE_UNIT_ZEROING" :
                prefValue = String.valueOf(scopeUnitZeroParam);
                break;
            //WEATHER
            case "WEATHER_UNIT_TEMPERATURE" :
                prefValue = String.valueOf(weatherUnitTemperatureParam);
                break;
            case "WEATHER_VALUE_TEMPERATURE" :
                prefValue = String.valueOf(weatherValueTemperatureParam);
                break;
            case "WEATHER_UNIT_HUMIDITY" :
                prefValue = String.valueOf(weatherUnitHumidityParam);
                break;
            case "WEATHER_VALUE_HUMIDITY" :
                prefValue = String.valueOf(weatherValueHumidityParam);
                break;
            case "WEATHER_UNIT_ALTITUDE" :
                prefValue = String.valueOf(weatherUnitAltitudeParam);
                break;
            case "WEATHER_VALUE_ALTITUDE" :
                prefValue = String.valueOf(weatherValueAltitudeParam);
                break;
            case "WEATHER_UNIT_PRESSURE" :
                prefValue = String.valueOf(weatherUnitPressureParam);
                break;
            case "WEATHER_VALUE_PRESSURE" :
                prefValue = String.valueOf(weatherValuePressureParam);
                break;
            // GENRAL SETTING
            case "SETTING_UNIT_SPEED" :
                prefValue = String.valueOf(settingUnitSpeedParam);
                break;
            case "SETTING_UNIT_DISTANCE" :
                prefValue = String.valueOf(settingUnitDistanceParam);
                break;
            case "SETTING_UNIT_MEASURE" :
                prefValue = String.valueOf(settingUnitMeasureParam);
                break;
            //RIFLE SETTING
            case "BULLET_UNIT_SPEED" :
                prefValue = String.valueOf(bulletUnitSpeedParam);
                break;
            case "BULLET_VALUE_SPEED" :
                prefValue = String.valueOf(bulletValueSpeedParam);
                break;
            case "BULLET_UNIT_WEIGHT" :
                prefValue = String.valueOf(bulletUnitWeightParam);
                break;
            case "BULLET_VALUE_WEIGHT" :
                prefValue = String.valueOf(bulletValueWeightParam);
                break;
            case "BULLET_UNIT_BC" :
                prefValue = String.valueOf(bulletUnitBCParam);
                break;
            case "BULLET_VALUE_BC" :
                prefValue = String.valueOf(bulletValueBCParam);
                break;
            case "BULLET_NAME" :
                prefValue = String.valueOf(bulletNameParam);
                break;
        }
        return prefValue;
    }

    //On enregistre les préférences de l'utilisateur pour une utilisation ultérieure
    public static boolean CommitPref()
    {
        boolean isOk = false;
        SharedPreferences.Editor editor2 = sharedpreferences.edit();
        editor2.putString(scopeUnitUD, scopeUnitUDParam);
        editor2.putString(scopeUnitLR, scopeUnitLRParam);
        editor2.putString(scopeValueUD, String.valueOf(scopeValueUDParam));
        editor2.putString(scopeValueLR, String.valueOf(scopeValueLRParam));
        editor2.putString(scopeUnitHeight, scopeUnitHeightParam);
        editor2.putString(scopeValueHeight, String.valueOf(scopeValueHeightParam));
        editor2.putString(scopeUnitZero, scopeUnitZeroParam);
        editor2.putString(scopeValueZero, String.valueOf(scopeValueZeroParam));
        //WEATHER
        editor2.putString(weatherUnitTemperature, weatherUnitTemperatureParam);
        editor2.putString(weatherValueTemperature, String.valueOf(weatherValueTemperatureParam));
        editor2.putString(weatherUnitHumidity, weatherUnitHumidityParam);
        editor2.putString(weatherValueHumidity, String.valueOf(weatherValueHumidityParam));
        editor2.putString(weatherUnitAltitude, weatherUnitAltitudeParam);
        editor2.putString(weatherValueAltitude, String.valueOf(weatherValueAltitudeParam));
        editor2.putString(weatherUnitPressure, weatherUnitPressureParam);
        editor2.putString(weatherValuePressure, String.valueOf(weatherValuePressureParam));
        //GENERAL SETTINGS
        editor2.putString(settingUnitSpeed, settingUnitSpeedParam);
        editor2.putString(settingUnitDistance, settingUnitDistanceParam);
        editor2.putString(settingUnitMeasure, settingUnitMeasureParam);
        //RIFLE SETTINGS
        editor2.putString(bulletUnitSpeed, bulletUnitSpeedParam);
        editor2.putString(bulletValueSpeed, String.valueOf(bulletValueSpeedParam));
        editor2.putString(bulletUnitWeight, bulletUnitWeightParam);
        editor2.putString(bulletValueWeight, String.valueOf(bulletValueWeightParam));
        editor2.putString(bulletUnitBC, bulletUnitBCParam);
        editor2.putString(bulletValueBC, String.valueOf(bulletValueBCParam));
        editor2.putString(bulletName, bulletNameParam);
        if(editor2.commit()) { isOk = true; }
        return isOk;
    }

    public static boolean SetPrefValue(String param, String valueKey)
    {
        boolean isok = false;
        switch(param) {
            case "SCOPE_UNIT_UD" :
                scopeUnitUDParam = valueKey;
                break;
            case "SCOPE_UNIT_LR" :
                scopeUnitLRParam = valueKey;
                break;
            case "SCOPE_VALUE_UD" :
                scopeValueUDParam = Float.parseFloat(valueKey);
                break;
            case "SCOPE_VALUE_LR" :
                scopeValueLRParam = Float.parseFloat(valueKey);
                break;
            case "SCOPE_UNIT_HEIGHT" :
                scopeUnitHeightParam = valueKey;
                break;
            case "SCOPE_UNIT_ZEROING" :
                scopeUnitZeroParam = valueKey;
                break;
            case "SCOPE_VALUE_HEIGHT" :
                scopeValueHeightParam = Float.parseFloat(valueKey);
                break;
            case "SCOPE_VALUE_DISTANCE" :
                scopeValueZeroParam = Float.parseFloat(valueKey);
                break;
            //WEATHER
            case "WEATHER_UNIT_TEMPERATURE" :
                weatherUnitTemperatureParam = valueKey;
                break;
            case "WEATHER_VALUE_TEMPERATURE" :
                weatherValueTemperatureParam = Float.parseFloat(valueKey);
                break;
            case "WEATHER_UNIT_HUMIDITY" :
                weatherUnitHumidityParam = valueKey;
                break;
            case "WEATHER_VALUE_HUMIDITY" :
                weatherValueHumidityParam = Float.parseFloat(valueKey);
                break;
            case "WEATHER_UNIT_ALTITUDE" :
                weatherUnitAltitudeParam = valueKey;
                break;
            case "WEATHER_VALUE_ALTITUDE" :
                weatherValueAltitudeParam = Float.parseFloat(valueKey);
                break;
            case "WEATHER_UNIT_PRESSURE" :
                weatherUnitPressureParam = valueKey;
                break;
            case "WEATHER_VALUE_PRESSURE" :
                weatherValuePressureParam = Float.parseFloat(valueKey);
                break;
            //GENERAL SETTINGS
            case "SETTING_UNIT_SPEED" :
                settingUnitSpeedParam = valueKey;
                break;
            case "SETTING_UNIT_DISTANCE" :
                settingUnitDistanceParam = valueKey;
                break;
            case "SETTING_UNIT_MEASURE" :
                settingUnitMeasureParam = valueKey;
                break;
            //RIFLE SETTING
            case "BULLET_UNIT_SPEED" :
                bulletUnitSpeedParam = valueKey;
                break;
            case "BULLET_VALUE_SPEED" :
                bulletValueSpeedParam = Float.parseFloat(valueKey);
                break;
            case "BULLET_UNIT_WEIGHT" :
                bulletUnitWeightParam = valueKey;
                break;
            case "BULLET_VALUE_WEIGHT" :
                bulletValueWeightParam = Float.parseFloat(valueKey);
                break;
            case "BULLET_UNIT_BC" :
                bulletUnitBCParam = valueKey;
                break;
            case "BULLET_VALUE_BC" :
                bulletValueBCParam = Float.parseFloat(valueKey);
                break;
            case "BULLET_NAME" :
                bulletNameParam = valueKey;
                break;
        }
        if(CommitPref()) { isok=true; }
        return isok;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // Inflate the menu; this adds items to the action bar if it is present.
        menu.add(0, 1, 1, menuIconWithText(getResources().getDrawable(R.drawable.ic_gensetting), getResources().getString(R.string.menu_generalSetting)));
        menu.add(0, 2, 2, menuIconWithText(getResources().getDrawable(R.drawable.ic_scope), getResources().getString(R.string.menu_scopeSetting)));
        menu.add(0, 3, 3, menuIconWithText(getResources().getDrawable(R.drawable.ic_rifle), getResources().getString(R.string.menu_rifleSetting)));
        menu.add(0, 4, 4, menuIconWithText(getResources().getDrawable(R.drawable.ic_weather), getResources().getString(R.string.menu_weatherSetting)));
        menu.add(0, 5, 5, menuIconWithText(getResources().getDrawable(R.drawable.ic_donate), getResources().getString(R.string.menu_donate)));
        menu.add(0, 6, 6, menuIconWithText(getResources().getDrawable(R.drawable.ic_about), getResources().getString(R.string.menu_about)));
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        switch(id) {
            case 1:
                navController.navigate(R.id.action_Go_to_GeneralSettings);
                break;
            case 2 :
                navController.navigate(R.id.action_MenuHome_to_SettingScope);
                break;
            case 3 :
                navController.navigate(R.id.action_MenuHome_to_SettingRifle);
                break;
            case 4 :
                navController.navigate(R.id.action_MenuHome_to_SettingWeather);
                break;
            case 5 :
                navController.navigate(R.id.action_MenuHome_to_Donate);
                break;
            case 6 :
                navController.navigate(R.id.action_MenuHome_to_About);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}