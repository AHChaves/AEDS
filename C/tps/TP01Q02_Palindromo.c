#include <stdio.h>
#include <stdbool.h>
#include <wchar.h>
#include <locale.h>

//aprendi que todo tamanho de string alocado sem ser dinamicamente deve ser colocado pelo define, para uma maior facildiade de alteracao do dado
#define STR_MAX_SIZE (1000 + 1)

//funcao que informa o tamnho da string
int strSize(wchar_t str[]){

    int size = 0;

    for(int i = 0; i < STR_MAX_SIZE; i++){
        if(str[i] != '\0' || str[i] != '\n')
            size++;
        else
            i = STR_MAX_SIZE;
    }

    return size;
}

//funcao que checa se a string eh palindroma ou nao
int isPalindrome(const wchar_t* str) {
    int left = 0;
    int right = strSize(str) - 1;
    bool ehpalindromo = true;
    
    while (left < right) {
       
        if ((str[left] != str[right])) 
            ehpalindromo = false; 
        
        left++;
        right--;
    }
    
    return ehpalindromo; 
}

//Funcao Main
int main() {
    setlocale(LC_ALL, "");
    wchar_t input[STR_MAX_SIZE];
    
    while (wscanf(L"%ls", input) == 1 ) {
        
        if (isPalindrome(input)) {
            printf("SIM\n");
        } else {
            printf("NAO\n");
        }
    }
    
    return 0;
}
