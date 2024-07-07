package com.example.balistikcalculater;

public class MesureConversion {
    public static double cmToInch(double cm)
    {
        double inch = cm/2.54;
        return inch;
    }

    public static double kmhTomph(double kmh) { return kmh/1.609; }

    public static double celsiusToFarenheit(double celsius) { return (celsius * 1.8) + 32; }

    public static double inchToCm(double inch)
    {
        return inch*2.54;
    }

    public static double msTofs(double ms)
    {
        return ms*3.2808;
    }

    public static double fsToMs(double fs)
    {
        return fs/3.2808;
    }

    public static double yardstoM(double yards)
    {
        return yards/1.094;
    }

    public static double mToYards(double m)
    {
        return m*1.094;
    }

    public static double mToFeet(double m)
    {
        return m*3.281;
    }

    public static int incrementeYardsToMeter(double yards, int incrementation)
    {
        int incrementationMetre = (int)Math.abs((yards*10)/1.094);
        return incrementationMetre;
    }

    public static double hPAToinHg (double hpa)
    {
        return hpa/33.8639;
    }
}