package com.example.balistikcalculater;

import android.database.MatrixCursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.balistikcalculater.databinding.CalculateTableBinding;
import com.example.balistikcalculater.databinding.MenuHomeBinding;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class CalculateTable extends Fragment {
    private CalculateTableBinding binding;
    private MenuHomeBinding binding2;

    //Création du convertisseur d'unité
    private MesureConversion converter = new MesureConversion();

    //Import des objets de classes
    private Zero angleZero = new Zero();
    private Solve solutionSolve = new Solve();
    private Retrieve retrieveValue = new Retrieve();
    private Atmosphere atmocor = new Atmosphere();

    //Variable globale pour récupérer la solution
    double sln[][] = new double[2003][9];

    //Variables globales des paramètres
    //SETTINGS RIFLE
    private String bulletUnitSpeed;
    private double bulletValueSpeed;
    private String bulletUnitWeight;
    private double bulletValueWeight;
    private String bulletUnitBC;
    private double bulletValueBC;
    private String bulletName;
    //GEN SETTINGS
    private String settingUnitSpeed;
    private String settingUnitMeasure;
    private String settingUnitDistance;
    //SCOPE SETTINGS
    private String scopeUnitUD;
    private String scopeUnitLR;
    private double scopeValueUD;
    private double scopeValueLR;
    private String scopeUnitHeight;
    private double scopeValueHeight;
    private String scopeUnitZero ;
    private double scopeValueZero;
    //WEATHER SETTINGS
    private String weatherUnitTemperature;
    private double weatherValueTemperature;
    private String weatherUnitHumidity;
    private double weatherValueHumidity;
    private String weatherUnitAltitude;
    private double weatherValueAltitude;
    private String weatherUnitPressure;
    private double weatherValuePressure;
    //HOME MENU VALUE
    private double homeStepDistance;
    private double homeMaxDistance;
    private double homeAngle;
    private double homeWindAngle;
    private double homeWindSpeed;

    private int convertBC(String bc)
    {
        int bcInt = 7;
        if(bc.equals("G1"))
        {
            bcInt = 1;
        }
        return bcInt;
    }

    //Format des décimals
    DecimalFormat df = new DecimalFormat("0.00");

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        //Import des valeurs des paramètres
        //VALEURS WEATHER
        weatherValueTemperature = Double.parseDouble(MainActivity.GetPrefValue("WEATHER_VALUE_TEMPERATURE"));
        weatherValueHumidity = Double.parseDouble(MainActivity.GetPrefValue("WEATHER_VALUE_HUMIDITY"));
        weatherValueAltitude = Double.parseDouble(MainActivity.GetPrefValue("WEATHER_VALUE_ALTITUDE"));
        weatherValuePressure = Double.parseDouble(MainActivity.GetPrefValue("WEATHER_VALUE_PRESSURE"));
        weatherUnitTemperature = MainActivity.GetPrefValue("WEATHER_UNIT_TEMPERATURE");
        weatherUnitHumidity = MainActivity.GetPrefValue("WEATHER_UNIT_HUMIDITY");
        weatherUnitAltitude = MainActivity.GetPrefValue("WEATHER_UNIT_ALTITUDE");
        weatherUnitPressure = MainActivity.GetPrefValue("WEATHER_UNIT_PRESSURE");

        //VALEURS GENERALES
        settingUnitSpeed = MainActivity.GetPrefValue("SETTING_UNIT_SPEED");
        settingUnitMeasure= MainActivity.GetPrefValue("SETTING_UNIT_MEASURE");
        settingUnitDistance= MainActivity.GetPrefValue("SETTING_UNIT_DISTANCE");

        //VALEURS SCOPES
        scopeValueUD = Double.parseDouble(MainActivity.GetPrefValue("SCOPE_VALUE_UD"));
        scopeValueLR = Double.parseDouble(MainActivity.GetPrefValue("SCOPE_VALUE_LR"));
        scopeValueHeight = Double.parseDouble(MainActivity.GetPrefValue("SCOPE_VALUE_HEIGHT"));
        scopeValueZero = Double.parseDouble(MainActivity.GetPrefValue("SCOPE_VALUE_DISTANCE"));
        scopeUnitUD = MainActivity.GetPrefValue("SCOPE_UNIT_UD");
        scopeUnitLR = MainActivity.GetPrefValue("SCOPE_UNIT_LR");
        scopeUnitHeight = MainActivity.GetPrefValue("SCOPE_UNIT_HEIGHT");
        scopeUnitZero = MainActivity.GetPrefValue("SCOPE_UNIT_ZEROING");

        //VALEURS RIFLES
        bulletValueSpeed = Double.parseDouble(MainActivity.GetPrefValue("BULLET_VALUE_SPEED"));
        bulletValueWeight = Double.parseDouble(MainActivity.GetPrefValue("BULLET_VALUE_WEIGHT"));
        bulletValueBC = Double.parseDouble(MainActivity.GetPrefValue("BULLET_VALUE_BC"));
        bulletUnitSpeed = MainActivity.GetPrefValue("BULLET_UNIT_SPEED");
        bulletUnitWeight = MainActivity.GetPrefValue("BULLET_UNIT_WEIGHT");
        bulletUnitBC = MainActivity.GetPrefValue("BULLET_UNIT_BC");
        bulletName = MainActivity.GetPrefValue("BULLET_NAME");

        //Ajout des binding
        binding = CalculateTableBinding.inflate(inflater, container, false);
        binding2 = MenuHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //VALEURS DU HOME MENU
        homeStepDistance = Double.parseDouble(binding2.tfIncrementation.getText().toString());
        homeMaxDistance = Double.parseDouble(binding2.tfDistanceFin.getText().toString());
        homeAngle = Double.parseDouble(binding2.tfAngle.getText().toString());
        homeWindAngle = Double.parseDouble(binding2.tfWindAngle.getText().toString());
        homeWindSpeed = Double.parseDouble(binding2.tfWindSpeed.getText().toString());

        //Conversion des valeurs si nécessaire
        if(bulletUnitSpeed.equals("M/S")) { bulletValueSpeed = converter.msTofs(bulletValueSpeed); }
        if(scopeUnitHeight.equals("CM")) { scopeValueHeight = converter.cmToInch(scopeValueHeight); }
        if(scopeUnitZero.equals("M")) { scopeValueZero = converter.mToYards(scopeValueZero); }
        if(!binding2.toggleWindSpeed.isChecked()) { homeWindSpeed = converter.kmhTomph(homeWindSpeed); }
        if(weatherUnitAltitude.equals("M")) { weatherValueAltitude = converter.mToFeet(weatherValueAltitude); }
        if(weatherUnitPressure.equals("HPA")) { weatherValuePressure = converter.hPAToinHg(weatherValuePressure); }
        if(weatherUnitTemperature.equals("°C")) { weatherValueTemperature = converter.celsiusToFarenheit(weatherValueTemperature); }

        //Calcul de la solution
        //Conversion du BC en fonction des caractéristiques météo
        bulletValueBC=atmocor.atmCorrect(bulletValueBC, weatherValueAltitude, weatherValuePressure, weatherValueTemperature,weatherValueHumidity/100);
        //Calcul solution
        double zeroangle=0;
        zeroangle= angleZero.ZeroAngle(convertBC(bulletUnitBC),bulletValueBC,bulletValueSpeed, scopeValueHeight,scopeValueZero,0);
        int k= solutionSolve.solveAll(convertBC(bulletUnitBC),bulletValueBC,bulletValueSpeed,scopeValueHeight,homeAngle,zeroangle,homeWindSpeed,homeWindAngle,sln);
        //On récupère la solution
        sln = solutionSolve.getSolution();

        int s=0;
        int xValue;
        double yValue;
        int incrementation = (int) homeStepDistance;

        // données du tableau
        final String [] col1 = new String[(1000/incrementation)+10];
        final String [] col2 = new String[(1000/incrementation)+10];
        final String [] col3 = new String[(1000/incrementation)+10];

        //On enregistre les données dans le fichier tableau*
        String myHtmlString = "";
        myHtmlString += "<html><body style='background-color:%23ffffff;'><table><thead style='background-color:%234a4a4a;color:%23ff8615;'><tr><th scope='col'>Range</th><th scope='col'>Speed</th><th scope='col'>Vert. Correction</th><th scope='col'>Vert. MOA</th></thead><tbody>";
        for (s=0;s<=100;s++){
            yValue = retrieveValue.getPath(sln,s*incrementation);
            myHtmlString += "<tr><th scope='row'>" + String.valueOf(s*incrementation) + " yds</th>";
            myHtmlString += "<td>" + df.format(converter.fsToMs(retrieveValue.getVelocity(sln,s*incrementation))) + " m/s</td>";
            myHtmlString += "<td>" + df.format(converter.inchToCm(yValue)) + " cm</td>";
            myHtmlString += "<td>" + df.format(retrieveValue.getMOA(sln,s*incrementation)) + " moa</td></tr>";
            //System.out.println("X: " + s*incrementation + " || Y: " + df.format(convertMesure.inchToCm(yValue)) + " || MOA: " + df.format(retrieveValue.getMOA(sln,s*incrementation)) + " || Vitesse: " + df.format(convertMesure.fsToMs(retrieveValue.getVelocity(sln,s*incrementation))));
        }
        myHtmlString += "</tbody></table></body></html>";
        binding.webView1.loadData(myHtmlString, "text/html", "utf-8");
        binding.webView1.setBackgroundColor(Color.WHITE);
        binding.webView1.getSettings().setUseWideViewPort(true);

        /*TableLayout table = (TableLayout) binding.idTable;
        TableRow row; // création d'un élément : ligne
        TextView tv1,tv2,tv3; // création des cellules

        // pour chaque ligne
        for(int i=0;i<col1.length;i++) {
            row = new TableRow(this.getContext()); // création d'une nouvelle ligne

            tv1 = new TextView(this.getContext()); // création cellule
            tv1.setText(col1[i]); // ajout du texte
            tv1.setGravity(Gravity.CENTER); // centrage dans la cellule
            // adaptation de la largeur de colonne à l'écran :
            tv1.setLayoutParams( new TableRow.LayoutParams( 0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1 ) );

            // idem 2ème cellule
            tv2 = new TextView(this.getContext());
            tv2.setText(col2[i]);
            tv2.setGravity(Gravity.CENTER);
            tv2.setLayoutParams( new TableRow.LayoutParams( 0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1 ) );

            // idem 3ème cellule
            tv3 = new TextView(this.getContext());
            tv3.setText(col3[i]);
            tv3.setGravity(Gravity.CENTER);
            tv3.setLayoutParams( new TableRow.LayoutParams( 0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1 ) );

            // ajout des cellules à la ligne
            row.addView(tv1);
            row.addView(tv2);
            row.addView(tv3);

            // ajout de la ligne au tableau
            table.addView(row);
        }*/
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}