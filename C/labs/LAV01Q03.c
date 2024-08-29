#include <stdio.h>
#include <ctype.h> // Para a função isupper()

// Função para contar caracteres maiúsculos
int contarMaiusculos(const char *s) {
    int contador = 0;
    while (*s) { // Enquanto não chegarmos ao fim da string
        if (isupper(*s)) { // Verifica se o caractere é maiúsculo
            contador++;
        }
        s++;
    }
    return contador;
}

int main() {
    char linha[256]; // Buffer para armazenar cada linha de entrada
    
    // Ler linha por linha da entrada padrão
    while (fgets(linha, sizeof(linha), stdin)) {
        // Remove o caractere de nova linha, se houver
        linha[strcspn(linha, "\n")] = '\0';
        
        // Verifica se a linha é "FIM"
        if (strcmp(linha, "FIM") == 0) {
            break;
        }
        
        // Contar e imprimir o número de maiúsculas
        int resultado = contarMaiusculos(linha);
        printf("%d\n", resultado);
    }
    
    return 0;
}
