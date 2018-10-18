#include <stdio.h>
#include <sys/msg.h>
#include <sys/shm.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>

int main() {
		printf("%i ", getpid());
		printf("shmwriter: enter a shm loc: ");

		key_t key = ftok("shmfile", 'r');				//Generate a System V IPC Key for memshare
		char *charArray[100];			//Used to identify shmloc
		int shmid;
		char *data;

		if(key == -1){
			perror("ftokl");
			exit(1);
		}
	while(1){
		shmid = shmget(key, sizeof(char)*100, 0644 | IPC_CREAT);
		data = (char*)shmat(shmid, (void*)0, 0);
		printf("%i ", getpid());
		printf("Written in memory: %s\n", data);
		shmdt(data);
		sleep(2);
	}

	shmctl(shmid,IPC_RMID,NULL);
}
