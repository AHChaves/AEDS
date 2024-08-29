import java.util.Random;
import java.util.Scanner;

public class TP01Q04_Alteracao_Aleatoria {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random gerador = new Random();
        boolean sair = false;
        gerador.setSeed(4); 
        
        while (!sair && scanner.hasNextLine()) {
            String entrada = scanner.nextLine();
            
            sair = entrada.equals("FIM");
            
            if(!sair){
                char letra1 = (char) ('a' + (Math.abs(gerador.nextInt()) % 26)); // 'a' a 'y'
                char letra2 = (char) ('a' + (Math.abs(gerador.nextInt()) % 26)); // 'a' a 'y'
                
                // Substitui letra1 por letra2 na string de entrada
                String resultado = substituiLetras(entrada, letra1, letra2);
                System.out.println(resultado);
            }
        }

        scanner.close();
    }
    
    // Método que substitui todas as ocorrências de letra1 por letra2
    public static String substituiLetras(String str, char letra1, char letra2) {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < str.length(); i++) {
            char caractere = str.charAt(i);
            if (caractere == letra1) {
                sb.append(letra2);
            } else {
                sb.append(caractere);
            }
        }
        
        return sb.toString();
    }
}