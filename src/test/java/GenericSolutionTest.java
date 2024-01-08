import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.EnumMap;

import org.junit.jupiter.api.*;

import frc.utility.GenericInterpolation.GenericSolution;

public class GenericSolutionTest {

  public enum TestEnum {
    parameter1, parameter2, parameter3
  }

  @BeforeEach
  public void setup() {
    return;
  }
 
  @AfterEach
  public void shutdown() {
    return;
  }
  
  @Test
  public void testInitSuccessful() {
    GenericSolution<TestEnum> solution = new GenericSolution<TestEnum>(0, new EnumMap<>(TestEnum.class));
  }

  @Test
  public void testAddAndGetParameters() {
    EnumMap<TestEnum, Double> myMap = new EnumMap<>(TestEnum.class);
    myMap.put(TestEnum.parameter1, 10.);
    myMap.put(TestEnum.parameter2, 5.);
    myMap.put(TestEnum.parameter3, -10.);
    GenericSolution<TestEnum> solution = new GenericSolution<TestEnum>(0, myMap);
    assertEquals(solution.getParameter(TestEnum.parameter1), 10);
    assertEquals(solution.getParameter(TestEnum.parameter2), 5.);
    assertEquals(solution.getParameter(TestEnum.parameter3), -10.);
  }

  @Test public void testCompareTo() {
    GenericSolution<TestEnum> solution1 = new GenericSolution<TestEnum>(-5, new EnumMap<>(TestEnum.class));
    GenericSolution<TestEnum> solution2 = new GenericSolution<TestEnum>(0, new EnumMap<>(TestEnum.class));
    GenericSolution<TestEnum> solution3 = new GenericSolution<TestEnum>(5, new EnumMap<>(TestEnum.class));
    GenericSolution<TestEnum> solution4 = new GenericSolution<TestEnum>(5, new EnumMap<>(TestEnum.class));
    assertTrue(solution1.compareTo(solution2) == -1);
    assertTrue(solution2.compareTo(solution1) == 1);
    assertTrue(solution3.compareTo(solution4) == 0);
  }

}