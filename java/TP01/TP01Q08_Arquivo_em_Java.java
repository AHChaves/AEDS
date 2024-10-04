import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class TP01Q08_Arquivo_em_Java {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        Salvar_Arquivo(scanner, n);
        Ler_Valores_Inverso(n);
    }

    //Funcao que salva os numeros no arquivo
    private static void Salvar_Arquivo(Scanner scanner, int n) {
        try (RandomAccessFile file = new RandomAccessFile("numbers.txt", "rw")) {
            for (int i = 0; i < n; i++) {
                double number = Ler_Valores_Entrada(scanner);
                file.writeDouble(number);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Funcao que le os valores do arquivo
    private static double Ler_Valores_Entrada(Scanner scanner) {
        double number = 0.0;
        boolean isValid = false;

        while (!isValid) {
            String line = scanner.nextLine();
            if (!line.isEmpty()) {
                try {
                    number = Double.parseDouble(line);
                    isValid = true;
                } catch (NumberFormatException e) {
                    // Captura exceção e continua o loop
                }
            }
        }
        return number;
    }

    // Funcao que pega os bvalores vindo do arquivo e inverte os mesmos
    private static void Ler_Valores_Inverso(int n) {
        try (RandomAccessFile file = new RandomAccessFile("numbers.txt", "r")) {
            long fileLength = file.length();
            for (int i = 0; i < n; i++) {
                file.seek(fileLength - (i + 1) * 8);
                double number = file.readDouble();
                if (number % 1 == 0) {
                    System.out.println((int) number);
                } else {
                    System.out.println(number);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
