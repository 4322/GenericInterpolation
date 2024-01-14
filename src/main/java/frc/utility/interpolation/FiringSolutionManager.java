package frc.utility.interpolation;

public interface FiringSolutionManager<S extends GenericFiringSolution> {
  public S calcFiringSolution(double currentMag, double currentDeg);
}
