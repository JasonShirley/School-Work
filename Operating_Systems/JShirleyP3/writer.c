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
	void handle_sigint(int sig);
	char charArray[100];							//Used to identify shmloc
	int charArrToInt;
	struct sigaction sa;
	
	sa.sa_handler = handle_sigint;
	sa.sa_flags = SA_RESTART;
	sigemptyset(&sa.sa_mask);

    if (sigaction(SIGINT, &sa, NULL) == -1) {
        perror("sigaction");
        exit(1);
    }
    else if (sigaction(SIGUSR1, &sa, NULL) == -1){
		perror("sigaction");
		exit(1);
	}
    	
	printf("%d shmwriter: enter a shemloc: ", getpid());
	fgets(charArray, 100, stdin);
	charArrToInt = atoi(charArray);
	
	key_t key = ftok("shmfile", charArrToInt);				//Generate a System V IPC Key for memshare

	if(key == -1){
		perror("ftokl");
		exit(1);
	}
	
	shmid = shmget(key, sizeof(char)*100, 0644 | IPC_CREAT);
	
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
