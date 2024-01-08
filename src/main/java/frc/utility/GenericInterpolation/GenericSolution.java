package frc.utility.GenericInterpolation;

import java.util.EnumMap;

public class GenericSolution<K extends Enum<K>> implements Comparable<GenericSolution<K>> {
    // A generic solution to be extrapolated. Can have an undefined number
    // of numerical (double) parameters as represented by an enum.
    private double setpoint;
    private EnumMap<K, Double> params;

    // Parameter map must already be populated with parameters
    public GenericSolution(double setpoint, EnumMap<K, Double> params) {
        this.setpoint = setpoint;
        this.params = params;
    }

    public double getSetpoint() {
        return setpoint;
    }

    public double getParameter(K enumValue) {
        return params.get(enumValue);
    }

    // Return the entire enumMap. Only should be used by Generic Interpolator.
    protected EnumMap<K, Double> getAllParameters() {
        return params;
    }

    // Custom method to compare solutions, so that they can be ordered/binary searched.
    @Override
    public int compareTo(GenericSolution<K> solution) {
        if (solution.getSetpoint() > setpoint) {
            return -1;
        } else if (solution.getSetpoint() == setpoint) {
            return 0;
        } else {
            return 1;
        }
    }
}