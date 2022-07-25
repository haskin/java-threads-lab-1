import java.util.Scanner;

public class Main {
    private static final long mainThreadId = Thread.currentThread().getId();

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter start of first range: ");
        int start1 = scanner.nextInt();
        System.out.print("Enter end of first range: ");
        int end1 = scanner.nextInt();

        System.out.print("Enter start of second range: ");
        int start2 = scanner.nextInt();
        System.out.print("Enter end of second range: ");
        int end2 = scanner.nextInt();

        // int start1 = 2;
        // int end1 = 6;

        // int start2 = 1;
        // int end2 = 4;

        RangeAdder adder1 = new RangeAdder(start1, end1);
        RangeAdder adder2 = new RangeAdder(start2, end2);

        adder1.start();
        adder2.start();

        adder1.join();
        long partialSum1 = adder1.getSum();
        adder2.join();
        long partialSum2 = adder2.getSum();

        long sum = partialSum1 + partialSum2;

        System.out.println("The sum of both ranges is: " + sum);

        scanner.close();
    }

    // DO NOT modify the RangeAdder class
    static class RangeAdder extends Thread {

        int start;
        int end;

        private volatile long sum = 0;

        public RangeAdder(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            final long currentId = Thread.currentThread().getId();

            if (currentId == mainThreadId) {
                throw new RuntimeException("You must start a new thread!");
            }

            long total = 0;
            for (int i = start; i <= end; i++) {
                total += i;
            }

            this.sum = total;
        }

        public long getSum() {
            return sum;
        }
    }
}