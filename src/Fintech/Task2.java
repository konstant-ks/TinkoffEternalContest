package Fintech;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = 1;
        int m = 1;
        if (scanner.hasNext()) {
            n = scanner.nextInt();
        }
        if (scanner.hasNext()) {
            m = scanner.nextInt();
        }
        int[][] matrix = new int[m][n];

        int maxValue = n * m;
        int count = 1;
        int baseN = 0;
        int baseM = 0;
        int descBaseM = 0;
        boolean desc = false;
        while (count <= maxValue) {
            if (!desc) {
                for (int i = 0, j = baseN; i <= baseM && j >= 0; ) {
                    matrix[i][j] = count++;
                    i++;
                    j--;
                    if (count >= maxValue) {
                        break;
                    }
                }
            } else {
                for (int i = descBaseM, j = baseN; i <= baseM && j >= 0; ) {
                    matrix[i][j] = count++;
                    i++;
                    j--;
                    if (count >= maxValue) {
                        break;
                    }
                }
            }
            if (baseN < n - 1) {
                baseN++;
            } else {
                desc = true;
                descBaseM++;
            }
            if (baseM < m - 1) {
                baseM++;
            }

        }

        List<String> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            StringJoiner stringJoiner = new StringJoiner(" ");
            for (int j = 0; j < n; j++) {
                stringJoiner.add(String.valueOf(matrix[i][j]));
            }
            list.add(stringJoiner.toString());
        }
        System.out.println(String.join("\n", list));
    }
}
