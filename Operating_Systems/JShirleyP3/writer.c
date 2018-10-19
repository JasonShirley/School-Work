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

void handle_sigint(int sig){
	printf("\nI have caught the signal %d\n", sig);
	shmdt(data);
	shmctl(shmid, IPC_RMID, NULL);
	exit(0);
}

int main() {
	char charArray[100];							//Used to identify shmloc
	int charArrToInt;
	
	printf("%d", getpid());
	printf(" shmwriter: enter a shemloc: ");
	fgets(charArray, 100, stdin);
	charArrToInt = atoi(charArray);
	
	key_t key = ftok("shmfile", charArrToInt);				//Generate a System V IPC Key for memshare

	if(key == -1){
		perror("ftokl");
		exit(1);
	}
	shmid = shmget(key, sizeof(char)*100, 0644 | IPC_CREAT);
	printf("%d I am right here" , shmid);
	signal(SIGINT, handle_sigint);
	
	while(1){
		data = (char*)shmat(shmid, (void*)0, 0);
		printf("%d ", getpid());
		printf("shmwriter: enter some data: ");
		fgets(data, 100, stdin);
		strtok(data, "\n");
	}
}
