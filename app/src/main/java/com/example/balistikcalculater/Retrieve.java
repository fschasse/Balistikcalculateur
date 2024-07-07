package com.example.balistikcalculater;

public class Retrieve {
    //Definition des constantes
    private static final int __BCOMP_MAXRANGE__ = 90;

    public static double getRange(double[][] sln, int yardage) {
        if (yardage < 2001) {
            return sln[yardage][0];
        } else {
            return 0;
        }
    }

    public static double getPath(double[][] sln, int yardage) {
        if (yardage < 2001) {
            return sln[yardage][1];
        } else {
            return 0;
        }
    }

    public static double getMOA(double[][] sln, int yardage) {
        if (yardage == 0) {
            return 0.0;
        }
        else if (yardage < 2001) {
            return sln[yardage][2];
        } else {
            return 0;
        }
    }

    public static double getTime(double[][] sln, int yardage) {
        if (yardage < 2001) {
            return sln[yardage][3];
        } else {
            return 0;
        }
    }

    public static double getWindage(double[][] sln, int yardage) {
        if (yardage < 2001) {
            return sln[yardage][4];
        } else {
            return 0;
        }
    }

    public static double getWindageMOA(double[][] sln, int yardage) {
        if (yardage < 2001) {
            return sln[yardage][5];
        } else {
            return 0;
        }
    }

    public static double getVelocity(double[][] sln, int yardage) {
        if (yardage < 2001) {
            return sln[yardage][6];
        } else {
            return 0;
        }
    }

    public static double getVx(double[][] sln, int yardage) {
        if (yardage < 2001) {
            return sln[yardage][7];
        } else {
            return 0;
        }
    }

    public static double getVy(double[][] sln, int yardage) {
        if (yardage < 2001) {
            return sln[yardage][8];
        } else {
            return 0;
        }
    }
}
