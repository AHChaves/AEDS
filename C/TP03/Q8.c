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

 typedef struct CelulaDupla{
    Pokemon elemento;
    struct CelulaDupla *prox, *ant;
}CelulaDupla;

CelulaDupla* new_celula_dupla(Pokemon elemento){
    CelulaDupla *temp = (CelulaDupla*)malloc(sizeof(CelulaDupla));
    temp->elemento = elemento;
    temp->ant = NULL;
    temp->prox = NULL;
    return temp;
}

typedef struct ListaDupla{
    struct CelulaDupla *primeiro, *ultimo;
    int size;
} ListaDupla;

ListaDupla new_lista_dupla(){
    ListaDupla temp;
    temp.primeiro = temp.ultimo = new_celula_dupla(ConstrutorVazio());
    temp.size = 0;
    return temp;
}

int size_lista_dupla(ListaDupla *l){
    return l->size;
}

void insert_begin_dupla(ListaDupla *l, Pokemon elemento){
    
    CelulaDupla *temp = new_celula_dupla(ConstrutorVazio());
    temp->prox = l->primeiro;

    l->primeiro->elemento = elemento; 
    l->primeiro->ant = temp;   
    l->primeiro = temp;
    l->size++;
}

void insert_end_dupla(ListaDupla *l, Pokemon elemento){    
    l->ultimo->prox = new_celula_dupla(elemento);
    l->ultimo->prox->ant = l->ultimo;
    l->ultimo = l->ultimo->prox;
    l->size++;
}

void insert_at_dupla(ListaDupla *l, Pokemon elemento, int pos){
    
    if(pos < 0 || pos > l->size)
        printf("Erro ao tentar inserir na posicao (%d/ tamanho = %d) invalida!", pos, l->size);
    else if (pos == 0)
        insert_begin_dupla(l, elemento);
    else if (pos == l->size)
        insert_end_dupla(l, elemento);
    else{
        
        CelulaDupla *ant = l->primeiro;
        for(int i=0; i<pos;i++) 
            ant = ant->prox;
            
        CelulaDupla *temp = new_celula_dupla(elemento);  
        temp->prox = ant->prox;
        temp->prox->ant = temp;
        temp->ant = ant;
        ant->prox = temp;
        l->size++;
    }
}

Pokemon remove_end_dupla(ListaDupla *l){

    if(l->primeiro == l->ultimo){
        printf("\nA lista esta vazia!\n");
        return ConstrutorVazio();
    }
    
    CelulaDupla *temp = l->ultimo;
    Pokemon elemento = temp->elemento;

    l->ultimo = l->ultimo->ant;
    l->ultimo->prox = NULL;
    l->size--;

    free(temp);
    
    return elemento;
}

Pokemon remove_at_dupla(ListaDupla *l, int pos){

    if(l->primeiro == l->ultimo){
        printf("\nA lista esta vazia!\n");
        return ConstrutorVazio();
    }else if(pos < 0 || pos > l->size-1)
        printf("Erro ao tentar remover item da posicao (%d/ tamanho = %d) invalida!", pos, l->size);
    else if(pos == l->size-1)
        remove_end_dupla(l);
    else{
        
        CelulaDupla *ant = l->primeiro;
        for(int i=0; i<pos;i++) 
            ant = ant->prox;
            
        CelulaDupla *temp = ant->prox;  
        Pokemon elemento = temp->elemento;

        temp->prox->ant = ant;
        ant->prox = temp->prox;
        free(temp);       

        l->size--;

        return elemento;
    }
}

Pokemon remove_begin_dupla(ListaDupla *l){
    return remove_at_dupla(l, 0);
}

bool pesquisar_lista_dupla(ListaDupla *l, Pokemon elemento){
    CelulaDupla *i;
    for (i = l->primeiro->prox; i != NULL; i = i->prox)
        if(i->elemento.id == elemento.id)
            return true;
    return false;
}

void print_lista_dupla(ListaDupla *l){
    CelulaDupla *i;
    for (i = l->primeiro->prox; i != NULL; i = i->prox)
    {
        imprimir(i->elemento);
    }
}

void print_lista_dupla_inverso(ListaDupla *l){
    CelulaDupla *i;
    for (i = l->ultimo; i != l->primeiro; i = i->ant)
    {
        imprimir(i->elemento);
    }
}

void delete_lista_dupla(ListaDupla *l){
    while(l->size > 0)
        remove_at_dupla(l,0);
    free(l->primeiro);
}

//===================================================== Fim Lista de Pokemons =============================================

int numeroComparacoes = 0, numero_movimentacoes = 0;

void swap(Pokemon *a, Pokemon *b) {
    Pokemon temp = *a;
    *a = *b;
    *b = temp;
}

CelulaDupla* partition(CelulaDupla* low, CelulaDupla* high) {
    Pokemon pivot = high->elemento;
    CelulaDupla* i = low->ant;

    for (CelulaDupla* j = low; j != high; j = j->prox) {
        if (j->elemento.generation < pivot.generation || 
           (j->elemento.generation == pivot.generation && strcmp(j->elemento.name, pivot.name) < 0)) {
            i = (i == NULL) ? low : i->prox;
            numeroComparacoes++;
            swap(&(i->elemento), &(j->elemento));
            numero_movimentacoes +=3;
        }
    }
    i = (i == NULL) ? low : i->prox;
    numeroComparacoes++;
    swap(&(i->elemento), &(high->elemento));
    numeroComparacoes += 3;
    return i;
}

// Função recursiva do quicksort para a lista duplamente encadeada
void quicksortRecDupla(CelulaDupla* low, CelulaDupla* high) {
    if (high != NULL && low != high && low != high->prox) {
        CelulaDupla* pivot = partition(low, high);
        quicksortRecDupla(low, pivot->ant);
        quicksortRecDupla(pivot->prox, high);
    }
}

// Função para chamar o quicksort na lista duplamente encadeada
void quicksortDupla(ListaDupla *l) {
    if (l->primeiro != l->ultimo) {
        numeroComparacoes++;
        quicksortRecDupla(l->primeiro->prox, l->ultimo);
    }
}

void logarInformacoes(const int matricula, int comparacoes, int movimentacoes, double tempo)
{
    FILE *arquivo = fopen("matrícula_quicksort2.txt", "w");

    fprintf(arquivo, "%d\t%d\t%d\t%lf", matricula, comparacoes, movimentacoes, tempo);
    fclose(arquivo);
}

int main()
{
    // FILE *arq = fopen("pokemon.csv", "r");
    FILE * arq = fopen("/tmp/pokemon.csv", "r");

    char linha[TAM_LINHA];
    Pokemon *poke = (Pokemon *)malloc(sizeof(Pokemon) * QTD_POKEMONS);
    ListaDupla pokeSelect = new_lista_dupla();

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
            insert_end_dupla(&pokeSelect, Buscar_Pokemon_ID(id_input, poke, QTD_POKEMONS));
        }
    }

    clock_t inicio = clock();
    quicksortDupla(&pokeSelect);
    clock_t fim = clock(); 

    double tempoExecucao = (double)(fim - inicio) / CLOCKS_PER_SEC; 

    logarInformacoes(1528647, numeroComparacoes, numero_movimentacoes, tempoExecucao);

    print_lista_dupla(&pokeSelect);

    delete_lista_dupla(&pokeSelect);
    fclose(arq);
}