import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BenchmarkMin {

    public static void main(String[] args) {
        BenchmarkMin bm = new BenchmarkMin();

        int iterations = 1_000_000;
        double a = 1.2, b = 0.5, c = 3.8;

        // Warm-up
        for (int i = 0; i < 10_000; i++) {
            bm.Find_Min_Collection(a, b, c);
            bm.Find_Min_Math(a, b, c);
        }

        Runtime runtime = Runtime.getRuntime();

        // ========== Test 1: Collections.min() ==========
        runtime.gc();
        long memBefore1 = runtime.totalMemory() - runtime.freeMemory();
        long timeStart1 = System.nanoTime();

        for (int i = 0; i < iterations; i++) {
            bm.Find_Min_Collection(a, b, c);
        }

        long timeEnd1 = System.nanoTime();
        long memAfter1 = runtime.totalMemory() - runtime.freeMemory();
        double timeMs1 = (timeEnd1 - timeStart1) / 1_000_000.0;
        long memUsed1 = memAfter1 - memBefore1;

        // ========== Test 2: Math.min() ==========
        runtime.gc();
        long memBefore2 = runtime.totalMemory() - runtime.freeMemory();
        long timeStart2 = System.nanoTime();

        for (int i = 0; i < iterations; i++) {
            bm.Find_Min_Math(a, b, c);
        }

        long timeEnd2 = System.nanoTime();
        long memAfter2 = runtime.totalMemory() - runtime.freeMemory();
        double timeMs2 = (timeEnd2 - timeStart2) / 1_000_000.0;
        long memUsed2 = memAfter2 - memBefore2;

        // ========== Output ==========
        System.out.println("== Benchmark Results ==");
        System.out.println("Collections.min():");
        System.out.println("  Time  = " + timeMs1 + " ms");
        System.out.println("  Memory = " + memUsed1 + " bytes");

        System.out.println("Math.min():");
        System.out.println("  Time  = " + timeMs2 + " ms");
        System.out.println("  Memory = " + memUsed2 + " bytes");
    }

    // Cara 1: menggunakan List + Collections.min()
    private double Find_Min_Collection(double a, double b, double c) {
        List<Double> list = new ArrayList<>(3);
        list.add(a);
        list.add(b);
        list.add(c);
        return Collections.min(list);
    }

    // Cara 2: menggunakan Math.min()
    private double Find_Min_Math(double a, double b, double c) {
        return Math.min(a, Math.min(b, c));
    }
}
