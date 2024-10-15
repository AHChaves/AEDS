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

class PokemonQ11 {
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

    public PokemonQ11() {
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

    public PokemonQ11(int id, int generation, String name, String description, List<String> type,
            List<String> abilities,
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

    public PokemonQ11 clone() {
        PokemonQ11 pokemon = new PokemonQ11();
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

public class Q11 {

    private static int numComparacoes = 0, numMovimentacoes = 0;

    public static PokemonQ11 buscarPorId(List<PokemonQ11> lista, int id) {
        PokemonQ11 pk = new PokemonQ11();
        for (PokemonQ11 j : lista) {
            if (j.getId() == id) {
                pk = j.clone();
            }
        }
        return pk;
    }

    public static void sort(List<PokemonQ11> poke) {
        int maiorCaptureRate = getMaiorCaptureRate(poke);
        int[] count = new int[maiorCaptureRate + 1];
        PokemonQ11[] ordenado = new PokemonQ11[poke.size()];

        for (int i = 0; i < count.length; i++) {
            count[i] = 0;
        }

        for (PokemonQ11 pokemon : poke) {
            count[pokemon.getCaptureRate()]++;
        }

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        for (int i = poke.size() - 1; i >= 0; i--) {
            PokemonQ11 pokemon = poke.get(i);
            int captureRate = pokemon.getCaptureRate();
            ordenado[count[captureRate] - 1] = pokemon;
            numMovimentacoes++;
            count[captureRate]--;
        }

        for (int i = 0; i < poke.size(); i++) {
            poke.set(i, ordenado[i]);
            numMovimentacoes++;
        }

        ordenarPorNomeEmCasoDeEmpate(poke);
    }

    public static void ordenarPorNomeEmCasoDeEmpate(List<PokemonQ11> poke) {
        for (int i = 0; i < poke.size() - 1; i++) {
            for (int j = i + 1; j < poke.size(); j++) {
                if (poke.get(i).getCaptureRate() == poke.get(j).getCaptureRate()) {
                    if (poke.get(i).getName().compareToIgnoreCase(poke.get(j).getName()) > 0) {
                        PokemonQ11 temp = poke.get(i);
                        poke.set(i, poke.get(j));
                        poke.set(j, temp);
                        numMovimentacoes += 3;
                    }
                    numComparacoes++;
                }
                numComparacoes++;
            }
        }
    }

    public static int getMaiorCaptureRate(List<PokemonQ11> poke) {
        int maior = poke.get(0).getCaptureRate();
        for (PokemonQ11 pokemon : poke) {
            if (pokemon.getCaptureRate() > maior) {
                maior = pokemon.getCaptureRate();
            }
            numComparacoes++;
        }
        return maior;
    }

    public static void registrarLog(int matricula, long tempoExecucao) {
        String nomeArquivo = "matrícula_countingsort.txt";
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
        List<PokemonQ11> listaPokemon = new ArrayList<>();
        List<PokemonQ11> listaImpressao = new ArrayList<>();
        boolean isFim = false;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String linha = br.readLine();

            while ((linha = br.readLine()) != null) {
                // System.out.println(linha);
                PokemonQ11 j = new PokemonQ11();
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

        for (PokemonQ11 p : listaImpressao) {
            p.imprimir();
        }

        scanner.close();
    }
}