package Fintech;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {

//        Scanner scanner = new Scanner(System.in);

        int operationsCount = 8;
        List<String> operations = new ArrayList<>();
        operations.add("+11");
        operations.add("+1");
        operations.add("-11");
        operations.add("+2");
        operations.add("-2");
        operations.add("+3");
        operations.add("+2");
        operations.add("-1");

//        if (scanner.hasNext()) {
//            operationsCount = scanner.nextInt();
//        }
//        for (int i = 0; i < operationsCount; i++) {
//            operations.add(scanner.next());
//        }

        List<Shelf> wall = new ArrayList<>(operationsCount);
        for (int i = 1; i <= operationsCount; i++) {
            wall.add(new Shelf(i));
        }


        for (String operation : operations) {
            parseOperation(wall, operation);
        }
//        for (Shelf shelf : wall) {
//            System.out.println(shelf.number);
//        }
    }

    private static void parseOperation(List<Shelf> wall, String operation) {
        int toolNumber = Integer.parseInt(operation);
        boolean addition = toolNumber > 0;

        if (addition) {
            Shelf emptyShelf = wall.stream().filter(s -> s.numberOfTool == 0).findFirst().get();
            emptyShelf.numberOfTool = toolNumber;
            System.out.println(emptyShelf.number);
        } else {
            int positivToolNumber = Math.abs(toolNumber);
            Shelf shelf = wall.stream().filter(s -> s.numberOfTool == positivToolNumber).findFirst().get();
            shelf.numberOfTool = 0;
        }
    }

    static class Shelf {
        public int number;

        public int numberOfTool;

        public Shelf() {
        }

        public Shelf(int number) {
            this.number = number;
        }
    }
}
