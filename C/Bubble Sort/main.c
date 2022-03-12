/*
 * JShirleyP3.c
 * 
 * JShirleyP3 is a bubble sort program that makes use of prototypes, make files, arrays and function calls
 * to create a terminal comand line prompt that accepts arguments in terminal. The function main builds
 * the command line prompt by declaring argc (for argument count) and argv (the array to store the variables).
 * The program can accept up to 32 characters to sort in a bubble sort.
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "bubbleSort.h" // function for bubble sort



int main(int argc, char *argv[]){
    int i;
    char sort = 'F'; // initally F to see if the function fails being passed a legitiment argument.
    int array[34]; // accepts 32 characters, the program name, and the argument passed.
    
    // This if structure checks to see if there are no less the one or no more than 32 characters
    // being passed to the function bubble sort. It ignores the first two values in the argv array
    // which are reserved for the program name and the argument passed. If the enters an invalid number 
    // of arguments then display the usage message.
    if (argc < 3 || argc > 34){
        fprintf(stderr, "usage: %s [D,A] list_of_up_to_32_numbers\n", argv[0]);
        exit(1);
    }
    // If it's array position 1 in argv and char sort = A then pass the letter to sort to be used to ascend sort in bubblesort.
    for (i = 1; i < argc; i++){
        if (strcmp(argv[i], "A") == 0){
            sort = 'A';
        }
    // else if its array position 1 in argv and char sort = D the pass the letter to sort to be used to descend sort in bubblesort.
        else if(strcmp(argv[i], "D") == 0){
            sort = 'D';
        }
        // store the value being passed into the array position i
        if (i > 1){
            array[i] = atoi(argv[i]);
        }
    }
    // if an invalid or no argument is passed, display usage message.
    if (sort == 'F'){
        fprintf(stderr, "usage: %s [D,A] list_of_up_to_32_numbers\n", argv[0]);
        exit(1);
    }
    // Send off the values stored in the array to be sorted. 
    bubbleSort(i, array, sort);
}

