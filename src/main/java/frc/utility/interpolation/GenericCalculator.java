package frc.utility.interpolation;

import java.util.ArrayList;

public interface GenericCalculator<S extends GenericFiringSolution> {
  public ArrayList<S> find(ArrayList<S> solutions);
  public ArrayList<Double> calculate(double currentMag, double currentDeg, ArrayList<S> foundSolutions);
}
