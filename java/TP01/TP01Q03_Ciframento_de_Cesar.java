import java.util.Scanner;

public class TP01Q03_Ciframento_de_Cesar {
    private static final int SHIFT = 3;
    private static final char REPLACEMENT_CHAR = '\uFFFD';

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        while (scanner.hasNext()) {
            String input = scanner.nextLine();

            String encryptedMessage = cifraCesar(input, SHIFT);
            System.out.println( encryptedMessage);
        }

        scanner.close();
    }

    public static String cifraCesar(String str, int shift) {
        StringBuilder result = new StringBuilder();

        for (char c : str.toCharArray()) {
            if (c == REPLACEMENT_CHAR) {
                result.append(c);
            } else {
                char encryptedChar = (char) (c + shift);
                result.append(encryptedChar);
            }
        }

        return result.toString();
    }
}
