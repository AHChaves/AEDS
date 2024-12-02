import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class PokemonQ01 {
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

   public PokemonQ01() {
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

   public PokemonQ01(int id, int generation, String name, String description, List<String> type,
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

   public PokemonQ01 clone() {
      PokemonQ01 pokemon = new PokemonQ01();
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
   public PokemonQ01 pokemon;
   public No esq, dir;

   public No(PokemonQ01 elemento) {
      this.pokemon = elemento;
      esq = dir = null;
   }

   public No(PokemonQ01 elemento, No esq, No dir) {
      this.pokemon = elemento;
      this.esq = esq;
      this.dir = dir;
   }
}

class ArvoreBinaria {
   private No raiz;

   public ArvoreBinaria() {
      raiz = null;
   }

   public boolean pesquisar(String nome) {
      System.out.print("raiz ");
      return pesquisar(nome, raiz);
   }

   private boolean pesquisar(String nome, No i) {
      boolean resp;
      if (i == null) {
         resp = false;
      } else if (nome.equals(i.pokemon.getName())) {
         resp = true;
      } else if (nome.compareTo(i.pokemon.getName()) < 0) {
         System.out.print("esq ");
         resp = pesquisar(nome, i.esq);
      } else {
         System.out.print("dir ");
         resp = pesquisar(nome, i.dir);
      }
      return resp;
   }

   public void inserir(PokemonQ01 x) throws Exception {
      raiz = inserir(x, raiz);
   }

   private No inserir(PokemonQ01 pokemon, No i) throws Exception {
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

   public void remover(String nome) throws Exception {
      raiz = remover(nome, raiz);
   }

   // Método privado recursivo para remover Pokémon pelo nome.
   private No remover(String nome, No i) throws Exception {

      if (i == null) {
         throw new Exception("Erro ao remover: Pokémon não encontrado!");

      } else if (nome.compareTo(i.pokemon.getName()) < 0) {
         i.esq = remover(nome, i.esq);

      } else if (nome.compareTo(i.pokemon.getName()) > 0) {
         i.dir = remover(nome, i.dir);

      } else if (i.dir == null) {
         i = i.esq;

      } else if (i.esq == null) {
         i = i.dir;

      } else {
         i.esq = antecessor(i, i.esq);
      }

      return i;
   }

   private No antecessor(No i, No j) {
      if (j.dir != null) {
         j.dir = antecessor(i, j.dir);

      } else {
         i.pokemon = j.pokemon;
         j = j.esq;
      }
      return j;
   }

}

// -------------------------------- Fim Implementacao arvore
// ---------------------------------------------------------

public class Q01 {

   public static PokemonQ01 buscarPorId(List<PokemonQ01> lista, int id) {
      PokemonQ01 pk = new PokemonQ01();
      for (PokemonQ01 j : lista) {
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
      List<PokemonQ01> listaPokemon = new ArrayList<>();
      ArvoreBinaria arvoreImpressao = new ArvoreBinaria();
      boolean isFim = false;

      try (BufferedReader br = new BufferedReader(new FileReader(path))) {

         String linha = br.readLine();
         while ((linha = br.readLine()) != null) {
            PokemonQ01 j = new PokemonQ01();
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
               arvoreImpressao.inserir(buscarPorId(listaPokemon, id));
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      }
      isFim = false;

      while (!isFim) {
         entrada = scanner.nextLine();

         if (entrada.equals("FIM"))
            isFim = true;

         if (!isFim) {
            try {
               System.out.println(entrada);
               System.out.print("=>");
               boolean achou = arvoreImpressao.pesquisar(entrada);
               System.out.println((achou) ? "SIM" : "NAO");
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      }

      scanner.close();
   }
}