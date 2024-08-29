public class LAB01Q01 {
    public static int contarMaiusculos(String s) {
        int contador = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isUpperCase(c)) {
                contador++;
            }
        }
        return contador;
    }
    
    public static void main(String[] args) {
        // Usando Scanner para ler a entrada padrão
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        // Ler linha por linha
        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            if (linha.equals("FIM")) {
                break;
            }
            // Contar e imprimir o número de maiúsculas
            int resultado = contarMaiusculos(linha);
            System.out.println(resultado);
        }

        scanner.close();
    }
}