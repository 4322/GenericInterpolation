package frc.utility.interpolation;

import java.util.ArrayList;
import edu.wpi.first.math.geometry.Translation2d;

public class Calculator2D<S extends GenericFiringSolution> implements GenericCalculator<S> {
  private ArrayList<S> solutions;
  final double radius;
  double lastModifiedRadius;

  public Calculator2D(double searchRadius) {
    radius = searchRadius;
    lastModifiedRadius = radius;
  }

  public void init(ArrayList<S> solutionArrayList) {
    solutions = solutionArrayList;
  }

  public ArrayList<S> find(S inputsToFind) {
    final ArrayList<S> toReturn = new ArrayList<>();
    final Translation2d inputsCoords = new Translation2d(inputsToFind.getShotMag(), Math.abs(inputsToFind.getShotDeg()));
    double mult = 1; // start with normal radius and increase if nothing found

    // get all solutions within radius
    while (toReturn.size() == 0) {
      for (S solution : solutions) {
        if (inputsCoords.getDistance(new Translation2d(solution.getShotMag(), Math.abs(solution.getShotDeg()))) <= (radius * mult)) {
          toReturn.add(solution);
        }
      }
      mult += 1;
    }

    lastModifiedRadius = radius * (mult - 1);
    return toReturn;
  }

  public ArrayList<Double> calculate(double currentMag, double currentDeg,
      ArrayList<S> foundSolutions) {
    final Translation2d coords = new Translation2d(currentMag, Math.abs(currentDeg));
    final ArrayList<Double> calculatedComponentList = new ArrayList<>();
    final ArrayList<Double> weights = new ArrayList<>();
    double totalWeight = 0;

    // calculate weights and total weight for each solution
    for (S solution : solutions) {
      double weight = lastModifiedRadius - coords.getDistance(new Translation2d(solution.getShotMag(), Math.abs(solution.getShotDeg())));
      weights.add(weight);
      totalWeight += weight;
    }

    for (int i = 0; i < foundSolutions.get(0).toComponentList().size(); i++) {
      double componentValue = 0;
      for (int j = 0; j < foundSolutions.size(); j++) {
        componentValue += foundSolutions.get(j).toComponentList().get(i) * weights.get(j);
      }
      calculatedComponentList.add(componentValue/totalWeight);
    }

    return calculatedComponentList;
  }

  public void whenAdded() {
    return;
  }
}
