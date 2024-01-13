package frc.utility.interpolation;

import java.util.Comparator;

public class FiringSolutionComparator1D implements Comparator<FiringSolution> {
    public int compare(FiringSolution o1, FiringSolution o2) {
        if (o1.getShotMag() < o2.getShotMag()) {
            return -1;
        } else if (o1.getShotMag() == o2.getShotMag()) {
            return 0;
        } else {
            return 1;
        }
    };
}
