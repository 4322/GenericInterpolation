package frc.utility.interpolation;

import java.util.ArrayList;

public class FiringSolution extends GenericSolution {
    // An example FiringSolution. Typically this would go alongside whatever
    // subsystem you're using it for.
    private double flywheelSpeed;
    private double shotAngle;

    public FiringSolution(double shotMag, double shotDeg, double flywheelSpeed, double shotAngle) {
        super(shotMag, shotDeg);
        this.flywheelSpeed = flywheelSpeed;
        this.shotAngle = shotAngle;
    }

    protected ArrayList<Double> toArrayList() {
        ArrayList<Double> list = new ArrayList<>();
        list.add(flywheelSpeed);
        list.add(shotAngle);
        return list;
    }

    public FiringSolution fromArrayList(double shotMag, double shotDeg, ArrayList<Double> components) {
        return new FiringSolution(
            shotMag,
            shotDeg, 
            components.get(0), 
            components.get(1)
        );
    }

    public double getShotMag() {
        return shotMag;
    }

    public double getShotDeg() {
        return shotDeg;
    }

    public double getFlywheelSpeed() {
        return flywheelSpeed;
    }

    public double shotAngle() {
        return shotAngle;
    }
}