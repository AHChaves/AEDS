import java.util.Scanner;

public class LAB02Q02  {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = 3;

        for (int i = 0; i < n; i++) {

            int inicio = scanner.nextInt();
            int fim = scanner.nextInt();

            StringBuilder sequencia = new StringBuilder();

            for (int j = inicio; j <= fim; j++) {
                sequencia.append(j);
            }

            String original = sequencia.toString();

            String espelho = sequencia.reverse().toString();

            System.out.println(original + espelho);
        }

        scanner.close();
    }
}
