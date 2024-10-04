#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <stdbool.h>

#define TAM_MAX_STRiNG (100+1)

// Funcoa que substitui os valores de char1 e char2
void substituirRecursivo(char* str, char char1, char char2, int index) {
    if (str[index] != '\0') {
        if (str[index] == char1) {
            str[index] = char2;
        }
        substituirRecursivo(str, char1, char2, index + 1);
    }
}

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
    char str[TAM_MAX_STRiNG];
    srand(4); // Seed para geração de números aleatórios
    bool sair = false;

    while (!sair && scanf("%s", str) == 1) {

        if(Str_Comp(str, "FIM"))
            sair = true;

        if(!sair){
            char char1 = 'a' + (rand() % 26);
            char char2 = 'a' + (rand() % 26);

            substituirRecursivo(str, char1, char2, 0);

            printf("%s", str);
        }
    }

    return 0;
}
