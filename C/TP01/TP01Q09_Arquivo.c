#include <stdio.h>
#include <stdlib.h>

#define FILENAME "numbers.txt"

// Função para salvar números no arquivo
void salvar_arquivo(int n) {
    FILE *file = fopen(FILENAME, "wb");
    if (file == NULL) {
        return;
    }


    for (int i = 0; i < n; i++) {
        double number;
        scanf("%lf", &number);
        fwrite(&number, sizeof(double), 1, file);
    
    }

    fclose(file);
}

//funcao que imprime um numero com a precisao exata
void imprimirNumeroPrecisao(double numero) {
    // Se o número for inteiro, imprima sem casas decimais
    if (numero == (int)numero) {
        printf("%.0f\n", numero);
    }
    else{

        double fracao = numero - (int)numero; // pega apenas a casa fracionaria
        int casasDecimais = 0;

        // Determine o número exato de casas decimais pela constante multiplicacao por 10 para tornar o numer inteiro
        // e depois a subtracao pelo mesmo para sobrar apenas as casas decimais
        while (fracao != 0.0 && casasDecimais < 3) { 
            fracao *= 10;
            fracao -= (int)fracao;
            casasDecimais++;
        }

        // Formata a string para termos o numero exato de casas decimais
        char formato[10];
        sprintf(formato, "%%.%df\n", casasDecimais);
        printf(formato, numero);
    }
}

// Função para ler e exibir números do arquivo em ordem inversa
void ler_valores_inverso(int n) {
    FILE *file = fopen(FILENAME, "rb");
    if (file == NULL) {
        return;
    }

    // Obtém o tamanho do arquivo
    fseek(file, 0, SEEK_END);
    long fileSize = ftell(file);
    fseek(file, 0, SEEK_SET);

    for (int i = 0; i < n; i++) {
        fseek(file, fileSize - (i + 1) * sizeof(double), SEEK_SET);
        double number;
        fread(&number, sizeof(double), 1, file);
        imprimirNumeroPrecisao(number);
    }

    fclose(file);
}

int main() {
    int n;
    scanf("%d", &n);

    salvar_arquivo(n);
    ler_valores_inverso(n);

    return 0;
}
