package EternalContest;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long lowBound = 1;
        long highBound = 1;

        if (scanner.hasNext()) {
            lowBound = scanner.nextLong();
        }
        if (scanner.hasNext()) {
            highBound = scanner.nextLong();
        }

        System.out.println(numberOfTests(lowBound, highBound));
    }

    public static long numberOfTests(long lowBound, long highBound) {
        long numberOfTests = 1;

        long lowBoundTests = numberOfTestsForOrder(lowBound, false);
        long highBoundTests = numberOfTestsForOrder(highBound, true);

        int lowOrders = orders(lowBound);
        int highOrders = orders(highBound);

        if (lowOrders == highOrders) {
            numberOfTests = highBoundTests + lowBoundTests - 9;
        } else {
            long numberOfTestsBetweenOrders = Math.max(highOrders - lowOrders - 1, 0) * 9L;
            numberOfTests = lowBoundTests + numberOfTestsBetweenOrders + highBoundTests;
        }
//        System.out.println("тесты для нижней границы: " + lowBoundTests + " порядки нижней границы: " + lowOrders);
//        System.out.println("тесты для верхней границы: " + highBoundTests + " порядки верхей границы: " + highOrders);
        return numberOfTests;
    }

    private static long numberOfTestsForOrder(long bound, boolean isHigh) {
        int boundOrders = orders(bound);
        int subtract = boundOrders == 1 ? 10 : 9;
        long divider = Long.parseLong("1".repeat(Math.max(1, boundOrders)));

        long division = bound / divider;
        if (isHigh) {
            return division;
        }
        return division == 0
                ? 9
                : subtract - division;
    }

    private static int orders(long number) {
        return Long.toString(number).length();
    }
}

class Task5Test {
    @Test
    public void for1and1return1() {
        assertEquals(1, Task5.numberOfTests(1,1));
    }

    @Test
    public void for4and7return3() {
        assertEquals(4, Task5.numberOfTests(4,7));
    }

    @Test
    public void for1and9return9() {
        assertEquals(9, Task5.numberOfTests(1,9));
    }

    @Test
    public void for10and100return9() {
        assertEquals(9, Task5.numberOfTests(10,100));
    }

    @Test
    public void for12and90return7() {
        assertEquals(7, Task5.numberOfTests(12,90));
    }

    @Test
    public void for4and100return15() {
        assertEquals(15, Task5.numberOfTests(4,100));
    }

    @Test
    public void for10and100000000return() {
        assertEquals(63, Task5.numberOfTests(10,100000000));
    }
}
