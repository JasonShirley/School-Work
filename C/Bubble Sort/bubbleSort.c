// The function is a bubblesort, I don't believe I need to say much more other than it
// accepts values passed from main and either acceps a char A or char D to ascend or descend
// the sort.

#include <stdio.h>
#include "bubbleSort.h"

void bubbleSort(int n, int list[], char x){
    
    int i, j;
    int temp;
    if (x == 'A'){
        for(i=0; i < n-1; i++){
            for (j = 0; j < n-1-i; j++){
                if(list[j] > list [j+1]){
                    temp = list[j];
                    list[j] = list[j+1];
                    list[j+1] = temp;
                }
            }
        }
        for ( i = 2; i < n; i++){
            printf("%d\n", list[i]);
        }
    }
    if ( x == 'D'){
        for(i=0; i < n-1; i++){
            for (j = 0; j < n-1-i; j++){
                if(list[j] < list [j+1]){
                    temp = list[j];
                    list[j] = list[j+1];
                    list[j+1] = temp;
                }
            }
        }
        for ( i = 0; i < n-2; i++){
            printf("%d\n", list[i]);
        }
    }

}
