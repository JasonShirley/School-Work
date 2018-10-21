/*
 * The purpose of this application is to write from a shared memory segment.
 * This application will connect to a shared memory segment, and write to the
 * contents of it. The user will define the shared memory segment to connect to.
 * The application will prompt the user to input the shared memory segment, 
 * and the application will connect to the shared memory segment according to
 * the input from the user. The program will then prompt the user for input.
 * The input will store in the shared memory segment. The program will close 
 * upon ctl c input. The program will use SIGIT signal to catch ctl c. Upon
 * call of SIGINT the memory will be detached and deleted.
*/

#include <stdio.h>
#include <signal.h>
#include <sys/msg.h>
#include <sys/shm.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <unistd.h>

char* data;
int shmid;


/* handle_signal will handle signals called by the operating system.
 * It will handle SIGINT and SIGUSR1. If SIGINT is called, the program
 * will detach and clean up the shared memory segment as well as close the program. 
 * If SIGUSR1 is called the program will state that SIGUSR1 was caught
*/

void handle_signal(int sig){
	if (sig == SIGINT){
		printf("\nshmwriter: SIGINT signal caught.\n");
		printf("goodbye! thanks for sharing.\n");
		printf("cleaning up segid %d", shmid);
		printf("\n");
		shmdt(data);
		shmctl(shmid, IPC_RMID, NULL);
		exit(0);
	}
	else if (sig == SIGUSR1){
		printf("\n");
		printf("%d shmwriter: SIGUSR1 signal caught", getpid());
		fflush(stdout);	
	}
}

int main() {
	void handle_signal(int sig);					// define handle_signal function
	char charArray[100];							//Used to identify shmloc
	int charArrToInt = 0;
	int i;
	for (i = 0; i < 100; i++){						// Fill array with 0's easier to convert key id later
		charArray[i] = 0;
	}
	struct sigaction sa;							// declare sigaction
	
	sa.sa_handler = handle_signal;					// tell which function sigaction to use
	sa.sa_flags = 0;
	sigemptyset(&sa.sa_mask);

    if (sigaction(SIGINT, &sa, NULL) == -1) {		// SIGINT was triggered
        perror("sigaction");
        exit(1);
    }
    else if (sigaction(SIGUSR1, &sa, NULL) == -1){	// SIGUSR1 was triggered
		perror("sigaction");
		exit(1);
	}
    	
	printf("%d shmwriter: enter a shemloc: ", getpid());
	fgets(charArray, 100, stdin);
	charArrToInt = atoi(charArray);
	// This loop is used to convert the charArray to an int. The int produced will be used
	// to generate ftok key id for memory sharing.
	for(i = 0; i < 100; i++){
		charArrToInt = (int)charArray[i] + charArrToInt;
	}
	
	key_t key = ftok("shmfile", charArrToInt);				//Generate a System V IPC Key for memshare

	if(key == -1){
		perror("ftokl");
		exit(1);
	}
	
	shmid = shmget(key, sizeof(char)*100, 0644 | IPC_CREAT);	// generate shared memory id
	
	if(shmid == -1){
		perror("shmget");
		exit(1);
	}
	
	printf("set up memory seg, id %d" , shmid);
	printf("\n");
	
	data = shmat(shmid, (void*)0, 0);
	if(data == (char *)(-1)){
		perror("shmat");
		exit(1);
	}	
	
	while(1){
		printf("%d ", getpid());
		printf("shmwriter: enter some data: ");
		fgets(data, 100, stdin);
		strtok(data, "\n");
	}
}
