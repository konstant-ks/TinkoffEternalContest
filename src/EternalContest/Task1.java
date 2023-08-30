package EternalContest;

import java.util.Scanner;

public class Task1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int fare = 0;
        int fareMegabytes = 0;
        int mbCost = 0;
        int actualMegabytes = 0;

        if (scanner.hasNext()) {
            fare = scanner.nextInt();
        }
        if (scanner.hasNext()) {
            fareMegabytes = scanner.nextInt();
        }
        if (scanner.hasNext()) {
            mbCost = scanner.nextInt();
        }
        if (scanner.hasNext()) {
            actualMegabytes = scanner.nextInt();
        }

        if (actualMegabytes <= fareMegabytes) {
            System.out.println(fare);
            return;
        }

        int actualCost = fare + (actualMegabytes - fareMegabytes) * mbCost;
        System.out.println(actualCost);
    }
}