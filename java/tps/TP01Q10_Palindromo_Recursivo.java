import java.util.Scanner;

public class TP01Q10_Palindromo_Recursivo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean sair = false;

        while (!sair && scanner.hasNext()) {
            String input = scanner.nextLine();

            sair = input.equals("FIM");

            if(!sair){    
                if (ehPalindromo(input, 0, input.length() - 1)) {
                    System.out.println("SIM");
                } else {
                    System.out.println("NAO");
                }
            }
        }
        
        
        scanner.close();
    }

    public static boolean ehPalindromo(String str, int inicio, int fim) {

        boolean saida;

        if (inicio >= fim) {
            saida =  true;
        }
        else{

            char charInicio = str.charAt(inicio);
            char charFim = str.charAt(fim);

            if (!Character.isLetter(charInicio)) {
                saida =  ehPalindromo(str, inicio + 1, fim);
            }
            else if (!Character.isLetter(charFim)) {
                saida =  ehPalindromo(str, inicio, fim - 1);
            }
            else if (charInicio != charFim) {
                saida = false;
            }
            else saida = ehPalindromo(str, inicio + 1, fim - 1);

        }
        return saida;
    }
}
