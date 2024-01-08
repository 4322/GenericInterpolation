package frc.utility.GenericInterpolation;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;

import com.fasterxml.jackson.databind.ObjectMapper;

public class GenericInterpolator<K extends Enum<K>> {
  // Linearly interpolate between two GenericSolutions.
  private ArrayList<GenericSolution<K>> solutions;
  // empty map is used to create a new EnumMap while remaining generic
  private EnumMap<K, Double> emptyMap;
  private ObjectMapper mapper = new ObjectMapper();
  private String jsonName;

  public GenericInterpolator(String jsonName, EnumMap<K, Double> emptyMap) {
    this.jsonName = jsonName;
    this.emptyMap = emptyMap;
    // try to read from a json file, otherwise start with an empty list
    try {
      solutions =
          mapper.readValue(new File("/home/lvuser/deploy/" + jsonName + ".json"), solutions.getClass());
    } catch (Exception e) {
      solutions = new ArrayList<>();
    }
  }

  public void addSolution(GenericSolution<K> add) {
    // returns (-(insertion point) - 1) if not found
    int i = Collections.binarySearch(solutions, add);
    if (i >= 0) {
      // do not overwrite existing solution
      return;
    } else {
      // insert at insertion point
      int insertionPoint = -(i + 1);
      solutions.add(insertionPoint, add);
    }
  }

  public GenericSolution<K> calcNewSolution(double currentSetpoint) {
    int i = Collections.binarySearch(solutions, new GenericSolution<>(currentSetpoint, null));

    if (i >= 0) {
      return solutions.get(i);
    } else if (i < -solutions.size()) {
      // upper bound
      return solutions.get(solutions.size() - 1);
    } else if (i == -1) {
      // lower bound
      return solutions.get(0);
    } else {
      // convert to insertion point (first greatest element in list)
      // since this is a unique solution, the index below this must be 
      // the first lowest element in the list.
      int upperIdx = -(i + 1);
      int lowerIdx = upperIdx - 1;

      // linear interpolation calculation referenced from
      // https://github.com/Team3309/FRC2020/blob/master/src/main/java/frc/robot/util/FiringSolutionManager.java
      double d = (currentSetpoint - solutions.get(lowerIdx).getSetpoint()) / 
              (solutions.get(upperIdx).getSetpoint() - solutions.get(lowerIdx).getSetpoint());

      EnumMap<K, Double> upperMap = solutions.get(upperIdx).getAllParameters();
      EnumMap<K, Double> lowerMap = solutions.get(lowerIdx).getAllParameters();
      EnumMap<K, Double> calcSolutionMap = new EnumMap<>(emptyMap);

      // interpolate value based on distance between 
      // first highest and first lowest point
      upperMap.forEach((key, value) -> {
          calcSolutionMap.put(key, value * d + lowerMap.get(key) * (1-d));
        }
      );

      return new GenericSolution<>(currentSetpoint, calcSolutionMap);
    }
  }

  // write to a json that can later be read from
  public void writeSolutions() {
    try {
      mapper.writeValue(new File("/media/sda1/" + jsonName + ".json"), solutions);
    } catch (Exception e) {
      return;
    }
  }
}
