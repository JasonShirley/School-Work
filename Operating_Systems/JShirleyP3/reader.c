/*
 * The purpose of this application is to read from a shared memory segment.
 * This application will connect to a shared memory segment, and read the
 * contents from it. The user will define the shared memory segment to connect to.
 * The application will prompt the user to input the shared memory segment, 
 * and the application will connect to the shared memory segment according to
 * the input from the user. 
*/



#include <stdio.h>
#include <signal.h>
#include <sys/msg.h>
#include <sys/shm.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>

char* data;												// Used to store user input
int shmid;												// used for identifying shared memory id

/*
 * handle_signal is used for the signal handling. The signals that will be caught
 * are SIGINT, for signal interrupt and SIGUSR1 for custom user signal. SIGINT
 * will detach from the memory segment and close the program. SIGUSR1 will 
 * simply stat that the user signal was caught and continue on with the program. 
*/
void handle_signal(int sig){
	if(sig == SIGINT){
		printf("\nshmreader: SIGINT signal caught. exiting \n");
		printf("goodbye! thanks for sharing \n");
		shmdt(data);
		exit(0);
	}
	else if(sig == SIGUSR1){
		printf("\n caught sigusr1 \n");
	}
}


int main() {
	void handle_signal(int sig);
	char charArray[100];									// Used to identify shmloc
	int charArrToInt;										// Used to convert the charArray to int, for ftok
	int i;
	for (i = 0; i < 100; i++){								// Fill the char array with 0's, easier to convert key id for later
		charArray[i] = 0;
	}
	
	struct sigaction sa;									// declare sigaction
	sa.sa_handler = handle_signal;							// tell which function sigaction calls
	sa.sa_flags = 0;
	sigemptyset(&sa.sa_mask);

    if (sigaction(SIGINT, &sa, NULL) == -1) {				// SIGINT is called
        perror("sigaction");
        exit(1);
    }
    else if (sigaction(SIGUSR1, &sa, NULL) == -1){			// SIGUSR1 is called
		perror("sigaction");
		exit(1);
	}

	printf("%d", getpid());
	printf(" shmwriter: enter a shemloc: ");
	fgets(charArray, 100, stdin);
	printf("\n");
	// This loop is used to convert the charArray to an int. The int produced will be used
	// to generate ftok key id for memory sharing.	
	for(i = 0; i < 100; i++){
		charArrToInt = (int)charArray[i] + charArrToInt;
		printf("%d \n", charArrToInt);
	}
	
	printf("%d\n", charArrToInt);
	
	key_t key = ftok("shmfile", charArrToInt);				//Generate a System V IPC Key for memshare

	if(key == -1){
		perror("ftokl");
		exit(1);
	}
	printf("%d set up memory seg, id ", getpid());
	shmid = shmget(key, sizeof(charArray), 0644 | IPC_CREAT);	// define the shmem segment and connect to it
	printf("%i ", shmid);
	printf("\n");
	
	data = (char*)shmat(shmid, (void*)0, 0);					// attach char* to the memory segment
	
	if(data == (char *)(-1)){
		perror("shmat");
		exit(1);
	}	
	
	while(1){
		printf("%d shmreader: shared memory currently contains: %s\n", getpid(), data);
		if(atoi(data) == getpid()){
			printf("%d was told to exit. goodbye!\n", getpid());
			shmdt(data);
			exit(1);
		}
		sleep(10);
	}
}
