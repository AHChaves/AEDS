import java.util.Scanner;

public class TP01Q06_ls {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean sair = false;

        while (!sair && scanner.hasNextLine()) {
            String input = scanner.nextLine();
           
            sair = input.equals("FIM");
           
            if(!sair){
                boolean isVowels = areAllVowels(input);
                boolean isConsonants = areAllConsonants(input);
                boolean isInteger = isInteger(input);
                boolean isReal = isReal(input);

                System.out.println((isVowels ? "SIM" : "NAO") + " " +
                        (isConsonants ? "SIM" : "NAO") + " " +
                        (isInteger ? "SIM" : "NAO") + " " +
                        (isReal ? "SIM" : "NAO"));
            }
        }
    }

    // Confere se todos os caracteres sao vogais
    private static boolean areAllVowels(String s) {
        int i = 0;
        int length = s.length();
        boolean result = true;
        while (i < length) {
            char c = s.charAt(i);
            if (c != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u' &&
                    c != 'A' && c != 'E' && c != 'I' && c != 'O' && c != 'U') {
                result = false;
                i = length; // Termina o loop
            }
            i++;
        }
        return result;
    }

    // Confere se todos os caractteres sao consoates
    private static boolean areAllConsonants(String s) {
        int i = 0;
        int length = s.length();
        boolean result = true;
        while (i < length) {
            char c = s.charAt(i);
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' ||
                        c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
                    result = false;
                    i = length; // Termina o loop
                }
            } else {
                result = false;
                i = length; // Termina o loop
            }
            i++;
        }
        return result;
    }

    // confere se os caracteres sao inteiros
    private static boolean isInteger(String s) {
        int i = 0;
        int length = s.length();
        boolean result = true;
        if (length == 0) {
            result = false;
        } else {
            if (s.charAt(0) == '-' || s.charAt(0) == '+') {
                i++;
            }
            if (i >= length) {
                result = false;
            } else {
                while (i < length) {
                    char c = s.charAt(i);
                    if (c < '0' || c > '9') {
                        result = false;
                        i = length; // Termina o loop
                    }
                    i++;
                }
            }
        }
        return result;
    }

    //verifica se a entrada eh composta por um numero real
    private static boolean isReal(String s) {
        int i = 0;
        int length = s.length();
        boolean hasDot = false;
        boolean result = true;
        if (length == 0) {
            result = false;
        } else {

            if (s.charAt(0) == '-' || s.charAt(0) == '+') {
                i++;
            }
            if (i >= length) {
                result = false;
            } else {

                while (i < length) {
                    char c = s.charAt(i);
                    if (c == '.') {
                        if (hasDot) { // confere se a mais de um ponto no numero
                            result = false;
                            i = length; // Termina o loop
                        }
                        hasDot = true;
                    } else if (c < '0' || c > '9') { //confere se eh composto por numeros
                        result = false;
                        i = length; // Termina o loop
                    }
                    i++;
                }
            }
        }
        return result && hasDot;
    }
}
