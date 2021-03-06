package benchmark.romberg;

public class Romberg {

  static void romberg(float[][] r, float a, float b, int M, Function f) {
    float h, s;

    h = b - a;
    r[0][0] = (f.apply(a) + f.apply(b)) * h / 2.0F;

    for (int n = 1; n <= M; n++) {
      h = h / 2.0F;
      s = 0.0F;

      for (int i = 1; i <= int_pow(2, n - 1); i++) {
        s = s + f.apply(a + (float) (2.0F * i - 1) * h);
      }

      r[n][0] = r[n - 1][0] / 2.0F + h * s;

      for (int m = 1; m <= n; m++) {
        r[n][m] = r[n][m - 1] + (float) (1.0 / (int_pow(4, m) - 1))
            * (r[n][m - 1] - r[n - 1][m - 1]);

      }
    }
  }

  static int int_pow(int base, int exp) {
    int acc = 1;

    while (exp > 0) {
      acc = acc * base;
      exp = exp - 1;
    }

    return acc;
  }

}
