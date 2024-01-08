import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.EnumMap;

import org.junit.jupiter.api.*;

import frc.utility.GenericInterpolation.GenericSolution;
import frc.utility.GenericInterpolation.GenericInterpolator;

public class InterpolatorTest {

  public enum TestEnum {
    parameter1, parameter2, parameter3
  }

  private GenericInterpolator myInterpolator;

  @BeforeEach
  public void setup() {
    myInterpolator = new GenericInterpolator<>("myJson", new EnumMap<>(TestEnum.class));
  }
 
  @AfterEach
  public void shutdown() {
    return;
  }
  
  @Test
  public void testCalcNewSolution_PullNoCalc() {
    EnumMap<TestEnum, Double> map1 = new EnumMap<>(TestEnum.class);
    map1.put(TestEnum.parameter1, 3.);
    map1.put(TestEnum.parameter2, 4.);
    map1.put(TestEnum.parameter3, 5.);
    GenericSolution<TestEnum> solution = new GenericSolution<>(0, map1);
    myInterpolator.addSolution(solution);

    GenericSolution<TestEnum> calculatedSolution1 = myInterpolator.calcNewSolution(0);
    assertEquals(calculatedSolution1.getParameter(TestEnum.parameter1), solution.getParameter(TestEnum.parameter1));
    assertEquals(calculatedSolution1.getParameter(TestEnum.parameter2), solution.getParameter(TestEnum.parameter2));
    assertEquals(calculatedSolution1.getParameter(TestEnum.parameter3), solution.getParameter(TestEnum.parameter3));

    // check upper and lower bounds
    GenericSolution<TestEnum> calculatedSolution2 = myInterpolator.calcNewSolution(-1);
    assertEquals(calculatedSolution2.getParameter(TestEnum.parameter1), solution.getParameter(TestEnum.parameter1));
    assertEquals(calculatedSolution2.getParameter(TestEnum.parameter2), solution.getParameter(TestEnum.parameter2));
    assertEquals(calculatedSolution2.getParameter(TestEnum.parameter3), solution.getParameter(TestEnum.parameter3));
    GenericSolution<TestEnum> calculatedSolution3 = myInterpolator.calcNewSolution(1);
    assertEquals(calculatedSolution3.getParameter(TestEnum.parameter1), solution.getParameter(TestEnum.parameter1));
    assertEquals(calculatedSolution3.getParameter(TestEnum.parameter2), solution.getParameter(TestEnum.parameter2));
    assertEquals(calculatedSolution3.getParameter(TestEnum.parameter3), solution.getParameter(TestEnum.parameter3));
    
  }

  @Test
  public void testCalcNewSolution_PullWithCalc() {
    EnumMap<TestEnum, Double> map1 = new EnumMap<>(TestEnum.class);
    map1.put(TestEnum.parameter1, 3.);
    map1.put(TestEnum.parameter2, 4.);
    map1.put(TestEnum.parameter3, 5.);
    EnumMap<TestEnum, Double> map2 = new EnumMap<>(TestEnum.class);
    map2.put(TestEnum.parameter1, 4.);
    map2.put(TestEnum.parameter2, 5.);
    map2.put(TestEnum.parameter3, 6.);

    myInterpolator.addSolution(new GenericSolution<>(5, map1));
    myInterpolator.addSolution(new GenericSolution<>(10, map2));

    GenericSolution<TestEnum> calculatedSolution = myInterpolator.calcNewSolution(7.5);

    assertEquals(calculatedSolution.getParameter(TestEnum.parameter1), 3.5);
    System.out.println(calculatedSolution.getParameter(TestEnum.parameter1));
    assertEquals(calculatedSolution.getParameter(TestEnum.parameter2), 4.5);
    System.out.println(calculatedSolution.getParameter(TestEnum.parameter2));
    assertEquals(calculatedSolution.getParameter(TestEnum.parameter3), 5.5);
    System.out.println(calculatedSolution.getParameter(TestEnum.parameter3));
  }

  @Test
  public void testCalcNewSolution_PullWithCalcMultipleSolutions() {
    EnumMap<TestEnum, Double> map1 = new EnumMap<>(TestEnum.class);
    map1.put(TestEnum.parameter1, 3.);
    map1.put(TestEnum.parameter2, 4.);
    map1.put(TestEnum.parameter3, 5.);
    EnumMap<TestEnum, Double> map2 = new EnumMap<>(TestEnum.class);
    map2.put(TestEnum.parameter1, 4.);
    map2.put(TestEnum.parameter2, 5.);
    map2.put(TestEnum.parameter3, 6.);

    // using null maps because i cba to make new ones, it'll fail if it gets the wrong ones anyway
    myInterpolator.addSolution(new GenericSolution<>(0, null));
    myInterpolator.addSolution(new GenericSolution<>(5, map1));
    myInterpolator.addSolution(new GenericSolution<>(10, map2));
    myInterpolator.addSolution(new GenericSolution<>(15, null));

    GenericSolution<TestEnum> calculatedSolution = myInterpolator.calcNewSolution(7.5);

    assertEquals(calculatedSolution.getParameter(TestEnum.parameter1), 3.5);
    System.out.println(calculatedSolution.getParameter(TestEnum.parameter1));
    assertEquals(calculatedSolution.getParameter(TestEnum.parameter2), 4.5);
    System.out.println(calculatedSolution.getParameter(TestEnum.parameter2));
    assertEquals(calculatedSolution.getParameter(TestEnum.parameter3), 5.5);
    System.out.println(calculatedSolution.getParameter(TestEnum.parameter3));
  }

}