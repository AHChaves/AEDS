import java.util.Scanner;

public class TP01Q12_Ciframento_Cesar_Recursivo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean sair = false;

        while (!sair && scanner.hasNext()) {
            String input = scanner.nextLine();

            sair = input.equals("FIM");

            if(!sair){
                String cifrada = cifraCesar(input, 0);
                System.out.println(cifrada);
            }
        }
        
        scanner.close();
    }


    // funcao que realiza o processo da cifra de cesar
    public static String cifraCesar(String str, int index) {
        String str_final;
        if (index == str.length()) {
            str_final =  "";
        }
        else{
            char atual = str.charAt(index);
            char cifrado;

            if (atual >= 'a' && atual <= 'z') {
                cifrado = (char) (((atual - 'a' + 3) % 26) + 'a');
            } else if (atual >= 'A' && atual <= 'Z') {
                cifrado = (char) (((atual - 'A' + 3) % 26) + 'A');
            } else if(atual >= ' ' && atual <= '/'){
                cifrado = (char) (((atual - ' ' + 3) % 26) + ' ');
            } 
            else {
                cifrado = atual;
            }

            str_final = cifrado + cifraCesar(str, index + 1);

        }
        return str_final;
    }
}

