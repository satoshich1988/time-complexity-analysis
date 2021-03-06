package tech.dario.timecomplexityanalysis.testalgorithms.custom;

public class Exponential {
  // quick(1000) ~ 1ms ~ 3000000 iterations
  public void quick(long n) {
    double v = Math.pow(3000000L, n / 1000.d);
    for (long i = 0; i < v; i++) {

    }
  }

  // average(1000) ~ 10ms ~ 30000000 iterations
  public void average(long n) {
    double v = Math.pow(30000000L, n / 1000.d);
    for (long i = 0; i < v; i++) {

    }
  }

  // slow(1000) ~ 100ms ~ 300000000 iterations
  public void slow(long n) {
    double v = Math.pow(300000000L, n / 1000.d);
    for (long i = 0; i < v; i++) {

    }
  }
}
