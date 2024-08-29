#include <stdio.h>
#include <ctype.h> 
#include <string.h> 

int contarMaiusculosRecursivo(const char *s) {
    if (*s == '\0') {
        return 0;
    }
    
    int contagemAtual = isupper(*s) ? 1 : 0;

    return contagemAtual + contarMaiusculosRecursivo(s + 1);
}

int main() {
    char linha[256]; 
    
    while (fgets(linha, sizeof(linha), stdin)) {
        linha[strcspn(linha, "\n")] = '\0';
        
        if (strcmp(linha, "FIM") == 0) {
            break;
        }
        
        int resultado = contarMaiusculosRecursivo(linha);
        printf("%d\n", resultado);
    }
    
    return 0;
}