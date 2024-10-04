#include <stdio.h>
#include <string.h>
#include <stdbool.h>

#define TAM_MAX_STRING (1000+1)

// Função para verificar se a string é um palíndromo
bool ehPalindromo(char *str, int left, int right) {
    bool resultado;

    if (left >= right) {
        resultado = true; 
    } else {
        if (str[left] == str[right]) {
            resultado = ehPalindromo(str, left + 1, right - 1); // Avança para a próxima comparação
        } else {
            resultado = false; // Não é palíndromo
        }
    }
    return resultado;
}

//funcao de comparacao de duas strings pela refernecia das mesmas
bool Str_Comp(const char* str1, const char* str2) {
    bool ehIgual = true;

    while (ehIgual || *str1 != '\0' || *str2 != '\0') {
        if (*str1 != *str2) {
            ehIgual = false;
        }
        str1++;
        str2++;
    }

    if (*str1 == '\0' && *str2 == '\0'){
        ehIgual = false;
    }

    return ehIgual;
}

int main() {
    char string[TAM_MAX_STRING];
    bool sair = false;

    while (!sair && fgets(string, TAM_MAX_STRING, stdin) != NULL) {
        string[strcspn(string, "\n")] = '\0';

        if(Str_Comp(string, "FIM"))
            sair = true;

        if(!sair){
            if (ehPalindromo(string, 0, strlen(string) - 1)) {
                printf("SIM\n");
            } else {
                printf("NAO\n");
            }
        }
    }
    return 0;
}
