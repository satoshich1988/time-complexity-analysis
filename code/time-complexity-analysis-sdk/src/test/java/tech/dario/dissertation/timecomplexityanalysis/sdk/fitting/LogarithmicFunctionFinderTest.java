package tech.dario.dissertation.timecomplexityanalysis.sdk.fitting;

import org.apache.commons.math3.fitting.WeightedObservedPoints;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LogarithmicFunctionFinderTest {
  @Test
  public void findFittingFunction() throws Exception {
    final LogarithmicFunctionFinder logarithmicFunctionFinder = new LogarithmicFunctionFinder();
    FittingFunction fittingFunction = logarithmicFunctionFinder.findFittingFunction(new WeightedObservedPoints() {{
      add(1.0d, f(1.0d));
      add(2.0d, f(2.0d));
      add(5.0d, f(5.0d));
      add(10.0d, f(10.0d));
    }}.toList());

    assertEquals("Expected correct point 1", f(1.0d), fittingFunction.f(1.0d), 0.0001d);
    assertEquals("Expected correct point 2", f(2.0d), fittingFunction.f(2.0d), 0.0001d);
    assertEquals("Expected correct point 3", f(5.0d), fittingFunction.f(5.0d), 0.0001d);
    assertEquals("Expected correct point 4", f(10.0d), fittingFunction.f(10.0d), 0.0001d);
    assertEquals("Expected correct point 5", f(50.0d), fittingFunction.f(50.0d), 0.0001d);
    assertEquals("Expected correct RMS", 0.000d, fittingFunction.getRms(), 0.0001d);
    assertEquals("Expected correct string representation", "3.000 * ln(n) + 5.000 [rms: 0.000]", fittingFunction.getStringRepresentation());
  }

  private double f(double n) {
    return 3.0d * Math.log(n) + 5.0d;
  }
}