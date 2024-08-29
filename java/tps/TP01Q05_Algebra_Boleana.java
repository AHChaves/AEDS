import java.util.Scanner;

public class TP01Q05_Algebra_Boleana {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean sair = false;

        while (!sair && scanner.hasNextLine()) {
            String input = scanner.nextLine();
            
            sair = input.equals("0");

            if(!sair){
                boolean result = evaluateBooleanExpression(input);
                System.out.println(result);
            }
        }
    }

    private static boolean evaluateBooleanExpression(String input) {
        int i = 0;
        int length = input.length();
        
        int n = 0;
        while (i < length && input.charAt(i) >= '0' && input.charAt(i) <= '9') {
            n = n * 10 + (input.charAt(i) - '0');
            i++;
        }
        
        boolean[] values = new boolean[n];
        for (int j = 0; j < n; j++) {
            if (i < length && input.charAt(i) == '0') {
                values[j] = false;
            } else {
                values[j] = true;
            }
            i++;
        }
        
        boolean result = evaluateExpression(input, i, values);
        return result;
    }

    private static boolean evaluateExpression(String input, int startIndex, boolean[] values) {
        int i = startIndex;
        int length = input.length();
        
        boolean result = false;
        boolean operator = true; // Operador padrao eh o AND
        
        while (i < length) {
            char currentChar = input.charAt(i);
            
            if (currentChar == ' ') {
                i++;
                continue;
            }
            
            if (currentChar == '(') {
                int parenStart = i;
                int parenCount = 1;
                while (i < length && parenCount > 0) {
                    i++;
                    if (input.charAt(i) == '(') {
                        parenCount++;
                    } else if (input.charAt(i) == ')') {
                        parenCount--;
                    }
                }
                String subExpr = input.substring(parenStart + 1, i);
                boolean subResult = evaluateExpression(subExpr, 0, values);
                result = applyOperator(result, subResult, operator);
                operator = true;
            } else if (currentChar == '&') {
                operator = true;
                i++;
            } else if (currentChar == '|') {
                operator = false;
                i++;
            } else if (currentChar == 'T' || currentChar == 'F') {
                boolean currentValue = (currentChar == 'T');
                result = applyOperator(result, currentValue, operator);
                operator = true;
                i++;
            } else if (currentChar >= '0' && currentChar <= '9') {
                int index = currentChar - '0';
                boolean currentValue = values[index];
                result = applyOperator(result, currentValue, operator);
                operator = true;
                i++;
            } else {
                i++;
            }
        }
        return result;
    }

    private static boolean applyOperator(boolean currentResult, boolean value, boolean andOperator) {
        if (andOperator) {
            return currentResult && value;
        } else {
            return currentResult || value;
        }
    }
}