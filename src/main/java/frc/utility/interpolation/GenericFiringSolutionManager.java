package frc.utility.interpolation;

public abstract class GenericFiringSolutionManager<S extends GenericFiringSolution> {
  public abstract S calcSolution(double currentMag, double currentDeg);
}
