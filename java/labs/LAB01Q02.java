public class LAB01Q02 {
    public static int contarMaiusculos(String s) {
        return contarMaiusculos(s, 0);
    }

    private static int contarMaiusculos(String s, int index) {
        if (index >= s.length()) {
            return 0;
        }
        
        int contagemAtual = Character.isUpperCase(s.charAt(index)) ? 1 : 0;

        return contagemAtual + contarMaiusculos(s, index + 1);
    }
    
    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            if (linha.equals("FIM")) {
                break;
            }
            int resultado = contarMaiusculos(linha);
            System.out.println(resultado);
        }

        scanner.close();
    }
}
