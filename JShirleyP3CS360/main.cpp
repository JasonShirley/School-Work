/*******************************************************************************
 *
 * CS 360 Spring 2016 Assignment #3
 * File: main.cpp
 * Author: Aaron Boudreaux, updated by Steve Sheehy
 * Last Edit: 05/6/2016
 * Description: Driver for Assignment 3
 *
 ******************************************************************************/

#include "Enemy.h"
#include "Goblin.h"
#include "Troll.h"
#include "Orc.h"
#include "GameBoard.h"

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

		for(int i=0; i<numEnemies; i++)	{
			inFile >> type >> hp >> str >> con >> xPos >> yPos;

			if(type == GOBLIN)
				monsters[i] = new Goblin(hp, str, con, xPos, yPos);
			else if(type == TROLL)
				monsters[i] = new Orc(hp, str, con, xPos, yPos);
			else if(type == ORC)
				monsters[i] = new Troll(hp, str, con, xPos, yPos);
		}//end for

		inFile.close();

		//create the GameBoard
		GameBoard *gb = new GameBoard(20,20);

		//game loop
		//NOTE: traditionally, a game loop is a while(true) loop. I am limiting it to
		//a specific number of turns for brevity.
		int turn = 0;
		while(turn < NUM_TURNS)	{
			std::cout << "\nTurn #" << turn << std::endl;
			for(int i=0; i<numEnemies; i++)	{
				if(monsters[i]->isAlive()){ //skip dead enemies
					monsters[i]->update();
					monsters[i]->print();
					monsters[i]->attack();
					monsters[i]->injure(rand() % 10);
				}//end if monster is alive
				gb->addGamePiece(monsters[i]);
			}//end for

			turn++;
			gb->printBoard();
			gb->resetBoard();
		}//end while


		//clean up dynamically allocated memory
		delete gb;
		for(int i=0; i<numEnemies; i++)
			delete monsters[i];
		delete []monsters;
	}//end if file is open
	else
		std::cout << "ERROR: file enemyInfo.txt not found!\n";
		
	return 0;
}//end of main
