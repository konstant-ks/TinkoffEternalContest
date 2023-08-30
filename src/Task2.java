import java.util.Scanner;

public class Task2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfGuests = 0;

        if (scanner.hasNext()) {
            numberOfGuests = scanner.nextInt();
        }

        System.out.println(numberOfCuts(numberOfGuests));
    }

    private static int numberOfCuts(int bits) {
        if (bits == 0) {
            return 0;
        }
        return 32 - Integer.numberOfLeadingZeros(bits - 1);
    }
}
