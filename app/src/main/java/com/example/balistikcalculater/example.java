package com.example.balistikcalculater;

import java.text.DecimalFormat;
import java.util.Arrays;

public class example {

    //Variables globales
    private static int G1 = 1;
    private static int G2 = 2;
    private static int G3 = 3;
    private static int G4 = 4;
    private static int G5 = 5;
    private static int G6 = 6;
    private static int G7 = 7;
    private static int G8 = 8;

	public static void main(String[] args){
        //Import des objets de classes
        Zero angleZero = new Zero();
        Solve solutionSolve = new Solve();
        Retrieve retrieveValue = new Retrieve();
        MesureConversion convertMesure = new MesureConversion();
        Atmosphere atmocor = new Atmosphere();

        //Format des décimals
        DecimalFormat df = new DecimalFormat("0.00");

        int k=0;
        double sln[][] = new double[1000][9];// A pointer for receiving the ballistic solution.
        double bc=0.547; // The ballistic coefficient for the projectile.
        double v=convertMesure.msTofs(940); // Intial velocity, in ft/s
        double sh=convertMesure.cmToInch(5.0); // The Sight height over bore, in inches.
        double angle=0; // The shooting angle (uphill / downhill), in degrees.
        double zero=100; // The zero range of the rifle, in yards.
        double windspeed=0; // The wind speed in miles per hour.
        double windangle=0; // The wind angle (0=headwind, 90=right to left, 180=tailwind, 270/-90=left to right)
        
        // If we wish to use the weather correction features, we need to 
        // Correct the BC for any weather conditions.  If we want standard conditions,
        // then we can just leave this commented out.
        
        bc=atmocor.atmCorrect(bc, 0, 29.53, 59,.78);
        
        
        // First find the angle of the bore relative to the sighting system.
        // We call this the "zero angle", since it is the angle required to 
        // achieve a zero at a particular yardage.  This value isn't very useful
        // to us, but is required for making a full ballistic solution.
        // It is left here to allow for zero-ing at altitudes (bc) different from the
        // final solution, or to allow for zero's other than 0" (ex: 3" high at 100 yds)
        double zeroangle=0;
        zeroangle= angleZero.ZeroAngle(G1,bc,v, sh,zero,0);
        
        // Now we have everything needed to generate a full solution.
        // So we do.  The solution is stored in the pointer "sln" passed as the last argument.
        // k has the number of yards the solution is valid for, also the number of rows in the solution.
        k= solutionSolve.solveAll(G1,bc,v,sh,angle,zeroangle,windspeed,windangle,sln);

        //On récupère la solution
        sln = solutionSolve.getSolution();
        
        // Now print a simple chart of X / Y trajectory spaced at 10yd increments
        int s=0;
        int xValue;
        double yValue;
        for (s=0;s<=100;s++){
            //On converti s pour avoir des incrémentations en M
            xValue = (int)Math.abs(retrieveValue.getRange(sln, s*10));
            yValue = retrieveValue.getPath(sln,s*10);
            System.out.println("X: " + s*10 + " || Y: " + df.format(convertMesure.inchToCm(yValue)) + " || MOA: " + df.format(retrieveValue.getMOA(sln,s*10)) + " || Vitesse: " + df.format(convertMesure.fsToMs(retrieveValue.getVelocity(sln,s*10))));
        }
	}
}
