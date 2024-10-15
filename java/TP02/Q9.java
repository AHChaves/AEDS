import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class PokemonQ9 {
    private int id;
    private int generation;
    private String name;
    private String description;
    private List<String> type;
    private List<String> abilities;
    private double weight;
    private double height;
    private int captureRate;
    private boolean isLegendary;
    private LocalDate captureDate;

    public PokemonQ9() {
        this.id = 0;
        this.generation = 0;
        this.name = "";
        this.description = "";
        this.weight = 0;
        this.height = 0;
        this.captureRate = 0;
        this.isLegendary = false;
        this.captureDate.of(0, 1, 1);
    }

    public PokemonQ9(int id, int generation, String name, String description, List<String> type, List<String> abilities,
            double weight, double height, int captureRate, boolean isLegendary, LocalDate captureDate) {
        this.id = id;
        this.generation = generation;
        this.name = name;
        this.description = description;
        this.type = type;
        this.abilities = abilities;
        this.weight = weight;
        this.height = height;
        this.captureRate = captureRate;
        this.isLegendary = isLegendary;
        this.captureDate = captureDate;

    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGeneration() {
        return this.generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getType() {
        return this.type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public List<String> getAbilities() {
        return this.abilities;
    }

    public void setAbilities(List<String> abilities) {
        this.abilities = abilities;
    }

    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return this.height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getCaptureRate() {
        return this.captureRate;
    }

    public void setCaptureRate(int captureRate) {
        this.captureRate = captureRate;
    }

    public boolean getIsLegendary() {
        return this.isLegendary;
    }

    public void setIsLegendary(boolean isLegendary) {
        this.isLegendary = isLegendary;
    }

    public LocalDate getCaptureDate() {
        return this.captureDate;
    }

    public void setCaptureDate(LocalDate captureDate) {
        this.captureDate = captureDate;
    }

    public void ler(String linha) {
        String[] data = new String[12];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        int tmp = 0;
        int j = 0;
        for (int i = 0; i < linha.length(); i++) {
            if (linha.charAt(i) == '"') {
                List<String> aux = new ArrayList<>();
                i += 2;
                tmp = i;
                while (linha.charAt(i) != '"') {
                    if (linha.charAt(i) == ',' || linha.charAt(i) == ']') {
                        aux.add(linha.substring(tmp, i));
                        tmp = i + 1;
                    }
                    i++;
                }
                tmp = i + 1;
                setAbilities(aux);
            } else if (linha.charAt(i) == ',') {
                String l = linha.substring(tmp, i);
                if (l == "") {
                    data[j] = "0";
                } else {
                    data[j] = l;
                }
                tmp = i + 1;
                j++;
            } else if (linha.length() - i == 1) {
                data[j] = linha.substring(tmp, i + 1);

                tmp = i + 1;
                j++;
            }
        }

        setId(Integer.parseInt(data[0]));
        setGeneration(Integer.parseInt(data[1]));
        setName(data[2]);
        setDescription(data[3]);

        List<String> aux = new ArrayList<>();
        aux.add(data[4]);
        if (data[5] != "0")
            aux.add(data[5]);
        setType(aux);

        setWeight(Double.parseDouble(data[7]));
        setHeight(Double.parseDouble(data[8]));
        setCaptureRate(Integer.parseInt(data[9]));
        setIsLegendary(Boolean.parseBoolean(data[10]));
        setCaptureDate(LocalDate.parse(data[11], formatter));

    }

    public void imprimir() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.print("[#" + this.id + " -> " + this.name + ": " + this.description + " - [");
        for (int i = 0; i < this.type.size(); i++) {
            if (i > 0)
                System.out.print(", ");
            System.out.print("'" + this.type.get(i) + "'");
        }
        System.out.print("] - [");
        for (int i = 0; i < this.abilities.size(); i++) {
            if (i > 0)
                System.out.print(",");
            System.out.print(this.abilities.get(i));
        }
        System.out.println("] - " + this.weight + "kg - " + this.height + "m - " + this.captureRate + "% - "
                + this.isLegendary + " - " + this.generation + " gen] - " + this.captureDate.format(formatter));
    }

    public PokemonQ9 clone() {
        PokemonQ9 pokemon = new PokemonQ9();
        pokemon.id = this.id;
        pokemon.generation = this.generation;
        pokemon.name = this.name;
        pokemon.description = this.description;
        pokemon.type = this.type;
        pokemon.abilities = this.abilities;
        pokemon.weight = this.weight;
        pokemon.height = this.height;
        pokemon.captureRate = this.captureRate;
        pokemon.isLegendary = this.isLegendary;
        pokemon.captureDate = this.captureDate;
        return pokemon;
    }
}

public class Q9 {

    private static int numComparacoes = 0, numMovimentacoes = 0;

    public static PokemonQ9 buscarPorId(List<PokemonQ9> lista, int id) {
        PokemonQ9 pk = new PokemonQ9();
        for (PokemonQ9 j : lista) {
            if (j.getId() == id) {
                pk = j.clone();
            }
        }
        return pk;
    }

    public static void sort(List<PokemonQ9> poke) {
        int n = poke.size();

        PokemonQ9[] array = new PokemonQ9[n + 1]; // Ajustando o vetor para n+1
        for (int i = 0; i < n; i++) {
            array[i + 1] = poke.get(i); // Começando a partir do índice 1
        }

        // Construção do heap
        for (int tamHeap = 2; tamHeap <= n; tamHeap++) {
            construir(array, tamHeap);
        }

        // Ordenação propriamente dita
        int tamHeap = n;
        while (tamHeap > 1) {
            swap(array, 1, tamHeap--);

            reconstruir(array, tamHeap);
        }

        // Alterar o vetor para voltar à posição zero
        for (int i = 0; i < n; i++) {
            poke.set(i, array[i + 1]); // Ajusta de volta para o índice 0
        }
    }

    public static void construir(PokemonQ9[] array, int tamHeap) {
        for (int i = tamHeap; i > 1 && compare(array[i], array[i / 2]) > 0; i /= 2) {
            numComparacoes++;
            swap(array, i, i / 2);
            numMovimentacoes += 3;
        }
    }

    public static void reconstruir(PokemonQ9[] array, int tamHeap) {
        int i = 1;
        while (i <= (tamHeap / 2)) {
            int filho = getMaiorFilho(array, i, tamHeap);
            numComparacoes++;

            if (compare(array[i], array[filho]) < 0) {
                swap(array, i, filho);
                numMovimentacoes += 3;
                i = filho;
            } else {
                i = tamHeap;
            }
        }
    }

    public static int getMaiorFilho(PokemonQ9[] array, int i, int tamHeap) {
        int filho;
        numComparacoes++;

        if (2 * i == tamHeap || compare(array[2 * i], array[2 * i + 1]) > 0) {
            filho = 2 * i;
        } else {
            filho = 2 * i + 1;
        }
        return filho;
    }

    public static void swap(PokemonQ9[] array, int i, int j) {
        PokemonQ9 temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static int compare(PokemonQ9 p1, PokemonQ9 p2) {
        if (p1.getHeight() > p2.getHeight()) {
            return 1;
        } else if (p1.getHeight() < p2.getHeight()) {
            return -1;
        } else {
            return p1.getName().compareTo(p2.getName());
        }
    }

    public static void registrarLog(int matricula, long tempoExecucao) {
        String nomeArquivo = "matrícula_heapsort.txt";
        try (FileWriter writer = new FileWriter(nomeArquivo, true)) {
            writer.write(matricula + "\t" + numComparacoes + "\t" + numMovimentacoes + "\t" + tempoExecucao + "\t");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String entrada;
        // String path = "pokemon.csv";
        String path = "/tmp/pokemon.csv";
        List<PokemonQ9> listaPokemon = new ArrayList<>();
        List<PokemonQ9> listaImpressao = new ArrayList<>();
        boolean isFim = false;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String linha = br.readLine();

            while ((linha = br.readLine()) != null) {
                // System.out.println(linha);
                PokemonQ9 j = new PokemonQ9();
                j.ler(linha);
                listaPokemon.add(j);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (!isFim) {
            entrada = scanner.nextLine();

            if (entrada.equals("FIM"))
                isFim = true;

            if (!isFim) {
                int id = Integer.parseInt(entrada);
                listaImpressao.add(buscarPorId(listaPokemon, id));
            }
        }

        long inicioB = System.nanoTime();
        sort(listaImpressao);
        long fimB = System.nanoTime();
        long tempoExecucao = Duration.ofNanos(fimB - inicioB).toMillis();
        registrarLog(1528647, tempoExecucao);

        for (PokemonQ9 p : listaImpressao) {
            p.imprimir();
        }

        scanner.close();
    }
}