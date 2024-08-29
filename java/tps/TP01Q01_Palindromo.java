import java.util.Scanner;

public class TP01Q01_Palindromo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String input = scanner.nextLine();

            if (isPalindrome(input)) {
                System.out.println("SIM");
            } else {
                System.out.println("NAO");
            }
        }

        scanner.close();
    }

    //FUncao que avalia se a string recebida eh palindromo ou nao, retornando true ou false
    public static boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;
        boolean ehPalindromo = true;

        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                ehPalindromo = false;
                left = right;
            }
            left++;
            right--;
        }
        return ehPalindromo;
    }
}
