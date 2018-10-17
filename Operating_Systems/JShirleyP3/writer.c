#include <stdio.h>
#include <sys/msg.h>
#include <sys/shm.h>
#include <stdlib.h>

int main() {
	key_t key = ftok("shmfile", 'r');				//Generate a System V IPC Key for memshare
	char charArray[100];			//Used to identify shmloc	
	int shmid;				
	char *data;	

	if(key == -1){
		perror("ftokl");
		exit(1);
	}
	shmid = shmget(key, 1024, 0644 | IPC_CREAT);
	data = (char*)shmat(shmid, (void*)0, 0);
	printf("write data: ");
	fgets(data, 100, stdin);
	printf("Data written in memory: %s\n", data);

	shmdt(data);

}
