package tech.dario.dissertation.timecomplexityanalysis.sdk.fitting;

import org.apache.commons.math3.analysis.ParametricUnivariateFunction;
import org.apache.commons.math3.fitting.WeightedObservedPoint;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer;
import org.apache.commons.math3.linear.RealVector;

import java.util.Collection;

public final class LinearithmicFunctionFinder extends FittingFunctionFinder {

  public LinearithmicFunctionFinder() {
    super(new Parametric(), new double[]{1.0d, 0.0d});
  }

  @Override
  public FittingFunction findFittingFunction(Collection<WeightedObservedPoint> points) {
    LeastSquaresOptimizer.Optimum optimum = getOptimum(points);
    double[] params = optimum.getPoint().toArray();
    return new LinearithmicFunction(params[0], params[1], optimum.getRMS());
  }

  @Override
  public RealVector validate(RealVector realVector) {
    // Constraints:
    // a > 0
    // a + b >= 0
    double a = Math.max(realVector.getEntry(0), 0.0d + Double.MIN_VALUE);
    double b = Math.max(realVector.getEntry(1), -a);
    realVector.setEntry(0, a);
    realVector.setEntry(1, b);
    return realVector;
  }

  private class LinearithmicFunction implements FittingFunction {
    private final double a;
    private final double b;
    private final double rms;

    private LinearithmicFunction(double a, double b, double rms) {
      this.a = a;
      this.b = b;
      this.rms = rms;
    }

    @Override
    public double f(double n) {
      return a * n * Math.log(n) + b;
    }

    @Override
    public double getRms() {
      return rms;
    }

    @Override
    public String toString() {
      return String.format("%.6f * n * ln(n) + %.6f [rms: %.6f]", a, b, rms);
    }
  }

  private static class Parametric implements ParametricUnivariateFunction {
    @Override
    public double[] gradient(double x, double[] params) {
      return new double[]{x * Math.log(x), 1.0d};
    }

    @Override
    public double value(double x, double[] params) {
      double a = params[0];
      double b = params[1];
      return a * x * Math.log(x) + b;
    }
  }
}
