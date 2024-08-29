import java.util.Scanner;

public class TP01Q15_ls_Recursivo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean sair = false;

        while (!sair && scanner.hasNext()) {
            String input = scanner.nextLine();

            sair = input.equals("FIM");

            if(!sair){
                String resultado = (Vogais(input, 0) ? "SIM" : "NAO") + " " +
                                (Consoantes(input, 0) ? "SIM" : "NAO") + " " +
                                (Inteiros(input, 0) ? "SIM" : "NAO") + " " +
                                (Real(input, 0, false) ? "SIM" : "NAO");
                
                System.out.println(resultado);
            }
        }
        
        scanner.close();
    }

    // funcao recursiva que verifica se a string é feita por vogais
    public static boolean Vogais(String str, int index) {
        boolean resultado;
        boolean isVogal;

        if (index == str.length()) {
            resultado = true;
        } else {
            char c = Character.toLowerCase(str.charAt(index));
            isVogal = (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u');
            resultado = isVogal && Vogais(str, index + 1);
        }
        
        return resultado;
    }

    // funcao recursiva que verifica se a string é feita por Consoantes
    public static boolean Consoantes(String str, int index) {
        boolean resultado;
        boolean isConsoante;

        if (index == str.length()) {
            resultado = true;
        } else {
            char c = Character.toLowerCase(str.charAt(index));
            isConsoante = (c >= 'a' && c <= 'z') && !(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u');
            resultado = isConsoante && Consoantes(str, index + 1);
        }
        
        return resultado;
    }

    // funcao recursiva que verifica se a string é feita por numeros inteiros
    public static boolean Inteiros(String str, int index) {
        boolean resultado;
        boolean isDigito;

        if (index == str.length()) {
            resultado = true;
        } else if (index == 0 && str.charAt(0) == '-' && str.length() > 1) {
            resultado = Inteiros(str, index + 1);
        } else {
            isDigito = str.charAt(index) >= '0' && str.charAt(index) <= '9';
            resultado = isDigito && Inteiros(str, index + 1);
        }
        
        return resultado;
    }

    // funcao recursiva que verifica se a string é feita por numeros reais
    public static boolean Real(String str, int index, boolean pontoEncontrado) {
        boolean resultado;
        boolean isDigito;

        if (index == str.length()) {
            resultado = pontoEncontrado;
        } else if (index == 0 && str.charAt(0) == '-' && str.length() > 1) {
            resultado = Real(str, index + 1, pontoEncontrado);
        } else if (str.charAt(index) == '.' && !pontoEncontrado) {
            resultado = Real(str, index + 1, true);
        } else {
            isDigito = str.charAt(index) >= '0' && str.charAt(index) <= '9';
            resultado = isDigito && Real(str, index + 1, pontoEncontrado);
        }
        
        return resultado;
    }
}
