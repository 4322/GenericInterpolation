package frc.utility.interpolation;

import java.util.ArrayList;

public class GenericFiringSolution {
    // DO NOT USE THIS CLASS BY ITSELF!!! Write a class extending this one
    protected double shotMag;
    protected double shotDeg;

    protected GenericFiringSolution(double shotMag, double shotDeg) {
        this.shotMag = shotMag; 
        this.shotDeg = shotDeg;
    }

    protected ArrayList<Double> toArrayList() { return null; }

    protected GenericFiringSolution fromArrayList(double shotMag, double shotDeg, ArrayList<Double> components) { return null; }

    protected static GenericFiringSolution interpolate1D(double currentMag, double currentDeg, GenericFiringSolution solution, GenericFiringSolution solution2) {
        ArrayList<Double> solutionComponents = solution.toArrayList();
        ArrayList<Double> solution2Components = solution2.toArrayList();
        ArrayList<Double> calculatedSolutionComponents = new ArrayList<>();

        // linear interpolation calculation referenced from
        // https://github.com/Team3309/FRC2020/blob/master/src/main/java/frc/robot/util/FiringSolutionManager.java
        double d = (currentMag - solution.getShotMag()) / (solution2.getShotMag() - solution.getShotMag());

        for (int i = 0; i < solutionComponents.size(); i++) {
            calculatedSolutionComponents.add(solution2Components.get(i) * d + solutionComponents.get(i) * (1-d));
        }

        GenericFiringSolution calculatedSolution = solution.fromArrayList(currentMag, currentDeg, calculatedSolutionComponents);

        return calculatedSolution;
    }

    public double getShotMag() {
        return shotMag;
    }

    public double getShotDeg() {
        return shotDeg;
    }
}