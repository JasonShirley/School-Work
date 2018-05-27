#include "GameBoard.h"
#include <vector>
#include <iostream>
#include "Goblin.h"
using namespace std;
GameBoard::GameBoard(int xCor, int yCor){
	xCoordinate = (xCor * 2) + 1;
	yCoordinate = (yCor * 2) + 1;
	xCorInit = xCor;
	yCorInit = yCor;
	
	gameBoard = (char **)malloc(yCoordinate * sizeof(char *));			// allocate space for 2d gameboard    
    for (int i=0; i < xCoordinate; i++){
         gameBoard[i] = (char *)malloc(xCoordinate * sizeof(char));
	 }
	 
	for (int i = 0; i < yCoordinate; i++){
		for (int j = 0; j < xCoordinate; j++){
			gameBoard[i][xCor] = '|';
			gameBoard[xCor][j] = '_';
			gameBoard[i][j] = '.';
			if (i == yCor){
				gameBoard[i][j] = '_';
			}
		}
	}
}

GameBoard::~GameBoard(){
	free (gameBoard);
	for (Enemy *e : enemyVect){
		delete e;
	}
}

void GameBoard::addGamePiece(Enemy *enemy){
	enemyVect.push_back(enemy);
}

void GameBoard::resetBoard() const {
	for (int i = 0; i < yCoordinate; i++){
		for (int j = 0; j < xCoordinate; j++){
			gameBoard[i][xCorInit] = '|';
			gameBoard[xCorInit][j] = '_';
			gameBoard[i][j] = '.';
			if (i == yCorInit){
				gameBoard[i][j] = '_';
			}
		}
	}
}

void GameBoard::printBoard() const{
	int setRow;
	int setCol;

	for (int i = 0; i < yCoordinate; i++){
		for (int j = 0; j < xCoordinate; j++){
			for (Enemy* e : enemyVect){	
				if (e->getX() >= 0 && e->getY() >= 0){	// The enemy is in Q1
					setRow = yCorInit - e->getY();
					setCol = xCorInit + e->getX();
					if (setCol >= xCoordinate){	       // The enemy has stepped out of x bounds					
						setCol = xCoordinate - 1;
					}
					if (setRow < 0){				// The enemy has stepped out of y bounds
						setRow = 0;
					}
					gameBoard[setRow][setCol] = e->getDisplayChar();
				}
				else if (e->getX() > 0 && e->getY() < 0){	// The enemy is in Q4 
					setRow = xCorInit - (e->getY());
					setCol = yCorInit + (e->getX());
					if (setCol >= xCoordinate){		// The enemy has stepped out of x bounds
						setCol = xCoordinate -1;
					}
					if (setRow >= yCoordinate){		// The enemy has stepped out of y bounds
						setRow = yCoordinate -1;
					}
					gameBoard[setRow][setCol] = e->getDisplayChar();
				}
				else if (e->getX() < 0 && e->getY() < 0){	// The enemy is in Q3 
					setRow = yCorInit - e->getY();
					setCol = xCorInit + e->getX();
					if (setCol <= 0){
						setCol = 0;
					}
					if (setRow >= yCoordinate){
						setRow = yCoordinate - 1;
					}

					gameBoard[setRow][setCol] = e->getDisplayChar();

				}
				else if (e->getX() < 0 && e->getY() >= 0){	// The enemy is in Q2
					setCol = xCorInit + e->getX();
					setRow = yCorInit - e->getY();
					if (setCol < 0){
						setCol = 0;
					}
					if (setRow < 0){
						setRow = 0;
					}
					gameBoard[setRow][setCol] = e->getDisplayChar();
				}
			}
			cout << gameBoard[i][j] ;				
		}

		cout << endl;
	}
}

