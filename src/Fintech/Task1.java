package Fintech;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int minute = 1;
        if (scanner.hasNext()) {
            minute = scanner.nextInt();
        }

        System.out.println(mossCellsAtMinute(minute));
    }

    public static long mossCellsAtMinute(int minute) {
        if (minute == 1) {
            return 1;
        }

        return minute * 4L - 4;
    }
}


class TestTask1 {

    @Test
    public void for1return1() {
        assertEquals(1, Task1.mossCellsAtMinute(1));
    }

    @Test
    public void for6return20() {
        assertEquals(20, Task1.mossCellsAtMinute(6));
    }
}
