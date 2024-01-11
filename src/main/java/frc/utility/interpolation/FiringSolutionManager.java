package frc.utility.interpolation;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.fasterxml.jackson.databind.ObjectMapper;

public class FiringSolutionManager {
  private ArrayList<GenericFiringSolution> solutions;
  private Comparator<GenericFiringSolution> comparator;
  private ObjectMapper mapper = new ObjectMapper();

  public FiringSolutionManager(String jsonName, Comparator<GenericFiringSolution> comparator) {
    this.comparator = comparator;
    // try to read from a json file, otherwise start with an empty list
    if (jsonName != null) {
      try {
        solutions =
            mapper.readValue(new File("/home/lvuser/deploy/" + jsonName + ".json"), solutions.getClass());
      } catch (Exception e) {
        solutions = new ArrayList<>();
      }
      }
      Collections.sort(solutions, comparator);
  }

  public void addSolution(GenericFiringSolution add) {
    // returns (-(insertion point) - 1) if not found
    int i = Collections.binarySearch(solutions, add, comparator);
    if (i >= 0) {
      // do not overwrite existing solution
      return;
    } else {
      // insert at insertion point
      int insertionPoint = -(i + 1);
      solutions.add(insertionPoint, add);
    }
  }

  public GenericFiringSolution calcSolutionComponents1D(double shotMag, double shotDeg) {
    int i = Collections.binarySearch(solutions, new GenericFiringSolution(shotMag, shotDeg), comparator);

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
      return GenericFiringSolution.interpolate1D(shotMag, shotDeg, solutions.get(upperIdx), solutions.get(lowerIdx));
    }
  }

  // write to a json that can later be read from
  public void writeSolutions(String fileName) {
    try {
      mapper.writeValue(new File("/media/sda1/" + fileName + ".json"), solutions);
    } catch (Exception e) {
      return;
    }
  }
}
