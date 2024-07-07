package com.example.balistikcalculater;

public class AngleConversion {

    // Conversion de degrés en minutes d'arc
    public static double degToMOA(double deg) {
        return deg * 60;
    }

    // Conversion de degrés en radians
    public static double degToRad(double deg) {
        return deg * Math.PI / 180;
    }

    // Conversion de minutes d'arc en degrés
    public static double moaToDeg(double moa) {
        return moa / 60;
    }

    // Conversion de minutes d'arc en radians
    public static double moaToRad(double moa) {
        return moa / 60 * Math.PI / 180;
    }

    // Conversion de radians en degrés
    public static double radToDeg(double rad) {
        return rad * 180 / Math.PI;
    }

    // Conversion de radians en minutes d'arc
    public static double radToMOA(double rad) {
        return rad * 60 * 180 / Math.PI;
    }
}
