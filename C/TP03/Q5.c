#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h>
#include <time.h>
#include <limits.h>

#define TAM_STRING (50 + 1)
#define TAM_LINHA (1000 + 1)
#define QTD_POKEMONS 801
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

//======================================================= Lista de Pokemons =============================================

typedef struct Celula{
    Pokemon elemento;
    struct Celula *prox;
}Celula;

Celula* new_celula(Pokemon elemento){
    Celula *temp = (Celula*)malloc(sizeof(Celula));
    temp->elemento = elemento;
    temp->prox = NULL;
    return temp;
}

typedef struct LP{
    struct Celula *primeiro, *ultimo;
    int size;
} Lista_Pokemon;

Lista_Pokemon new_lista(){
    Lista_Pokemon temp;
    temp.primeiro = temp.ultimo = new_celula(ConstrutorVazio());
    temp.size = 0;
    return temp;
}

int size_lista(Lista_Pokemon *l){
    return l->size;
}

void insert_begin(Lista_Pokemon *l, Pokemon elemento){
    
    Celula *temp = new_celula(ConstrutorVazio());
    temp->prox = l->primeiro;

    l->primeiro->elemento = elemento;    
    l->primeiro = temp;
    l->size++;
}

void insert_end(Lista_Pokemon *l, Pokemon elemento){    
    l->ultimo->prox = new_celula(elemento);
    l->ultimo = l->ultimo->prox;
    l->size++;
}

void insert_at(Lista_Pokemon *l, Pokemon elemento, int pos){
    
    if(pos < 0 || pos > l->size)
        printf("Erro ao tentar inserir na posicao (%d/ tamanho = %d) invalida!", pos, l->size);
    else if (pos == 0)
        insert_begin(l, elemento);
    else if (pos == l->size)
        insert_end(l, elemento);
    else{
        Celula *ant = l->primeiro;
        for(int i=0; i<pos;i++) 
            ant = ant->prox;
            
        Celula *temp = new_celula(elemento);  
        temp->prox = ant->prox;
        ant->prox = temp;
        l->size++;
    }
}

Pokemon remove_at(Lista_Pokemon *l, int pos){

    if(l->primeiro == l->ultimo){
        printf("\nA lista esta vazia!\n");
        return ConstrutorVazio();
    }else if(pos < 0 || pos > l->size-1)
        printf("Erro ao tentar remover item da posicao (%d/ tamanho = %d) invalida!", pos, l->size);
    else{
        
        Celula *ant = l->primeiro;
        for(int i=0; i<pos;i++) 
            ant = ant->prox;
            
        Celula *temp = ant->prox;  
        Pokemon elemento = temp->elemento;

        ant->prox = temp->prox;
        free(temp);       

        if(pos == l->size-1)
            l->ultimo = ant;

        l->size--;

        return elemento;
    }
}

Pokemon remove_begin(Lista_Pokemon *l){
    return remove_at(l, 0);
}

Pokemon remove_end(Lista_Pokemon *l){
    return remove_at(l, l->size-1);
}

void print_lista(Lista_Pokemon *l){
    Celula *i;
    int j;
    for (i = l->primeiro->prox, j = 0; i != NULL; i = i->prox, j++)
    {
        printf("[%d] ", j);
        imprimir(i->elemento);
    }
}

void delete_lista(Lista_Pokemon *l){
    while(l->size > 0)
        remove_at(l,0);
    free(l->primeiro);
}


//===================================================== Fim Lista de Pokemons =============================================

int main()
{
    // FILE *arq = fopen("pokemon.csv", "r");
    FILE * arq = fopen("/tmp/pokemon.csv", "r");

    char linha[TAM_LINHA];
    Pokemon *poke = (Pokemon *)malloc(sizeof(Pokemon) * QTD_POKEMONS);
    Lista_Pokemon pokeSelect = new_lista();

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
            insert_end(&pokeSelect, Buscar_Pokemon_ID(id_input, poke, QTD_POKEMONS));
        }
    }

    int n_operacoes;

    scanf("%d", &n_operacoes);

    for (int i = 0; i < n_operacoes; i++)
    {
        char operacao[3];
        scanf("%2s", operacao);
        if (strcmp(operacao, "II") == 0)
        {
            int id;
            scanf("%d", &id);
            insert_begin(&pokeSelect, Buscar_Pokemon_ID(id, poke, QTD_POKEMONS));
        }
        else if (strcmp(operacao, "IF") == 0)
        {
            int id;
            scanf("%d", &id);
            insert_end(&pokeSelect, Buscar_Pokemon_ID(id, poke, QTD_POKEMONS));
        }
        else if (strcmp(operacao, "I*") == 0)
        {
            int id, pos;
            scanf("%d %d", &pos, &id);
            insert_at(&pokeSelect, Buscar_Pokemon_ID(id, poke, QTD_POKEMONS), pos);
        }
        else if (strcmp(operacao, "RI") == 0)
        {
            Pokemon p = remove_begin(&pokeSelect);
            printf("(R) %s\n", p.name);
        }
        else if (strcmp(operacao, "RF") == 0)
        {
            Pokemon p = remove_end(&pokeSelect);
            printf("(R) %s\n", p.name);
        }
        else if (strcmp(operacao, "R*") == 0)
        {
            int pos;
            scanf("%d", &pos);
            Pokemon p = remove_at(&pokeSelect, pos);
            printf("(R) %s\n", p.name);
        }
    }

    print_lista(&pokeSelect);

    delete_lista(&pokeSelect);
    fclose(arq);
}