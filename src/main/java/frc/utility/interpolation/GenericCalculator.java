package frc.utility.interpolation;

import java.util.ArrayList;

public abstract class GenericCalculator<S extends GenericFiringSolution> {
  public abstract void addSolution(S solution);

  public abstract ArrayList<Double> calculate(double currentMag, double currentDeg);
}
