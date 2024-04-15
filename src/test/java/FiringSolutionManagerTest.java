import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.*;

import frc.robot.shooting.FiringSolution;
import frc.robot.shooting.FiringSolutionManager;
import frc.utility.interpolation.Calculator1D;

public class FiringSolutionManagerTest {
  @Test
  public void testFromJson_NormalSolution() {
    FiringSolutionManager manager = FiringSolutionManager.createFromJson(new Calculator1D<>(), getClass().getResource("TestSolutions.json").getFile());

    FiringSolution calcSolution1 = manager.calcSolution(7.5, 0);
    assertEquals(calcSolution1.getShotMag(), 7.5);
    assertEquals(calcSolution1.getShotDeg(), 0);
    assertEquals(calcSolution1.getFlywheelSpeed(), 20);
    assertEquals(calcSolution1.getShotAngle(), 20);

    FiringSolution calcSolution2 = manager.calcSolution(15, 0);
    assertEquals(calcSolution2.getShotMag(), 15);
    assertEquals(calcSolution2.getShotDeg(), 0);
    assertEquals(calcSolution2.getFlywheelSpeed(), 35);
    assertEquals(calcSolution2.getShotAngle(), 35);

    FiringSolution calcSolution3 = manager.calcSolution(25, 0);
    assertEquals(calcSolution3.getShotMag(), 25);
    assertEquals(calcSolution3.getShotDeg(), 0);
    assertEquals(calcSolution3.getFlywheelSpeed(), 45);
    assertEquals(calcSolution3.getShotAngle(), 45);
  }
}