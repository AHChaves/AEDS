#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h>

#define TAM_STRING (50 + 1)
#define TAM_LINHA (1000 + 1)
#define TAM_MAX_LISTA 6
#define NUM_MAX_TYPES 2
#define NUM_INFO 17

// IMPLEMENTACAO DE LISTA RETIRADA DO GITHUB DA DISCIPLINA
//--------------------------------------------------------------------------------------------------------------------//

typedef struct L
{
    char array[TAM_MAX_LISTA][TAM_STRING]; // Elementos da pilha
    int n;                                 // Quantidade de array.
} Lista;

/**
 * Inicializacoes
 */
void start(Lista *l)
{
    l->n = 0;
}

/**
 * Insere um elemento na primeira posicao da lista e move os demais
 * elementos para o fim da
 * @param x int elemento a ser inserido.
 */
void inserirInicio(char *x, Lista *l)
{
    int i;

    // validar insercao
    if (l->n >= TAM_MAX_LISTA)
    {
        printf("Erro ao inserir!");
        exit(1);
    }

    // levar elementos para o fim do array
    for (i = l->n; i > 0; i--)
    {
        strcpy(l->array[i], l->array[i - 1]);
    }

    strcpy(l->array[0], x);
    l->n++;
}

/**
 * Mostra os array separados por espacos.
 */
void mostrar(Lista *l)
{
    int i;

    for (i = l->n - 1; i >= 0; i--)
    {
        printf("'%s'", l->array[i]);
        if ((i != 0) && (l->n >= 2))
            printf(", ");
    }
}


// FIM IMPLEMENTACAO LISTA
//----------------------------------------------------------------------------------------------------------------------------------//

typedef struct Pokemon_s
{
    int id;
    int generation;
    char name[TAM_STRING];
    char description[TAM_STRING];
    Lista *types;
    Lista *abilities;
    double weight;
    double height;
    int captureRate;
    bool isLegendary;
    char captureDate[TAM_STRING];
} Pokemon;

Pokemon ConstrutorVazio()
{
    Pokemon p;
    p.id = 0;
    p.generation = 0;
    strcpy(p.name, "");
    strcpy(p.description, "");
    p.types = (Lista *)malloc(sizeof(Lista));
    p.abilities = (Lista *)malloc(sizeof(Lista));
    start(p.types);
    start(p.abilities);
    p.weight = 0.0;
    p.height = 0.0;
    p.captureRate = 0;
    p.isLegendary = false;
    strcpy(p.captureDate, "");

    return p;
}

Pokemon ConstrutorInicializado(int id, int generation, char name[], char description[], double weight, double height, int captureRate, bool isLegendary, char captureDate[])
{
    Pokemon p;
    p.id = id;
    p.generation = generation;
    strcpy(p.name, name);
    strcpy(p.description, description);
    p.types = (Lista *)malloc(sizeof(Lista));
    p.abilities = (Lista *)malloc(sizeof(Lista));
    start(p.types);
    start(p.abilities);
    p.weight = weight;
    p.height = height;
    p.captureRate = captureRate;
    p.isLegendary = isLegendary;
    strcpy(p.captureDate, captureDate);

    return p;
}

void imprimir(Pokemon poke)
{
    printf("[#%d -> %s: %s - [", poke.id, poke.name, poke.description);

    mostrar(poke.types);

    printf("] - [");

    mostrar(poke.abilities);

    printf("] - %0.1lfkg - %0.1lfm - %d%c", poke.weight, poke.height, poke.captureRate, '%');
    printf(" - %s - %d gen] - %s", (poke.isLegendary) ? "true" : "false", poke.generation, poke.captureDate);
}

Pokemon Buscar_Pokemon_ID(int id, Pokemon *poke, int n_Poke)
{
    Pokemon encontrado = ConstrutorVazio();
    for (int i = 0; i < n_Poke; i++)
    {
        if (id == poke[i].id)
        {
            encontrado = poke[i];
        }
    }
    return encontrado;
}

Pokemon ler(char *linha)
{
    Pokemon poke = ConstrutorVazio();
    char *string = strtok(linha, ",");

    poke.id = atoi(string);

    string = strtok(NULL, ",");
    poke.generation = atoi(string);

    string = strtok(NULL, ",");
    strcpy(poke.name, string);

    string = strtok(NULL, ",");
    strcpy(poke.description, string);

    for (int i = 0; i < NUM_MAX_TYPES; i++)
    {
        string = strtok(NULL, ",");
        if (string != NULL && strcmp(string, "") != 0 && strstr(string, "[") == NULL)
        {
            inserirInicio(string, poke.types);
        }
    }

    if (strstr(string, "]") != NULL)
    {
        char tmp[TAM_STRING];
        int count = 0;
        int k = 0;
        for (int j = 0; j < strlen(string); j++)
        {
            if (j != 0)
            {

                if (string[j] != '[' && string[j] != ']' && string[j] != 39 && string[j] != '"' && count < 2)
                {
                    tmp[k] = string[j];
                    k++;
                }
                else if (string[j] == 39 && count <= 2)
                {
                    count++;
                }
                else if (count >= 2)
                {
                    j = strlen(string);
                }
            }
        }
        tmp[k] = '\0';

        if (string != NULL && strcmp(string, "") != 0)
        {
            inserirInicio(tmp, poke.abilities);
        }
    }
    else
    {
        int i = 0;
        while (strstr(string, "]") == NULL)
        {
            if (strstr(string, "[") == NULL || poke.abilities->n >= 1)
            {
                string = strtok(NULL, ",");
            }

            char tmp[TAM_STRING];
            int count = 0;
            int k = 0;
            for (int j = 0; j < strlen(string); j++)
            {
                if (j != 0)
                {

                    if (string[j] != '[' && string[j] != ']' && string[j] != 39 && string[j] != '"' && count < 2)
                    {
                        tmp[k] = string[j];
                        k++;
                    }
                    else if (string[j] == 39 && count <= 2)
                    {
                        count++;
                    }
                    else if (count >= 2)
                    {
                        j = strlen(string);
                    }
                }
            }
            tmp[k] = '\0';

            if (string != NULL && strcmp(string, "") != 0)
            {
                inserirInicio(tmp, poke.abilities);
            }
            i++;
        }
    }
    char array[TAM_MAX_LISTA][TAM_STRING] = {"0", "0", "0", "0", "0", "0"};

    for (int j = 0; j < TAM_MAX_LISTA; j++)
    {
        string = strtok(NULL, ",");
        if (string != NULL)
        {
            strcpy(array[j], string);
        }
    }

    if (strcmp(array[4], "0") == 0)
    {
        poke.weight = atof(array[4]);
        poke.height = atof(array[3]);
        poke.captureRate = atoi(array[0]);
        poke.isLegendary = (strcmp(array[1], "1") == 0) ? true : false; // Lê 1 como true, caso contrário false
        strcpy(poke.captureDate, array[2]);
    }
    else
    {
        poke.weight = atof(array[0]);
        poke.height = atof(array[1]);
        poke.captureRate = atoi(array[2]);
        poke.isLegendary = (strcmp(array[3], "1") == 0) ? true : false; // Lê 1 como true, caso contrário false
        strcpy(poke.captureDate, array[4]);
    }

    return poke;
}

void swap(Pokemon *a, Pokemon *b) {
    Pokemon temp = *a;
    *a = *b;
    *b = temp;
}

void bolha(Pokemon *poke, int n){
    int i, j;
    for (i = 0; i < n; i++) {
      for (j = i; j < n; j++) {
         if (poke[i].id > poke[j].id) {
            swap(&poke[i], &poke[j]);
         }
      }
   }
}

int main()
{
    // FILE *arq = fopen("pokemon.csv", "r");
    FILE * arq = fopen("/tmp/pokemon.csv", "r");

    char linha[TAM_LINHA];
    int n_Pokemons = 801;
    Pokemon *poke = (Pokemon *)malloc(sizeof(Pokemon) * n_Pokemons);
    Pokemon *pokeSelect = (Pokemon *)malloc(sizeof(Pokemon));
    int lista_tam = 0;

    int poke_index = 0;

    fgets(linha, sizeof(linha), arq); // Descartar a primeira linha
    while (fgets(linha, sizeof(linha), arq) != NULL)
    {
        poke[poke_index] = ler(linha);
        poke_index++;
    }

    char entrada[55];
    strcpy(entrada, "entrada");

    while (strcmp(entrada, "FIM") != 0)
    {
        scanf("%s", entrada);
        if (strcmp(entrada, "FIM") != 0)
        {
            int id_input = atoi(entrada);
            pokeSelect[lista_tam] = Buscar_Pokemon_ID(id_input, poke, n_Pokemons);
            lista_tam++;
            pokeSelect = (Pokemon*)realloc(pokeSelect, sizeof(Pokemon)*(lista_tam+1));
        }
    }

    bolha(pokeSelect, lista_tam);

    for(int i = 0; i < lista_tam; i++){
        imprimir(pokeSelect[i]);
    }

    free(poke);
    fclose(arq);
}