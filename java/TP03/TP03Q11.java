import java.util.Scanner;

class Celula {
    public int elemento;
    public Celula inf, sup, esq, dir;

    public Celula() {
        this(0);
    }

    public Celula(int elemento) {
        this(elemento, null, null, null, null);
    }

    public Celula(int elemento, Celula inf, Celula sup, Celula esq, Celula dir) {
        this.elemento = elemento;
        this.inf = inf;
        this.sup = sup;
        this.esq = esq;
        this.dir = dir;
    }
}

class Matriz {
    private Celula inicio;
    private int linha, coluna;

    public Matriz(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
        inicializarMatriz();
    }

    public Matriz() {
        this(3, 3);
        inicializarMatriz();
    }

    private void inicializarMatriz() {
        // Criação da matriz encadeada.
        Celula[][] celulas = new Celula[linha][coluna];

        // Criando as células.
        for (int i = 0; i < linha; i++) {
            for (int j = 0; j < coluna; j++) {
                celulas[i][j] = new Celula();
                if (i > 0) {
                    celulas[i][j].sup = celulas[i - 1][j];
                    celulas[i - 1][j].inf = celulas[i][j];
                }
                if (j > 0) {
                    celulas[i][j].esq = celulas[i][j - 1];
                    celulas[i][j - 1].dir = celulas[i][j];
                }
            }
        }
        inicio = celulas[0][0];
    }

    public void inserir(int[][] elementos) {
        if (elementos.length != linha || elementos[0].length != coluna) {
            throw new IllegalArgumentException("Dimensões da matriz de entrada não coincidem.");
        }

        Celula atual = inicio;
        for (int i = 0; i < linha; i++) {
            Celula linhaAtual = atual;
            for (int j = 0; j < coluna; j++) {
                linhaAtual.elemento = elementos[i][j];
                linhaAtual = linhaAtual.dir;
            }
            atual = atual.inf;
        }
    }

    public void mostrar() {
        Celula atual = inicio;
        for (int i = 0; i < linha; i++) {
            Celula linhaAtual = atual;
            for (int j = 0; j < coluna; j++) {
                System.out.print(linhaAtual.elemento + " ");
                linhaAtual = linhaAtual.dir;
            }
            System.out.println();
            atual = atual.inf;
        }
    }

    public Matriz soma(Matriz m) {
        if (this.linha != m.linha || this.coluna != m.coluna) {
            throw new IllegalArgumentException("As dimensões das matrizes devem ser iguais para soma.");
        }

        Matriz resultado = new Matriz(this.linha, this.coluna);

        Celula c1 = this.inicio;
        Celula c2 = m.inicio;
        Celula r = resultado.inicio;

        for (int i = 0; i < linha; i++) {
            Celula linhaC1 = c1, linhaC2 = c2, linhaR = r;
            for (int j = 0; j < coluna; j++) {
                linhaR.elemento = linhaC1.elemento + linhaC2.elemento;
                linhaC1 = linhaC1.dir;
                linhaC2 = linhaC2.dir;
                linhaR = linhaR.dir;
            }
            c1 = c1.inf;
            c2 = c2.inf;
            r = r.inf;
        }

        return resultado;
    }

    public Matriz multiplicacao(Matriz m) {
        if (this.coluna != m.linha) {
            throw new IllegalArgumentException(
                    "O número de colunas da primeira matriz deve ser igual ao número de linhas da segunda.");
        }

        Matriz resultado = new Matriz(this.linha, m.coluna);

        for (int i = 0; i < this.linha; i++) {
            for (int j = 0; j < m.coluna; j++) {
                int soma = 0;
                Celula linhaA = getLinha(this.inicio, i);
                Celula colunaB = getColuna(m.inicio, j);

                for (int k = 0; k < this.coluna; k++) {
                    soma += linhaA.elemento * colunaB.elemento;
                    linhaA = linhaA.dir;
                    colunaB = colunaB.inf;
                }

                setElemento(resultado, i, j, soma);
            }
        }

        return resultado;
    }

    private Celula getLinha(Celula inicio, int linha) {
        Celula atual = inicio;
        for (int i = 0; i < linha; i++) {
            atual = atual.inf;
        }
        return atual;
    }

    private Celula getColuna(Celula inicio, int coluna) {
        Celula atual = inicio;
        for (int i = 0; i < coluna; i++) {
            atual = atual.dir;
        }
        return atual;
    }

    private void setElemento(Matriz matriz, int linha, int coluna, int elemento) {
        Celula atual = matriz.inicio;

        for (int i = 0; i < linha; i++) {
            atual = atual.inf;
        }
        for (int j = 0; j < coluna; j++) {
            atual = atual.dir;
        }

        atual.elemento = elemento;
    }

    public boolean isQuadrada() {
        return this.linha == this.coluna;
    }

    public void mostrarDiagonalPrincipal() {
        if (!isQuadrada()) {
            throw new IllegalArgumentException("A matriz precisa ser quadrada");
        }

        Celula atual = inicio;
        for (int i = 0; i < linha; i++) {
            System.out.print(atual.elemento + " ");
            atual = atual.inf != null ? atual.inf.dir : null;
        }
        System.out.println();
    }

    public void mostrarDiagonalSecundaria() {
        if (!isQuadrada()) {
            throw new IllegalArgumentException("A matriz precisa ser quadrada");
        }

        Celula atual = inicio;
        for (int i = 0; i < coluna - 1; i++) {
            atual = atual.dir;
        }

        for (int i = 0; i < linha; i++) {
            System.out.print(atual.elemento + " ");
            atual = atual.inf != null ? atual.inf.esq : null;
        }
        System.out.println();
    }
}

public class TP03Q11 {

    public static void inserirMatriz(int[][] matriz, int tam_linha, int tam_col, Scanner s) {
        for (int n = 0; n < tam_linha; n++) {
            for (int m = 0; m < tam_col; m++) {
                matriz[n][m] = s.nextInt();
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n_op = scanner.nextInt();

        for (int i = 0; i < n_op; i++) {
            Matriz m1, m2, soma, mult;
            int[][] ele1, ele2;

            int linha = scanner.nextInt();
            int coluna = scanner.nextInt();
            m1 = new Matriz(linha, coluna);
            ele1 = new int[linha][coluna];
            inserirMatriz(ele1, linha, coluna, scanner);
            m1.inserir(ele1);

            int linha2 = scanner.nextInt();
            int coluna2 = scanner.nextInt();
            m2 = new Matriz(linha2, coluna2);
            ele2 = new int[linha2][coluna2];
            inserirMatriz(ele2, linha2, coluna2, scanner);
            m2.inserir(ele2);

            soma = m1.soma(m2);
            mult = m1.multiplicacao(m2);

            m1.mostrarDiagonalPrincipal();
            m1.mostrarDiagonalSecundaria();

            soma.mostrar();
            mult.mostrar();
        }

        scanner.close();
    }

}