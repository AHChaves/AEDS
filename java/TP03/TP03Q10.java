import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class PokemonQ10 {
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

    public PokemonQ10() {
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

    public PokemonQ10(int id, int generation, String name, String description, List<String> type,
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

    public PokemonQ10 clone() {
        PokemonQ10 pokemon = new PokemonQ10();
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

class CelulaDupla {
    public PokemonQ10 elemento;
    public CelulaDupla ant;
    public CelulaDupla prox;

    public CelulaDupla() {
        elemento = new PokemonQ10();
    }

    public CelulaDupla(PokemonQ10 elemento) {
        this.elemento = elemento;
        this.ant = this.prox = null;
    }
}

class ListaDupla {
    public CelulaDupla primeiro;
    public CelulaDupla ultimo;

    public ListaDupla() {
        primeiro = new CelulaDupla();
        ultimo = primeiro;
    }

    public void inserirFim(PokemonQ10 x) {
        ultimo.prox = new CelulaDupla(x);
        ultimo.prox.ant = ultimo;
        ultimo = ultimo.prox;
    }

    public void mostrar() {

        for (CelulaDupla i = primeiro.prox; i != null; i = i.prox) {
            i.elemento.imprimir();
        }
    }
}
// --------------------------------------------------------- Fim da
// implementacao de Pilha --------------------------------------------

class Quicksort {

    public void sort(ListaDupla lista) {
        quicksort(lista, lista.primeiro.prox, lista.ultimo);
    }

    private void quicksort(ListaDupla lista, CelulaDupla esq, CelulaDupla dir) {
        if (esq != null && dir != null && esq != dir && esq != dir.prox) {
            CelulaDupla pivo = partition(lista, esq, dir);
            quicksort(lista, esq, pivo.ant);
            quicksort(lista, pivo.prox, dir);
        }
    }

    private CelulaDupla partition(ListaDupla lista, CelulaDupla esq, CelulaDupla dir) {
        PokemonQ10 pivo = dir.elemento;
        CelulaDupla i = esq.ant;

        for (CelulaDupla j = esq; j != dir; j = j.prox) {
            if (comparaPokemon(j.elemento, pivo) <= 0) {
                i = (i == null) ? esq : i.prox;
                swap(i, j);
            }
        }

        i = (i == null) ? esq : i.prox;
        swap(i, dir);
        return i;
    }

    private int comparaPokemon(PokemonQ10 a, PokemonQ10 b) {
        if (a.getGeneration() < b.getGeneration()) {
            return -1;
        } else if (a.getGeneration() > b.getGeneration()) {
            return 1;
        }
        return a.getName().compareTo(b.getName());
    }

    private void swap(CelulaDupla a, CelulaDupla b) {
        PokemonQ10 temp = a.elemento;
        a.elemento = b.elemento;
        b.elemento = temp;
    }
}

public class TP03Q10 {

    public static PokemonQ10 buscarPorId(List<PokemonQ10> lista, int id) {
        PokemonQ10 pk = new PokemonQ10();
        for (PokemonQ10 j : lista) {
            if (j.getId() == id) {
                pk = j.clone();
            }
        }
        return pk;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String entrada;
        // String path = "pokemon.csv";
        String path = "/tmp/pokemon.csv";
        List<PokemonQ10> listaPokemon = new ArrayList<>();
        ListaDupla listaImpressao = new ListaDupla();
        boolean isFim = false;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String linha = br.readLine();
            while ((linha = br.readLine()) != null) {
                PokemonQ10 j = new PokemonQ10();
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

        Quicksort quicksort = new Quicksort();
        quicksort.sort(listaImpressao);

        listaImpressao.mostrar();

        scanner.close();
    }
}