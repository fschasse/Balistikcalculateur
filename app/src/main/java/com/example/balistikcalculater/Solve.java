package com.example.balistikcalculater;

import java.util.Arrays;

public class Solve {

    //Definition des constantes
    private static final double GRAVITY = (-32.194);
    private static Retard retardValue = new Retard();
    private static double solutionStore[][] = new double[2003][9];
    private static AngleConversion calculAngle = new AngleConversion();
    private static Windage calculDWind = new Windage();

    public double[][] getSolution()
    {
        return solutionStore;
    }

    public static int solveAll(int dragFunction, double dragCoefficient, double Vi, double sightHeight, double shootingAngle, double ZAngle, double windSpeed, double windAngle, double[][] solution) {
        double t=0;
        double dt=0.5/Vi;
        double v=0;
        double vx=0, vx1=0, vy=0, vy1=0;
        double dv=0, dvx=0, dvy=0;
        double x=0, y=0;
        
        double headwind=calculDWind.HeadWind(windSpeed, windAngle);
        double crosswind=calculDWind.CrossWind(windSpeed, windAngle);
        
        double Gy=GRAVITY*Math.cos(calculAngle.degToRad((shootingAngle + ZAngle)));
        double Gx=GRAVITY*Math.sin(calculAngle.degToRad((shootingAngle + ZAngle)));
    
        vx=Vi*Math.cos(calculAngle.degToRad(ZAngle));
        vy=Vi*Math.sin(calculAngle.degToRad(ZAngle));
    
        y=-sightHeight/12;

        int n=0;
        for (t=0;;t=t+dt){
            // Integrate trajectory
            vx1=vx; 
            vy1=vy;	
            v=Math.pow(Math.pow(vx,2)+Math.pow(vy,2),0.5);
            dt=0.5/v;
        
            // Compute acceleration using the drag function retardation	
            dv = retardValue.retard(dragFunction,dragCoefficient,v+headwind);		
            dvx = -(vx/v)*dv;
            dvy = -(vy/v)*dv;

            // Compute velocity, including the resolved gravity vectors.	
            vx=vx + dt*dvx + dt*Gx;
            vy=vy + dt*dvy + dt*Gy;

            if (x/3>=n){
                // Store solutions
                solution[n][0] = x/3;// Range in yds_
                solution[n][1] = y*12;// Path in inches
                solution[n][2] = -calculAngle.radToMOA(Math.atan(y/x));// Correction in MOA
                solution[n][3] = t+dt;// Time in s
                solution[n][4] = calculDWind.Windage(crosswind,Vi,x,t+dt);// Windage in inches
                solution[n][5] = calculAngle.radToMOA(Math.atan(solution[n][4]));// Windage in MOA
                solution[n][6] = v;// Velocity (combined)
                solution[n][7] = vx;// Velocity (x)
                solution[n][8] = vy;// Velocity (y)
                n++;
            }

            // Compute position based on average velocity.
            x=x+dt*(vx+vx1)/2;
            y=y+dt*(vy+vy1)/2;
            
            if (Math.abs(vy)>Math.abs(3*vx)) break;
            if (n>=2000) break;
        }
        solutionStore = solution;
        return n;
    }
}
