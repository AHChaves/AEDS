import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
        this.captureDate = LocalDate.of(0, 1, 1);
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

// --------------------------------------------------------- Inicio da
// implementacao de Pilha ------------------------------------------

class Celula {
    public PokemonQ9 elemento; // Elemento inserido na celula.
    public Celula prox; // Aponta a celula prox.

    public Celula() {
        elemento = new PokemonQ9();
    }

    public Celula(PokemonQ9 elemento) {
        this.elemento = elemento;
        this.prox = null;
    }
}

class Pilha {
    private Celula topo;

    public Pilha() {
        topo = null;
    }

    public void inserir(PokemonQ9 x) {
        Celula tmp = new Celula(x);
        tmp.prox = topo;
        topo = tmp;
        tmp = null;
    }

    public PokemonQ9 remover() throws Exception {
        if (topo == null) {
            throw new Exception("Erro ao remover!");
        }
        PokemonQ9 resp = topo.elemento;
        Celula tmp = topo;
        topo = topo.prox;
        tmp.prox = null;
        tmp = null;
        return resp;
    }

    public void mostrar() {
        int j = 0;
        for (Celula i = topo; i != null; i = i.prox, j++) {
            System.out.print("[" + j + "] ");
            i.elemento.imprimir();
        }
    }

    public void mostraPilha() {
        mostraPilha(topo, 0);
    }

    private int mostraPilha(Celula i, int pos) {
        if (i != null) {
            pos = mostraPilha(i.prox, pos);
            System.out.print("[" + pos + "] ");
            i.elemento.imprimir();
            pos++;
        }
        return pos;
    }

}

// --------------------------------------------------------- Fim da
// implementacao de Pilha --------------------------------------------

public class TP03Q9 {

    public static PokemonQ9 buscarPorId(List<PokemonQ9> lista, int id) {
        PokemonQ9 pk = new PokemonQ9();
        for (PokemonQ9 j : lista) {
            if (j.getId() == id) {
                pk = j.clone();
            }
        }
        return pk;
    }

    public static void listMenu(List<PokemonQ9> pl, Pilha l, Scanner scanner) {

        int n_operations = scanner.nextInt();

        for (int i = 0; i < n_operations; i++) {
            int number = 0;
            String entrada = scanner.next();

            switch (entrada) {
                case "I": {
                    number = scanner.nextInt();
                    try {
                        l.inserir(buscarPorId(pl, number));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "R": {
                    try {
                        PokemonQ9 p = l.remover();
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
        List<PokemonQ9> listaPokemon = new ArrayList<>();
        Pilha listaImpressao = new Pilha();
        boolean isFim = false;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String linha = br.readLine();
            while ((linha = br.readLine()) != null) {
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
                try {
                    listaImpressao.inserir(buscarPorId(listaPokemon, id));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        listMenu(listaPokemon, listaImpressao, scanner);

        listaImpressao.mostraPilha();

        scanner.close();
    }
}