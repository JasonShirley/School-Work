/*******************************************************************************
 *
 * CS 360 Spring 2017 Assignment #2
 * File: main.cpp
 * Author: Aaron Boudreaux, updated by Steve Sheehy
 * Last Edit: 04/19/2017
 * Description: Driver for Assignment 2
 *
 ******************************************************************************/

#include "Enemy.h"
#include "Goblin.h"
//#include "Troll.h"
//#include "Orc.h"

#include <iostream>
#include <fstream>
#include <ctime>
#include <cstdlib>

using namespace std;

#define GOBLIN 0
#define TROLL 1
#define ORC 2
#define NUM_TURNS 10	//turn this down to 1-2 when testing!

int main() {
	//srand(time(NULL));
	srand(static_cast<int>(time(0)));

	std::fstream inFile("enemyInfo.txt");

	if(inFile.is_open()) {
		int numEnemies, type, hp, str, con, xPos, yPos;
		inFile >> numEnemies;
 
		//Create array of Enemy pointers
		Enemy **monsters = new Enemy*[numEnemies];
		cout << "HERE" << endl;
		for(int i=0; i<numEnemies; i++)	{
			inFile >> type >> hp >> str >> con >> xPos >> yPos;
			cout << "HERE1" << endl;
			if(type == GOBLIN)
				monsters[i] = new Goblin(hp, str, con, xPos, yPos);
				cout << "HERE2" << endl;
			/*else if(type == TROLL)
				monsters[i] = new Troll(hp, str, con, xPos, yPos);
			else if(type == ORC)
				monsters[i] = new Orc(hp, str, con, xPos, yPos);
			*/															// <- DELETE AFTER TESTING
		}//end for
		inFile.close();

		//game loop
		//NOTE: traditionally, a game loop is a while(true) loop. I am limiting it to
		//a specific number of turns for brevity.
		int turn = 0;
		while(turn < NUM_TURNS)	{
			std::cout << "\nTurn #" << turn << std::endl;
			for(int i=0; i<1; i++)	{								// CHANGE BACK TO DEFAULT LATER!!!!!!!!!!!!!!!!!!!!!
				if(monsters[i]->isAlive()){ //skip dead enemies
					cout << "HERE3" << endl;
					//monsters[i]->update();
					//monsters[i]->print();
					monsters[i]->attack();
					//int temp = rand() % 10;
					//cout << "****************************" << temp << endl;
					//monsters[i]->injure(rand() % 10);
				}//end if monster is alive
			}//end for
			turn++;
		}//end while

		//clean up dynamically allocated memory
		for(int i=0; i<numEnemies; i++)
			delete monsters[i];
		delete []monsters;
	}//end if file is open
	else
		std::cout << "ERROR: file enemyInfo.txt not found!\n";
		
	return 0;
}//end of main
