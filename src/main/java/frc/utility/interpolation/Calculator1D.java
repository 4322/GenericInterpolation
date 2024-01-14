package frc.utility.interpolation;

import java.util.ArrayList;

public class Calculator1D<S extends GenericFiringSolution> implements GenericCalculator<S> {
  public ArrayList<S> find(ArrayList<S> solutions) {
    
  }

  public ArrayList<Double> calculate(double currentMag, double currentDeg,
      ArrayList<S> foundSolutions) {
    ArrayList<Double> s1ComponentList = foundSolutions.get(0).toComponentList();
    ArrayList<Double> s2ComponentList = foundSolutions.get(1).toComponentList();
    ArrayList<Double> calculatedComponentList = new ArrayList<>();
    // linear interpolation calculation referenced from
    // https://github.com/Team3309/FRC2020/blob/master/src/main/java/frc/robot/util/FiringSolutionManager.java
    double x = (currentMag - foundSolutions.get(0).getShotMag())
        / (foundSolutions.get(1).getShotMag() - foundSolutions.get(0).getShotMag());
    for (int i = 0; i < s1ComponentList.size(); i++) {
      calculatedComponentList.add(s2ComponentList.get(i) * x + s1ComponentList.get(i) * (1 - x));
    }
    return calculatedComponentList;
  }
}
