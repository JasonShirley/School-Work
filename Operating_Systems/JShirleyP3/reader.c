#include <stdio.h>
#include <signal.h>
#include <sys/msg.h>
#include <sys/shm.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>

char* data;
int shmid;

void handle_sigint(int sig){
	printf("\nI have caught the signal %d\n", sig);
	exit(0);
}
/*
 * The purpose of this application is to read from a shared memory segment.
 * This application will connect to a shared memory segment, and read the
 * contents from it. The user will define the shared memory segment to connect to.
 * The application will prompt the user to input the shared memory segment, 
 * and the application will connect to the shared memory segment according to
 * the input from the user. 
*/


int main() {
	signal(SIGINT, handle_sigint);
	char charArray[100];									// Used to identify shmloc
	int charArrToInt;										// Used to convert the charArray to int, for ftok

	printf("%d", getpid());
	printf(" shmwriter: enter a shemloc: ");
	fgets(charArray, 100, stdin);
	printf("\n");
	charArrToInt = atoi(charArray);							// ftok requires an int for id, convert the array to int for ftok use
	
	key_t key = ftok("shmfile", charArrToInt);				//Generate a System V IPC Key for memshare

	if(key == -1){
		perror("ftokl");
		exit(1);
	}
	printf("set up memory seg, id ");
	shmid = shmget(key, sizeof(charArray), 0644 | IPC_CREAT);	// define the shmem segment and connect to it
	printf("%i ", shmid);
	printf("/n");
	
	signal(SIGINT, handle_sigint);
	while(1){
		data = (char*)shmat(shmid, (void*)0, 0);					// attach char* to the memory segment
		printf("%d ", getpid());
		printf("shmreader: shared memory currently contains: %s\n", data);
		sleep(2);
	}

	shmctl(shmid,IPC_RMID,NULL);
}
