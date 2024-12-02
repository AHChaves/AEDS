import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class PokemonQ1 {
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

    public PokemonQ1() {
        this.id = 0;
        this.generation = 0;
        this.name = "";
        this.description = "";
        this.weight = 0;
        this.height = 0;
        this.captureRate = 0;
        this.isLegendary = false;
        this.captureDate = LocalDate.of(0, 1, 1);
    }

    public PokemonQ1(int id, int generation, String name, String description, List<String> type, List<String> abilities,
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

    public PokemonQ1 clone() {
        PokemonQ1 pokemon = new PokemonQ1();
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

class Lista {
    private PokemonQ1[] array;
    private int n;

    public Lista() {
        this(6);
    }

    public Lista(int tamanho) {
        array = new PokemonQ1[tamanho];
        n = 0;
    }

    public int getN() {
        return this.n;
    }

    public void inserirInicio(PokemonQ1 x) throws Exception {

        // validar insercao
        if (n >= array.length) {
            throw new Exception("Erro ao inserir!");
        }

        // levar elementos para o fim do array
        for (int i = n; i > 0; i--) {
            array[i] = array[i - 1];
        }

        array[0] = x;
        n++;
    }

    public void inserirFim(PokemonQ1 x) throws Exception {

        // validar insercao
        if (n >= array.length) {
            throw new Exception("Erro ao inserir!");
        }

        array[n] = x;
        n++;
    }

    public void inserir(PokemonQ1 x, int pos) throws Exception {

        // validar insercao
        if (n >= array.length || pos < 0 || pos > n) {
            throw new Exception("Erro ao inserir!");
        }

        // levar elementos para o fim do array
        for (int i = n; i > pos; i--) {
            array[i] = array[i - 1];
        }

        array[pos] = x;
        n++;
    }

    public PokemonQ1 removerInicio() throws Exception {

        // validar remocao
        if (n == 0) {
            throw new Exception("Erro ao remover!");
        }

        PokemonQ1 resp = array[0];
        n--;

        for (int i = 0; i < n; i++) {
            array[i] = array[i + 1];
        }

        return resp;
    }

    public PokemonQ1 removerFim() throws Exception {

        // validar remocao
        if (n == 0) {
            throw new Exception("Erro ao remover!");
        }

        return array[--n];
    }

    public PokemonQ1 remover(int pos) throws Exception {

        if (n == 0 || pos < 0 || pos >= n) {
            throw new Exception("Erro ao remover!");
        }

        PokemonQ1 resp = array[pos];
        n--;

        for (int i = pos; i < n; i++) {
            array[i] = array[i + 1];
        }

        return resp;
    }

    public void mostrar() {
        for (int i = 0; i < n; i++) {
            System.out.print("[" + i + "] ");
            array[i].imprimir();
        }
    }

    public boolean pesquisar(PokemonQ1 x) {
        boolean retorno = false;
        for (int i = 0; i < n && retorno == false; i++) {
            retorno = (array[i] == x);
        }
        return retorno;
    }
}

public class TP03Q1 {

    public static PokemonQ1 buscarPorId(List<PokemonQ1> lista, int id) {
        PokemonQ1 pk = new PokemonQ1();
        for (PokemonQ1 j : lista) {
            if (j.getId() == id) {
                pk = j.clone();
            }
        }
        return pk;
    }

    public static void listMenu(List<PokemonQ1> pl, Lista l, Scanner scanner) {

        int n_operations = scanner.nextInt();

        for (int i = 0; i < n_operations; i++) {
            int number = 0, pos = 0;
            String entrada = scanner.next();

            switch (entrada) {
                case "II": {
                    number = scanner.nextInt();
                    PokemonQ1 p = buscarPorId(pl, number);
                    try {
                        l.inserirInicio(p);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "I*": {
                    pos = scanner.nextInt();
                    number = scanner.nextInt();
                    try {
                        l.inserir(buscarPorId(pl, number), pos);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "IF": {
                    number = scanner.nextInt();
                    try {
                        l.inserirFim(buscarPorId(pl, number));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "RI": {
                    try {
                        PokemonQ1 p = l.removerInicio();
                        System.out.println("(R) " + p.getName());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "RF": {
                    try {
                        PokemonQ1 p = l.removerFim();
                        System.out.println("(R) " + p.getName());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "R*": {
                    pos = scanner.nextInt();
                    try {
                        PokemonQ1 p = l.remover(pos);
                        System.out.println("(R) " + p.getName());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }

            }
        }

    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String entrada;
        // String path = "pokemon.csv";
        String path = "/tmp/pokemon.csv";
        List<PokemonQ1> listaPokemon = new ArrayList<>();
        Lista listaImpressao = new Lista(801);
        boolean isFim = false;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String linha = br.readLine();
            while ((linha = br.readLine()) != null) {
                PokemonQ1 j = new PokemonQ1();
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
                try {
                    listaImpressao.inserirFim(buscarPorId(listaPokemon, id));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        listMenu(listaPokemon, listaImpressao, scanner);

        listaImpressao.mostrar();

        scanner.close();
    }
}