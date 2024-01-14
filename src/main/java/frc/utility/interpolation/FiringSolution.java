package frc.utility.interpolation;

import java.util.ArrayList;

public class FiringSolution extends GenericFiringSolution {
  private double shotMag;
  private double shotDeg; // angle from head-on to target (top-down to field)
  private double flywheelSpeed;
  private double shotAngle; // angle at which we should shoot (robot side view)

  public FiringSolution() {}

  public FiringSolution(double shotMag, double shotDeg, double flywheelSpeed, double shotAngle) {
    this.shotMag = shotMag;
    this.shotDeg = shotDeg;
    this.flywheelSpeed = flywheelSpeed;
    this.shotAngle = shotAngle;
  }

  @Override
  public ArrayList<Double> toComponentList() {
    ArrayList<Double> list = new ArrayList<>();
    list.add(flywheelSpeed);
    list.add(shotAngle);
    return list;
  }

  @Override
  public double getShotMag() {
    return shotMag;
  }

  @Override
  public double getShotDeg() {
    return shotDeg;
  }
  
  public double getFlywheelSpeed() {
    return flywheelSpeed;
  }

  public double getShotAngle() {
    return shotAngle;
  }
}