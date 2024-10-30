#include <stdio.h>
#include <stdlib.h>

#define TAM_MAX_ARR 100

int Sort(int arr[], int tmp[], int left, int mid, int right) {
    int i = left;    
    int j = mid + 1; 
    int k = left;    
    int inv_count = 0;

    while (i <= mid && j <= right) {
        if (arr[i] <= arr[j]) {
            tmp[k++] = arr[i++];
        } else {
            tmp[k++] = arr[j++];
            inv_count += (mid - i + 1);
        }
    }

    while (i <= mid) 
        tmp[k++] = arr[i++];

    while (j <= right) 
        tmp[k++] = arr[j++];

    for (i = left; i <= right; i++) 
        arr[i] = tmp[i];

    return inv_count;
}

int Merge_Sort(int arr[], int tmp[], int left, int right) {
    int mid, inv_count = 0;
    if (left < right) {
        mid = (left + right) / 2;

        inv_count += Merge_Sort(arr, tmp, left, mid);
        inv_count += Merge_Sort(arr, tmp, mid + 1, right);
        inv_count += Sort(arr, tmp, left, mid, right);
    }
    return inv_count;
}

int Count_Inversoes(int arr[], int n) {
    int* tmp = (int*)malloc(n * sizeof(int));
    int result = Merge_Sort(arr, tmp, 0, n - 1);
    free(tmp);
    return result;
}

int Min_Ult(int grid_largada[], int grid_chegada[], int n) {
    int* arr = (int*)malloc(n * sizeof(int));
    int posicao_inicial[TAM_MAX_ARR];

    for (int i = 0; i < n; i++) 
        posicao_inicial[grid_largada[i]] = i;

    for (int i = 0; i < n; i++) 
        arr[i] = posicao_inicial[grid_chegada[i]];

    int inversoes = Count_Inversoes(arr, n);
    free(arr);
    return inversoes;
}

int main() {
    int entrada;
    scanf("%d", &entrada);
    while (entrada >= 1) {
        int grid_largada[entrada], grid_chegada[entrada];
        
        for (int i = 0; i < entrada; i++) 
            scanf("%d", grid_largada[i]);
        for (int i = 0; i < entrada; i++) 
            scanf("%d", grid_chegada[i]);

        printf("%d\n", Min_Ult(grid_largada, grid_chegada, entrada));
        scanf("%d", &entrada);
    }
    return 0;
}