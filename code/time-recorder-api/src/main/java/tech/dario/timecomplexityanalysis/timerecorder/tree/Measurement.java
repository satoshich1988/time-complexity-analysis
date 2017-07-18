package tech.dario.timecomplexityanalysis.timerecorder.tree;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Measurement implements Mergeable<Measurement> {
  private final double count;
  private final double total;

  private static final DecimalFormat DECIMAL_FORMAT;
  static {
    DECIMAL_FORMAT = new DecimalFormat();
    DECIMAL_FORMAT.setMaximumFractionDigits(1);
    DECIMAL_FORMAT.setMinimumFractionDigits(1);
    DECIMAL_FORMAT.setGroupingSize(3);
    DECIMAL_FORMAT.setRoundingMode(RoundingMode.HALF_UP);
    DECIMAL_FORMAT.setDecimalSeparatorAlwaysShown(true);
    DECIMAL_FORMAT.setGroupingUsed(true);
  }

  public Measurement(double count, double total) {
    this.count = count;
    this.total = total;
  }

  public static Measurement fromElapsedTime(long elapsedTime) {
    return new Measurement(1, elapsedTime);
  }

  public double getCount() {
    return count;
  }

  public double getTotal() {
    return total;
  }

  @Override
  public Measurement mergeWith(Measurement otherMeasurement) {
    double newCount = count + otherMeasurement.count;
    double newTotal = total + otherMeasurement.total;
    return new Measurement(newCount, newTotal);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Measurement measurement = (Measurement) o;

    if (Double.compare(measurement.count, count) != 0) return false;
    return Double.compare(measurement.total, total) == 0;

  }

  @Override
  public int hashCode() {
    int result;
    long temp;
    temp = Double.doubleToLongBits(count);
    result = (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(total);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    return result;
  }

  @Override
  public String toString() {
    return "{count: " + count +
            ", tot: " + toMicroSeconds(total) +
            ", avg: " + toMicroSeconds(total / count) + "}";
  }

  private String toMicroSeconds(double nanoSeconds) {
    return DECIMAL_FORMAT.format(nanoSeconds / 1000) + " μs";
  }
}
