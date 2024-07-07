package com.example.balistikcalculater;

public class Windage {
    private AngleConversion conversionAngle = new AngleConversion();

    double Windage(double WindSpeed, double Vi, double xx, double t){
        double Vw = WindSpeed*17.60; // Convert to inches per second.
        return (Vw*(t-xx/Vi));
    }
    
    
    // Headwind is positive at WindAngle=0
    double HeadWind(double WindSpeed, double WindAngle){
        double Wangle = conversionAngle.degToRad(WindAngle);
        return (Math.cos(Wangle)*WindSpeed);
    }
    
    // Positive is from Shooter's Right to Left (Wind from 90 degree)
    double CrossWind(double WindSpeed, double WindAngle){
        double Wangle = conversionAngle.degToRad(WindAngle);
        return (Math.sin(Wangle)*WindSpeed);
    }
}
