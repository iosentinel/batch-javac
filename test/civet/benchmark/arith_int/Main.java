package benchmark.arith_int;

import org.civet.Civet;
import org.civet.Civet.Compile;

public class Main {
  public static Exp buildSimpleExp() { // The expression from Ulrik's
    // dissertation: (x-y)*(x-y)
    Exp e = new Mul(new Add(new Var(0), new Neg(new Var(1))), new Add(
        new Var(0), new Neg(new Var(1))));
    return e;
  }

  public static Exp buildExp() { // (x-2)^2+4*(y-a) =
    // ((x-2)*(x-2))-(4*(y+(-a)))
    Exp x_m_2 = new Add(new Var(0), new Constant(-2));
    Exp e = new Add(new Mul(x_m_2, x_m_2), new Neg(new Mul(new Constant(4),
        new Add(new Var(1), new Var(2)))));
    return e;
  }

  @Compile
  public static void main(String argv[]) {
    int gridSize;
    if (argv.length != 1)
      throw new Error("A single integer argument (gridsize) is needed!");
    try {
      gridSize = Integer.parseInt(argv[0]);
    } catch (NumberFormatException e) {
      throw new Error("Argument must be an integer! (the grid size)");
    }
    if (gridSize < 1)
      throw new Error("A positive integer argument is needed!");
    Exp e = Civet.CT(Main.buildExp());
    Timer tim = new Timer();
    int result = 0;
    long total_time = 0;
    for (int i = 0; i < 10; i++) {
      System.out.print("[Iteration #" + i + "] ");
      System.out.flush();
      tim.start();
      result = result + performTest(gridSize, e, result);
      tim.stop();
      if (i > 4)
        total_time = total_time + tim.diff();
      tim.report();
    }
    System.out.println("Checksum: " + result);
    System.out.println("Average time 5 last iterations: " + (total_time / 5));
  }

  public static final int minimal_int = Integer.MIN_VALUE;

  public static int performTest(int size, Exp e, int result) {
    int[] env = new int[3];
    int max_value = minimal_int;
    int max_x = size / 2, max_y = size / 2;
    env[2] = result;
    for (int x = -max_x; x < max_x; x++)
      for (int y = -max_y; y < max_y; y++) {
        env[0] = x;
        env[1] = y;
        int res = computePoint(e, env);
        if (res > max_value)
          max_value = res;
      }
    return max_value;
  }

  public static int computePoint(Exp e, int[] xybinding) {
    return Apply.a(e, xybinding);
  }
}
