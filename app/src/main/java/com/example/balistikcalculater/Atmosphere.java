package com.example.balistikcalculater;

public class Atmosphere {

    public static double calcFR(double temperature, double pressure, double relativeHumidity) {
        double VPw = 4e-6 * Math.pow(temperature, 3) - 0.0004 * Math.pow(temperature, 2) + 0.0234 * temperature - 0.2517;
        return 0.995 * (pressure / (pressure - (0.3783) * (relativeHumidity) * VPw));
    }

    public static double calcFP(double pressure) {
        double Pstd = 29.53; // in-hg
        return (pressure - Pstd) / Pstd;
    }

    public static double calcFT(double temperature, double altitude) {
        double Tstd = -0.0036 * altitude + 59;
        return (temperature - Tstd) / (459.6 + Tstd);
    }

    public static double calcFA(double altitude) {
        return 1 / (-4e-15 * Math.pow(altitude, 3) + 4e-10 * Math.pow(altitude, 2) - 3e-5 * altitude + 1);
    }

    public static double atmCorrect(double dragCoefficient, double altitude, double barometer, double temperature, double relativeHumidity) {
        double FA = calcFA(altitude);
        double FT = calcFT(temperature, altitude);
        double FR = calcFR(temperature, barometer, relativeHumidity);
        double FP = calcFP(barometer);

        // Calculate the atmospheric correction factor
        double CD = (FA * (1 + FT - FP) * FR);
        return dragCoefficient * CD;
    }
}
