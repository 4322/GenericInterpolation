import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.EnumMap;

import org.junit.jupiter.api.*;

import frc.utility.interpolation.FiringSolution;
import frc.utility.interpolation.FiringSolutionComparator1D;
import frc.utility.interpolation.FiringSolutionManager;
import frc.utility.interpolation.GenericFiringSolution;

public class FiringSolutionManagerTest {

  FiringSolutionManager manager;

  @BeforeEach
  public void setup() {
    manager = new FiringSolutionManager(null, new FiringSolutionComparator1D());
  }
 
  @AfterEach
  public void shutdown() {
    return;
  }
  
  @Test
  public void testInterpolate1D() {
    FiringSolution solution1 = new FiringSolution(20, 20, 20, 20);
    FiringSolution solution2 = new FiringSolution(30, 30, 30, 30);
    FiringSolution solution3 = new FiringSolution(40, 40, 40, 40);

    manager.addSolution(solution1);
    manager.addSolution(solution2);
    manager.addSolution(solution3);

    FiringSolution calcSolution1 = 
  }

  @Test
  public void testCompareTo() {
    
  }

}