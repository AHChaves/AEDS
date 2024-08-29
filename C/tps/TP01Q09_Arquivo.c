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
        printf("%0.3lf\n", number);
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
