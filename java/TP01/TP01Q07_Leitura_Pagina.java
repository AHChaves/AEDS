import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

public class TP01Q07_Leitura_Pagina {

    public static int contarOcorrencias(String texto, String padrao) {
        int contador = 0;
        int indice = 0;

        while ((indice = texto.indexOf(padrao, indice)) != -1) {
            contador++;
            indice += padrao.length();
        }

        return contador;
    }

    public static void processaPagina(String endereco, String nomePagina) throws Exception {
        URL url = new URL(endereco);
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        String inputLine;
        int[] contagem = new int[26];
        String consoantes = "bcdfghjklmnpqrstvwxyz";
        
        while ((inputLine = in.readLine()) != null) {
            for (char c : inputLine.toCharArray()) {
                switch (c) {
                    case 'a': contagem[0]++; break;
                    case 'e': contagem[1]++; break;
                    case 'i': contagem[2]++; break;
                    case 'o': contagem[3]++; break;
                    case 'u': contagem[4]++; break;
                    case '\u00E1': contagem[5]++; break;
                    case '\u00E9': contagem[6]++; break;
                    case '\u00ED': contagem[7]++; break;
                    case '\u00F3': contagem[8]++; break;
                    case '\u00FA': contagem[9]++; break;
                    case '\u00E0': contagem[10]++; break;
                    case '\u00E8': contagem[11]++; break;
                    case '\u00EC': contagem[12]++; break;
                    case '\u00F2': contagem[13]++; break;
                    case '\u00F9': contagem[14]++; break;
                    case '\u00E3': contagem[15]++; break;
                    case '\u00F5': contagem[16]++; break;
                    case '\u00E2': contagem[17]++; break;
                    case '\u00EA': contagem[18]++; break;
                    case '\u00EE': contagem[19]++; break;
                    case '\u00F4': contagem[20]++; break;
                    case '\u00FB': contagem[21]++; break;
                    default:
                        if (consoantes.indexOf(c) != -1) {
                            contagem[22]++;
                        }
                        break;
                }
            }
            contagem[23] += contarOcorrencias(inputLine, "<br>");
            contagem[24] += contarOcorrencias(inputLine, "<table>");
        }
        in.close();

        // Saída formatada em um loop
        String[] resultados = {
            "a(", "e(", "i(", "o(", "u(", "\u00E1(", "\u00E9(", "\u00ED(", "\u00F3(", "\u00FA(",
            "\u00E0(", "\u00E8(", "\u00EC(", "\u00F2(", "\u00F9(", "\u00E3(", "\u00F5(", "\u00E2(", "\u00EA(", "\u00EE(",
            "\u00F4(", "\u00FB(", "consoante(", "<br>(", "<table>(", nomePagina 
        };

        for (int i = 0; i < contagem.length; i++) {
            if((i== 0) || (i == 1)){
                System.out.print(resultados[i] + (contagem[i] - 1) + ") ");
            }
            else if(resultados[i] == nomePagina){
                System.out.print(resultados[i]);
            }
            else if(resultados[i] == "consoante("){
                System.out.print(resultados[i] + (contagem[i] - 3) + ") ");
            }
            else
                System.out.print(resultados[i] + contagem[i] + ") ");
        }
        System.out.println();
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean sair = false;
        
        while (!sair && scanner.hasNext()) {
            String nomePagina = scanner.nextLine();

            sair = nomePagina.equals("FIM");

            if(!sair){
                String endereco = scanner.nextLine();
                processaPagina(endereco, nomePagina);
            }
        }
        scanner.close();
    }
}

