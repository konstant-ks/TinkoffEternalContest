package Fintech;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Task4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String calculation = null;

        if (scanner.hasNext()) {
            calculation = scanner.next();
        }

        Calculator calculator = new Calculator();
        String result = calculator.evaluate(calculation);
        System.out.println(result == null ? "WRONG" : result);
    }

    static class Calculator {

        /**
         * Evaluate statement represented as string.
         *
         * @param statement mathematical statement containing digits, '.' (dot) as decimal mark,
         *                  parentheses, operations signs '+', '-', '*', '/'<br>
         *                  Example: <code>(1 + 38) * 4.5 - 1 / 2.</code>
         * @return string value containing result of evaluation or null if statement is invalid
         */
        public String evaluate(String statement) {
            if (statement == null || statement.isEmpty() || !isValid(statement))
                return null;

            statement = removeBrackets(statement);

            statement = convertNegatives(statement);

            statement = calculateMultiplicationAndDivision(statement);
            if (statement == null) {
                return null;
            }

            statement = calculateAdditionAndSubtraction(statement);

            return formatOutput(statement);
        }

        /**
         * Format the string to following format:
         * -Rounding to 4 significant digits
         * -Remove zeroes at the end
         * @param statement
         * @return
         */
        private String formatOutput(String statement) {
            double value = Double.parseDouble(statement);

            if (Double.isNaN(value) || !Double.isFinite(value))
                return null;

            BigDecimal bd = new BigDecimal(value).setScale(4, RoundingMode.HALF_UP);
            statement = bd.toString().replaceFirst("[.]?0*$", "");
            return statement;
        }

        /**
         * Replace addition and subtraction with results of such actions
         * @param statement
         * @return
         */
        private String calculateAdditionAndSubtraction(String statement) {
            String operators[] = statement.split("[0-9]+[.]?[0-9]*");
            String operands[] = statement.split("[+-]");
            double aggregate = Double.parseDouble(operands[0]);
            for (int i = 1; i < operands.length; i++) {
                if (operators[i].equals("+")) {
                    aggregate += Double.parseDouble(operands[i]);
                }
                else {
                    aggregate -= Double.parseDouble(operands[i]);
                }
            }
            return Double.toString(aggregate);
        }

        /**
         * Replace multiplication and division with results of such actions
         * @param statement
         * @return
         */
        private String calculateMultiplicationAndDivision(String statement) {
            while (statement.contains("*") || statement.contains("/")) {
                String operators[] = statement.split("[0-9]+[.]?[0-9]*");
                String operands[] = statement.split("[/*+-]");
                for (int i = 1; i < operands.length; i++) {
                    if (operators[i].equals("*")){
                        double product = Double.parseDouble(operands[i - 1]) * Double.parseDouble(operands[i]);
                        if (Double.isNaN(product) || !Double.isFinite(product))
                            return null;
                        statement = statement.replace(operands[i - 1] + "*" + operands[i], Double.toString(product));
                        break;
                    }
                    if (operators[i].equals("/")){
                        double product = Double.parseDouble(operands[i - 1]) / Double.parseDouble(operands[i]);
                        if (Double.isNaN(product) || !Double.isFinite(product))
                            return null;
                        statement = statement.replace(operands[i - 1] + "/" + operands[i], Double.toString(product));
                        break;
                    }
                }
            }
            return statement;
        }

        /**
         * Convert operations with negative numbers to recognisable format
         * @param statement with math operation on negative numbers
         * @return statement appropriate to evaluate method
         */
        private String convertNegatives(String statement) {
            //Replace "number*negativeNumber" with "0 - number*negativeNumber"
            Pattern pattern = Pattern.compile("[0-9]+[.]?[0-9]*[/*+-]-");
            Matcher matcher = pattern.matcher(statement);
            while (matcher.find()) {
                statement = statement.replaceFirst(matcher.group(0), "0-" + matcher.group(0).substring(0, matcher.group(0).length() - 1));
            }
            //Fix the - at the beginning of the statement
            statement = statement.replaceFirst("^-", "0-");
            return statement;
        }

        /**
         * Replace brackets with evaluated value inside of them
         * @param statement
         * @return
         */
        private String removeBrackets(String statement) {
            while (statement.contains("(") && statement.contains(")")) {
                String brackets = statement.substring(statement.lastIndexOf("("), statement.indexOf(")") + 1);
                statement = statement.replace(brackets, evaluate(statement.substring(statement.lastIndexOf("(") + 1, statement.indexOf(")"))));
            }
            return statement;
        }

        private boolean isValid(String statement) {
            final Pattern wrongCharacterPattern = Pattern.compile("[^0-9()*/+\\-.]");
            final Pattern duplicateCharacterPattern = Pattern.compile("[*/+\\-.][*/+\\-.]+");
            if (wrongCharacterPattern.matcher(statement).find()) {
                return false;
            } else if (duplicateCharacterPattern.matcher(statement).find()){
                return false;
            }
            int bracketCount = 0;
            for (int i = 0; i < statement.length(); i++) {
                if (statement.charAt(i) == '(') {
                    bracketCount++;
                } else if (statement.charAt(i) == ')') {
                    bracketCount--;
                }
            }
            return bracketCount == 0;
        }
    }
}
