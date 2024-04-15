package frc.robot.shooting;

import java.util.ArrayList;

import frc.utility.interpolation.GenericFiringSolution;

public class FiringSolution extends GenericFiringSolution {
  private final double shotMag;
  private final double shotDeg; // angle from head-on to target (top-down to field)
  private double flywheelSpeed;
  private double shotAngle; // angle at which we should shoot (for a hooded shooter, pivot, etc.)

  public FiringSolution(double shotMag, double shotDeg, double flywheelSpeed, double shotAngle) {
    this.shotMag = shotMag;
    this.shotDeg = shotDeg;
    this.flywheelSpeed = flywheelSpeed;
    this.shotAngle = shotAngle;
  }

  protected FiringSolution(double shotMag, double shotDeg, ArrayList<Double> componentList) {
    this.shotMag = shotMag;
    this.shotDeg = shotDeg;
    this.flywheelSpeed = componentList.get(0);
    this.shotAngle = componentList.get(1);
  }

  // not to be used for calculations
  protected FiringSolution(double flywheelSpeed, double shotAngle) {
    this.shotMag = 0;
    this.shotDeg = 0;
    this.flywheelSpeed = flywheelSpeed;
    this.shotAngle = shotAngle;
  }

  // for loading from json
  private FiringSolution() {
    this.shotMag = 0;
    this.shotDeg = 0;
    this.flywheelSpeed = 0;
    this.shotAngle = 0;
  }

  @Override
  protected ArrayList<Double> toComponentList() {
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

  public void setFlywheelSpeed(double speed) {
    flywheelSpeed = speed;
  }

  public void setShotAngle(double angle) {
    shotAngle = angle;
  }
}
