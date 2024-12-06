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

class PokemonQ02 {
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

    public PokemonQ02() {
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

    public PokemonQ02(int id, int generation, String name, String description, List<String> type,
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

    public PokemonQ02 clone() {
        PokemonQ02 pokemon = new PokemonQ02();
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

// ------------------------------------ Implementacao arvore
// ------------------------------------------------------

class No {
    public PokemonQ02 pokemon;
    public No esq, dir;

    public No(PokemonQ02 elemento) {
        this.pokemon = elemento;
        esq = dir = null;
    }

    public No(PokemonQ02 elemento, No esq, No dir) {
        this.pokemon = elemento;
        this.esq = esq;
        this.dir = dir;
    }
}

class NoDuplo {
    public int capture;
    public ArvoreBinaria arvorePokemon;
    public NoDuplo esq, dir;

    public NoDuplo(int elemento) {
        this.capture = elemento;
        this.arvorePokemon = new ArvoreBinaria();
        esq = dir = null;
    }

    public NoDuplo(int elemento, ArvoreBinaria no, NoDuplo esq, NoDuplo dir) {
        this.capture = elemento;
        this.arvorePokemon = no;
        this.esq = esq;
        this.dir = dir;
    }
}

class ArvoreBinaria {
    private No raiz;

    public ArvoreBinaria() {
        raiz = null;
    }

    public boolean pesquisar(String nome, int cmp) {
        return pesquisar(nome, raiz, cmp);
    }

    private boolean pesquisar(String nome, No i, int cmp) {
        boolean resp;
        if (i == null) {
            resp = false;
        } else if (nome.equals(i.pokemon.getName())) {
            cmp++;
            resp = true;
        } else if (nome.compareTo(i.pokemon.getName()) < 0) {
            cmp++;
            System.out.print("esq ");
            resp = pesquisar(nome, i.esq, cmp);
        } else {
            System.out.print("dir ");
            resp = pesquisar(nome, i.dir, cmp);
        }
        return resp;
    }

    public void inserir(PokemonQ02 x) throws Exception {
        raiz = inserir(x, raiz);
    }

    private No inserir(PokemonQ02 pokemon, No i) throws Exception {
        if (i == null) {
            i = new No(pokemon);
        } else if (pokemon.getName().compareTo(i.pokemon.getName()) < 0) {
            i.esq = inserir(pokemon, i.esq);
        } else if (pokemon.getName().compareTo(i.pokemon.getName()) > 0) {
            i.dir = inserir(pokemon, i.dir);
        } else {
            throw new Exception("Erro ao inserir: Pokémon já existe!");
        }

        return i;
    }
}

class ArvoreDupla {
    private NoDuplo raiz;

    public ArvoreDupla() {
        raiz = null;
    }

    public boolean pesquisar(String poke, int cmp) {
        System.out.print("raiz ");
        return pesquisar(poke, raiz, cmp);
    }

    private boolean pesquisar(String nome, NoDuplo i, int cmp) {

        if (i == null) {
            return false;
        }

        if (i.arvorePokemon.pesquisar(nome, cmp)) {
            cmp++;
            return true;
        }

        // Continuar pesquisando na subárvore esquerda e direita do nó atual
        System.out.print(" ESQ ");
        if (pesquisar(nome, i.esq, cmp)) {
            cmp++;
            return true;
        }

        System.out.print(" DIR ");
        return pesquisar(nome, i.dir, cmp);
    }

    public void inserir(int x) throws Exception {
        raiz = inserir(x, raiz);
    }

    private NoDuplo inserir(int capture, NoDuplo i) throws Exception {
        if (i == null) {
            i = new NoDuplo(capture);
        } else if (capture < i.capture) {
            i.esq = inserir(capture, i.esq);
        } else if (capture > i.capture) {
            i.dir = inserir(capture, i.dir);
        } else {
            throw new Exception("Erro ao inserir: Pokémon já existe!");
        }

        return i;
    }

    public void inserirPokemon(PokemonQ02 x) throws Exception {
        inserirPokemon(x, raiz);
    }

    private NoDuplo inserirPokemon(PokemonQ02 poke, NoDuplo i) throws Exception {
        if ((poke.getCaptureRate() % 15) == i.capture) {
            i.arvorePokemon.inserir(poke);
        } else if ((poke.getCaptureRate() % 15) < i.capture) {
            i.esq = inserirPokemon(poke, i.esq);
        } else if ((poke.getCaptureRate() % 15) > i.capture) {
            i.dir = inserirPokemon(poke, i.dir);
        } else {
            throw new Exception("Erro ao inserir: Pokémon já existe!");
        }

        return i;
    }
}

// -------------------------------- Fim Implementacao arvore
// ---------------------------------------------------------

public class Q02 {

    private static int numComparacoes = 0, numMovimentacoes = 0;

    public static PokemonQ02 buscarPorId(List<PokemonQ02> lista, int id) {
        PokemonQ02 pk = new PokemonQ02();
        for (PokemonQ02 j : lista) {
            if (j.getId() == id) {
                pk = j.clone();
            }
        }
        return pk;
    }

    public static PokemonQ02 buscarPorNome(List<PokemonQ02> lista, String nome) {
        PokemonQ02 pk = new PokemonQ02();
        for (PokemonQ02 j : lista) {
            if (j.getName().compareTo(nome) == 0) {
                pk = j.clone();
            }
        }
        return pk;
    }

    public static void registrarLog(int matricula, long tempoExecucao) {
        String nomeArquivo = "matrícula_arvoreArvore.txt";
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
        List<PokemonQ02> listaPokemon = new ArrayList<>();
        ArvoreDupla arvoreImpressao = new ArvoreDupla();
        boolean isFim = false;
        int captureRates[] = { 7, 3, 11, 1, 5, 9, 13, 0, 2, 4, 6, 8, 10, 12, 14 };

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String linha = br.readLine();
            while ((linha = br.readLine()) != null) {
                PokemonQ02 j = new PokemonQ02();
                j.ler(linha);
                listaPokemon.add(j);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < captureRates.length; i++) {
            try {
                arvoreImpressao.inserir(captureRates[i]);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        while (!isFim) {
            entrada = scanner.nextLine();

            if (entrada.equals("FIM"))
                isFim = true;

            if (!isFim) {
                int id = Integer.parseInt(entrada);
                try {
                    arvoreImpressao.inserirPokemon(buscarPorId(listaPokemon, id));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        isFim = false;

        long inicioB = System.nanoTime();

        while (!isFim) {
            entrada = scanner.nextLine();

            if (entrada.equals("FIM"))
                isFim = true;

            if (!isFim) {
                try {
                    System.out.print("=> ");
                    System.out.println(entrada);
                    boolean achou = arvoreImpressao.pesquisar(entrada, numComparacoes);
                    System.out.println((achou) ? " SIM" : " NAO");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        long fimB = System.nanoTime();

        long tempoExecucao = Duration.ofNanos(fimB - inicioB).toMillis();
        registrarLog(1528647, tempoExecucao);

        scanner.close();
    }
}