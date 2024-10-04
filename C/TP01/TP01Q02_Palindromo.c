#include <stdio.h>
#include <stdbool.h>
#include <string.h>

//aprendi que todo tamanho de string alocado sem ser dinamicamente deve ser colocado pelo define, para uma maior facildiade de alteracao do dado
#define STR_MAX_SIZE (1000 + 1)

//funcao que informa o tamnho da string
int strSize(const char str[]){

    int size = 0;

    for(int i = 0; i < STR_MAX_SIZE; i++){
        if(str[i] != '\0')
            size++;
        else
            i = STR_MAX_SIZE;
    }

    return size;
}

//funcao que checa se a string eh palindroma ou nao
int ehPalindromo(const char* str) {
    int left = 0;
    int right = strSize(str) - 1;
    bool palindromo = true;
    
    while (left < right) {
       
        if ((str[left] != str[right])){
            palindromo = false; 
            left = right;
        }
        
        left++;
        right--;
    }
    
    return palindromo; 
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

//Funcao Main
int main() {
    char string[STR_MAX_SIZE];
    bool sair = false;

    while (!sair && fgets(string, STR_MAX_SIZE, stdin) != NULL) {
        string[strcspn(string, "\n")] = '\0';

        if(Str_Comp(string, "FIM"))
            sair = true;

        if(!sair){
            if (ehPalindromo(string)) {
                printf("SIM\n");
            } else {
                printf("NAO\n");
            }
        }
    }
    
    return 0;
}
