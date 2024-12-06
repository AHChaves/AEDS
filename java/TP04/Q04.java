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

class PokemonQ04 {
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

   public PokemonQ04() {
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

   public PokemonQ04(int id, int generation, String name, String description, List<String> type,
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

   public PokemonQ04 clone() {
      PokemonQ04 pokemon = new PokemonQ04();
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
class NoAN {
   public boolean cor;
   public PokemonQ04 pokemon;
   public NoAN esq, dir;

   public NoAN() {
      pokemon = new PokemonQ04();
   }

   public NoAN(PokemonQ04 elemento) {
      this(elemento, false, null, null);
   }

   public NoAN(PokemonQ04 elemento, boolean cor) {
      this(elemento, cor, null, null);
   }

   public NoAN(PokemonQ04 elemento, boolean cor, NoAN esq, NoAN dir) {
      this.cor = cor;
      this.pokemon = elemento;
      this.esq = esq;
      this.dir = dir;
   }
}

class Alvinegra {
   private NoAN raiz;

   public Alvinegra() {
      raiz = null;
   }

   public boolean pesquisar(String nome, int cmp) {
      System.out.print("raiz ");
      return pesquisar(nome, raiz, cmp);
   }

   private boolean pesquisar(String nome, NoAN i, int cmp) {
      boolean resp;
      if (i == null) {
         resp = false;
      } else if (nome.equals(i.pokemon.getName())) {
         resp = true;
         cmp++;
      } else if (nome.compareTo(i.pokemon.getName()) < 0) {
         System.out.print("esq ");
         cmp++;
         resp = pesquisar(nome, i.esq, cmp);
      } else {
         System.out.print("dir ");
         cmp++;
         resp = pesquisar(nome, i.dir, cmp);
      }
      return resp;
   }

   public void inserir(PokemonQ04 elemento) throws Exception {
      if (raiz == null) {
         raiz = new NoAN(elemento);
      } else if (raiz.esq == null && raiz.dir == null) {
         if (elemento.getName().compareTo(raiz.pokemon.getName()) < 0) {
            raiz.esq = new NoAN(elemento);
         } else if (elemento.getName().compareTo(raiz.pokemon.getName()) > 0) {
            raiz.dir = new NoAN(elemento);
         } else {
            throw new IllegalStateException("Elemento já está na árvore");
         }
      } else if (raiz.esq == null) {
         if (elemento.getName().compareTo(raiz.pokemon.getName()) < 0) {
            raiz.esq = new NoAN(elemento);
         } else if (elemento.getName().compareTo(raiz.dir.pokemon.getName()) < 0) {
            raiz.esq = new NoAN(raiz.pokemon);
            raiz.pokemon = elemento;
         } else if (elemento.getName().compareTo(raiz.dir.pokemon.getName()) > 0) {
            raiz.esq = new NoAN(raiz.pokemon);
            raiz.pokemon = raiz.dir.pokemon;
            raiz.dir.pokemon = elemento;
         } else {
            throw new IllegalStateException("Elemento já está na árvore");
         }
         raiz.esq.cor = raiz.dir.cor = false;
      } else if (raiz.dir == null) {
         if (elemento.getName().compareTo(raiz.pokemon.getName()) > 0) {
            raiz.dir = new NoAN(elemento);
         } else if (elemento.getName().compareTo(raiz.esq.pokemon.getName()) > 0) {
            raiz.dir = new NoAN(raiz.pokemon);
            raiz.pokemon = elemento;
         } else if (elemento.getName().compareTo(raiz.esq.pokemon.getName()) < 0) {
            raiz.dir = new NoAN(raiz.pokemon);
            raiz.pokemon = raiz.esq.pokemon;
            raiz.esq.pokemon = elemento;
         } else {
            throw new IllegalStateException("Elemento já está na árvore");
         }
         raiz.esq.cor = raiz.dir.cor = false;
      } else {
         inserir(elemento, null, null, null, raiz);
      }
      raiz.cor = false;
   }

   private void balancear(NoAN bisavo, NoAN avo, NoAN pai, NoAN i) {
      // Se o pai tambem e preto, reequilibrar a arvore, rotacionando o avo
      if (pai.cor == true) {
         // 4 tipos de reequilibrios e acoplamento
         if (pai.pokemon.getName().compareTo(avo.pokemon.getName()) > 0) { // rotacao a esquerda ou direita-esquerda
            if (i.pokemon.getName().compareTo(pai.pokemon.getName()) > 0) {
               avo = rotacaoEsq(avo);
            } else {
               avo = rotacaoDirEsq(avo);
            }
         } else { // rotacao a direita ou esquerda-direita
            if (i.pokemon.getName().compareTo(pai.pokemon.getName()) < 0) {
               avo = rotacaoDir(avo);
            } else {
               avo = rotacaoEsqDir(avo);
            }
         }
         if (bisavo == null) {
            raiz = avo;
         } else if (avo.pokemon.getName().compareTo(bisavo.pokemon.getName()) < 0) {
            bisavo.esq = avo;
         } else {
            bisavo.dir = avo;
         }
         // reestabelecer as cores apos a rotacao
         avo.cor = false;
         avo.esq.cor = avo.dir.cor = true;
      } // if(pai.cor == true)
   }

   private void inserir(PokemonQ04 poke, NoAN bisavo, NoAN avo, NoAN pai, NoAN i) throws Exception {
      if (i == null) {
         if (poke.getName().compareTo(pai.pokemon.getName()) < 0) {
            i = pai.esq = new NoAN(poke, true);
         } else {
            i = pai.dir = new NoAN(poke, true);
         }
         if (pai.cor == true) {
            balancear(bisavo, avo, pai, i);
         }
      } else {
         if (i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true) {
            i.cor = true;
            i.esq.cor = i.dir.cor = false;
            if (i == raiz) {
               i.cor = false;
            } else if (pai.cor == true) {
               balancear(bisavo, avo, pai, i);
            }
         }
         if (poke.getName().compareTo(i.pokemon.getName()) < 0) {
            inserir(poke, avo, pai, i, i.esq);
         } else if (poke.getName().compareTo(i.pokemon.getName()) > 0) {
            inserir(poke, avo, pai, i, i.dir);
         } else {
            throw new Exception("Erro inserir (elemento repetido)!");
         }
      }
   }

   private NoAN rotacaoDir(NoAN no) {
      NoAN noEsq = no.esq;
      NoAN noEsqDir = noEsq.dir;

      noEsq.dir = no;
      no.esq = noEsqDir;

      return noEsq;
   }

   private NoAN rotacaoEsq(NoAN no) {
      NoAN noDir = no.dir;
      NoAN noDirEsq = noDir.esq;

      noDir.esq = no;
      no.dir = noDirEsq;
      return noDir;
   }

   private NoAN rotacaoDirEsq(NoAN no) {
      no.dir = rotacaoDir(no.dir);
      return rotacaoEsq(no);
   }

   private NoAN rotacaoEsqDir(NoAN no) {
      no.esq = rotacaoEsq(no.esq);
      return rotacaoDir(no);
   }
}

// -------------------------------- Fim Implementacao arvore
// ---------------------------------------------------------

public class Q04 {

   private static int numComparacoes = 0, numMovimentacoes = 0;

   public static PokemonQ04 buscarPorId(List<PokemonQ04> lista, int id) {
      PokemonQ04 pk = new PokemonQ04();
      for (PokemonQ04 j : lista) {
         if (j.getId() == id) {
            pk = j.clone();
         }
      }
      return pk;
   }

   public static void registrarLog(int matricula, long tempoExecucao) {
      String nomeArquivo = "matrícula_avinegra.txt";
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
      List<PokemonQ04> listaPokemon = new ArrayList<>();
      Alvinegra arvoreImpressao = new Alvinegra();
      boolean isFim = false;

      try (BufferedReader br = new BufferedReader(new FileReader(path))) {

         String linha = br.readLine();
         while ((linha = br.readLine()) != null) {
            PokemonQ04 j = new PokemonQ04();
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

      long inicioB = System.nanoTime();

      while (!isFim) {
         entrada = scanner.nextLine();

         if (entrada.equals("FIM"))
            isFim = true;

         if (!isFim) {
            try {
               System.out.println(entrada);
               boolean achou = arvoreImpressao.pesquisar(entrada, numComparacoes);
               System.out.println((achou) ? "SIM" : "NAO");
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