package frc.utility.interpolation;

import java.util.ArrayList;

public abstract class GenericSolution {
    // DO NOT USE THIS CLASS BY ITSELF!!! Write a class extending this one
    protected double shotMag;
    protected double shotDeg;

    protected abstract ArrayList<Double> toArrayList();
    protected abstract GenericSolution fromArrayList(double shotMag, double shotDeg, ArrayList<Double> components);

    protected static ArrayList<Double> interpolate1D(double currentMag, double currentDeg, GenericSolution solution, GenericSolution solution2) {
        ArrayList<Double> solutionComponents = solution.toArrayList();
        ArrayList<Double> solution2Components = solution2.toArrayList();
        ArrayList<Double> calculatedSolutionComponents = new ArrayList<>();

        // linear interpolation calculation referenced from
        // https://github.com/Team3309/FRC2020/blob/master/src/main/java/frc/robot/util/FiringSolutionManager.java
        double d = (currentMag - solution.getShotMag()) / (solution2.getShotMag() - solution.getShotMag());

        for (int i = 0; i < solutionComponents.size(); i++) {
            calculatedSolutionComponents.add(solution2Components.get(i) * d + solutionComponents.get(i) * (1-d));
        }

        return calculatedSolutionComponents;
    }

    public double getShotMag() {
        return shotMag;
    }

    public double getShotDeg() {
        return shotDeg;
    }
}