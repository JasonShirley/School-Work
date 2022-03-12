//compile with gcc -std=c99
#include <stdio.h>
#include <stdlib.h>

int main(){

	int nrows = 10;
	int ncolumns = 10;
	int **array;
    int *arraPtr;
    
	array = malloc(nrows * sizeof(int *));

	if(array == NULL) {
		fprintf(stderr, "out of memory\n");
		exit(1);
    }
	for(int i = 0; i < nrows; i++) {
		array[i] = malloc(ncolumns * sizeof(int));
		if(array[i] == NULL) {
			fprintf(stderr, "out of memory\n");
			exit(1);
		}
	}

	//part 1: setup, make sure the above compiles


	//part 2: load the 2d array using array notation
    for (int i = 0; i < nrows; i++){
        for (int j = 0; j < ncolumns; j++){
            array[i][j] = i;
        }
    }
		
	//part 3: print the 2d array using array notation
    for (int i = 0; i < nrows; i++){
        printf("\n");
        for (int j = 0; j < ncolumns; j++){
            printf("%d ",array[i][j]);
        }
    }
    printf("\n");
	//part 4: print the 2d array using pointers
    for (int i = 0; i < nrows; i++){
        printf("\n");
        for (int j = 0; j < ncolumns; j++){
            int *ptr = &array[i][j];
            printf("%d ", *(ptr));
        }
    }

	//part 5: load the array using pointers
    for (int i = 0; i < nrows * ncolumns; i++){
        for (int j = 0; j < ncolumns * nrows; j++){
            
        }
    }

	//part 6: free the memory for the array

}

