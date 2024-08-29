import java.util.Scanner;

public class LAB03Q01_Balanco_Parenteses {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean sair = false;
        
        while (!sair && scanner.hasNextLine()) {
            String expressao = scanner.nextLine();

            sair = expressao.equals("FIM");

            if(!sair){
                if (verificaParenteses(expressao)) {
                    System.out.println("correto");
                } else {
                    System.out.println("incorreto");
                }
            }
        }
        
        scanner.close();
    }
    
    // Funcao que cverifica a quantidade de parenteses
    public static boolean verificaParenteses(String expressao) {
        int contador = 0;
        boolean correto = true;

        for (int i = 0; i < expressao.length(); i++) {
            char caractere = expressao.charAt(i);
            
            if (caractere == '(') {
                contador++;
            } else if (caractere == ')') {
                contador--;
                
                if (contador < 0) {
                    correto = false; 
                }
            }
        }
        
        if (contador != 0) {
            correto = false; 
        }

        return correto; 
    }
}
