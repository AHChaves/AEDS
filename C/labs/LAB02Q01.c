#include <stdio.h>
#include <string.h>

void combinador(char str1[], char str2[], char result[]) {
    int len1 = strlen(str1);
    int len2 = strlen(str2);
    int max_len = len1 > len2 ? len1 : len2;
    int index = 0;

    for (int i = 0; i < max_len; i++) {
        if (i < len1) {
            result[index++] = str1[i];
        }
        if (i < len2) {
            result[index++] = str2[i];
        }
    }

    result[index] = '\0';
}

int main() {
    int n = 3;
    
    for (int i = 0; i < n; i++) {
        char str1[100];
        char str2[100];
        char result[200];

        scanf("%s", str1);

        scanf("%s", str2);

        combinador(str1, str2, result);
        printf("%s\n", result);
    }

    return 0;
}
