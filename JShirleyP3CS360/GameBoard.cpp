#include "GameBoard.h"
#include <vector>
#include <iostream>
#include <ctime>
#include <cstdlib>

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
}
void GameBoard::addGamePiece(Enemy *enemy){
	cout << "Adding gamepiece " << enemy->getDisplayChar() << " at (" << enemy->getX() << " , " << enemy->getY() << ")" << endl;
	if (enemy->getX() > xCorInit){
		enemy->setX(xCorInit);
		cout << "Re-Positioning gamepiece " << enemy->getDisplayChar() << " " << enemy->getId();
		cout << ", gamepiece out of bounds, adding piece at (" << enemy->getX() << " , " << enemy->getY() << ")" << endl;
	}
	else if (enemy->getX() < -xCorInit){
		enemy->setX(-1*xCorInit);
		cout << "Re-Positioning gamepiece " << enemy->getDisplayChar();
		cout << ", gamepiece out of bounds, adding piece at (" << enemy->getX() << " , " << enemy->getY() << ")" << endl;	
		}
	if (enemy->getY() > yCorInit){
		enemy->setY(-1*yCorInit);
		cout << "Re-Positioning gamepiece " << enemy->getDisplayChar() << " " << enemy->getId();
		cout << ", gamepiece out of bounds, adding piece at (" << enemy->getX() << " , " << enemy->getY() << ")" << endl;	
		}		
	else if (enemy->getY() < -yCorInit){
		enemy->setY(-1*yCorInit);
		cout << "Re-Positioning gamepiece " << enemy->getDisplayChar() << " " << enemy->getId();
		cout << ", gamepiece out of bounds, adding piece at (" << enemy->getX() << " , " << enemy->getY() << ")" << endl;	
		}
	enemyVect.push_back(enemy);
	checkEqualPosition();
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

void GameBoard::printBoard() {
	int setRow;
	int setCol;
	int count = 0;

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
void GameBoard::checkEqualPosition(){
	int i = 0;
	int xOrY;
	for (i; i < enemyVect.size(); i++){
	for(int j = 0; j < enemyVect.size(); j++){
		if (enemyVect[i]->getX() == enemyVect[j]->getX() && enemyVect[i]->getY() == enemyVect[j]->getY()){
			if(enemyVect[i] != enemyVect[j]){
				cout << "Enemy " << enemyVect[j]->getDisplayChar() << " " << enemyVect[j]->getId();
				cout << " & " << enemyVect[i]->getDisplayChar() << " " << enemyVect[i]->getId() << " are equal";
				xOrY = rand() % 2 + 1;
				cout << xOrY << endl;
				if ( xOrY == 1){
					enemyVect[j]->setX(enemyVect[j]->getX() + rand() % 2 - 2);
					cout << " Moving " << enemyVect[j]->getDisplayChar();
					cout << " to (" << enemyVect[j]->getX() << " , " << enemyVect[j]->getY() << ")" << endl;
				}
				else if (xOrY == 2){
					enemyVect[j]->setY(enemyVect[j]->getY() + rand() % 2 - 2);
					cout << " Moving " << enemyVect[j]->getDisplayChar();
					cout << " to (" << enemyVect[j]->getX() << " , " << enemyVect[j]->getY() << ")" << endl;
				}
			}
		}
	}
}}
