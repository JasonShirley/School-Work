/*
 * Jason Shirley
 * CS360 Spring 2018
 * Program 3
 * GameBoard.cpp builds a cartesian plane to show the Enemy's position from Program 2.
 * The program handles finding where the enemy is at in cartesian form, converting
 * the cartesian form to an array form, and positioning the enemy inside of an array
 * for representing the cartesian plane. The program will also keep a vector of Enemies
 * to keep track of Enemeies that are in play.
 */

#include "GameBoard.h"
#include <vector>
#include <iostream>
#include <ctime>

using namespace std;

// Constructor, builds the initial array for displaying a cartesian grid.
GameBoard::GameBoard(int xCor, int yCor){
	xCoordinate = (xCor * 2) + 1;
	yCoordinate = (yCor * 2) + 1;
	xCorInit = xCor;
	yCorInit = yCor;
	
	// allocate space for 2d array of pointers    
	gameBoard = (char **)malloc(yCoordinate * sizeof(char *));			
    for (int i=0; i < xCoordinate; i++){
         gameBoard[i] = (char *)malloc(xCoordinate * sizeof(char));
	 }
	 // initialize the gameBoard array
	for (int i = 0; i < yCoordinate; i++){
		for (int j = 0; j < xCoordinate; j++){
			gameBoard[i][yCor] = '|';
			gameBoard[xCor][j] = '_';
			gameBoard[i][j] = '.';
			if (i == yCor){
				gameBoard[i][j] = '_';
			}
		}
	}
}
// Destructor for GameBoard.
GameBoard::~GameBoard(){
	free (gameBoard);
}
/*
 * addGamePiece adds an enemy to the enemyVect vector. It calls checkCartesianBounds
 * to see if the game piece is within the boundries of the cartesian plane
 */
void GameBoard::addGamePiece(Enemy *enemy){
	checkEqualPosition(enemy);
	checkCartesianBounds(enemy);
	cout << "Adding gamepiece " << enemy->getDisplayChar() << " at (" << enemy->getX() << " , " << enemy->getY() << ")" << endl;
	enemyVect.push_back(enemy);
}
// resetBoard resets the values of the game board to an empty board
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
// printBoard() prints the GameBoard with all characters in play. The method
// calls arrayBoundCheck to ensure that when printing, the enemy is within
// the bounds of the GameBoard array.
void GameBoard::printBoard() {
	int count = 0;
	// for each element in the 2d GameBoard array...
	for (int i = 0; i < yCoordinate; i++){
		setRow = i;
		for (int j = 0; j < xCoordinate; j++){
			for (Enemy *e : enemyVect){
				arrayBoundCheck(e);	// check if the enemy is in the bounds of the vector and place in GameBoard
			}
			setCol = j;
			cout << gameBoard[i][j] ;
		}		
	cout << endl;			
	}
	enemyVect.clear(); //clear the vector for the next round, ensure that the vector doesn't grow and consume too much memory!!!
}
/* arrayBoundCheck ensures that an enemy is in the bounds of the array for gameBoard.
 * It converts cartesian plots into array positions. From these positions we can 
 * ensure that the array quardrent is within the boundaries of the array and the 
 * Cartesian plane simultaneously. If the Cartesian plot falls out of bounds of the array,
 * the method resets the position to a position that would be on the edge of the Cartesian
 * plane.
 */
void GameBoard::arrayBoundCheck(Enemy *e){
	if (e->getX() > 0 && e->getY() > 0){	// The enemy is in Q1
		setRow = yCorInit - e->getY();
		setCol = xCorInit + e->getX();
		if (setCol > xCoordinate){	       // The enemy has stepped out of x bounds					
			setCol = xCoordinate - 1;
		}
		if (setRow < 0){				// The enemy has stepped out of y bounds
			setRow = 0;
		}
		gameBoard[setRow][setCol] = e->getDisplayChar();	
	}
	else if (e->getX() < 0 && e->getY() > 0){	// The enemy is in Q2
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
	else if (e->getX() < 0 && e->getY() < 0){	// The enemy is in Q3 
		setRow = yCorInit - e->getY();
		setCol = xCorInit + e->getX();
		if (setCol < 0){
			setCol = 0;
		}
		if (setRow >= yCoordinate){
			setRow = yCoordinate - 1;
		}
	}
	else if (e->getX() > 0 && e->getY() < 0){	// The enemy is in Q4 
		setRow = xCorInit - (e->getY());
		setCol = yCorInit + (e->getX());
		if (setCol > xCoordinate){		// The enemy has stepped out of x bounds
			setCol = xCoordinate -1;
		}
		if (setRow > yCoordinate){		// The enemy has stepped out of y bounds
			setRow = -yCoordinate -1;
		}
		gameBoard[setRow][setCol] = e->getDisplayChar();
	}
	gameBoard[setRow][setCol] = e->getDisplayChar();
}
// checkCartesianBounds check if the enemy is within the cartesian bounds. If it isn't, set the enemy's x and y
// position to a plot that is within the bounds. Mostly for displaying.
void GameBoard::checkCartesianBounds(Enemy *enemy){
	if (enemy->getX() > xCorInit){										// outside of positive x axis
		enemy->setX(xCorInit);
		cout << "Re-Positioning gamepiece " << enemy->getDisplayChar() << enemy->getId();
		cout << ", gamepiece out of bounds, adding piece at (" << enemy->getX() << " , " << enemy->getY() << ")" << endl;
		checkCartesianBounds(enemy);
	}
	else if (enemy->getX() < -xCorInit){								// outside of negative x axis
		enemy->setX(-1*xCorInit);
		cout << "Re-Positioning gamepiece " << enemy->getDisplayChar() << enemy->getId();
		cout << ", gamepiece out of bounds, adding piece at (" << enemy->getX() << " , " << enemy->getY() << ")" << endl;	
		checkCartesianBounds(enemy);
	}
	if (enemy->getY() > yCorInit){										// outside of positive y axis
		enemy->setY(yCorInit);
		cout << "Re-Positioning gamepiece " << enemy->getDisplayChar() << enemy->getId();
		cout << ", gamepiece out of bounds, adding piece at (" << enemy->getX() << " , " << enemy->getY() << ")" << endl;	
		checkCartesianBounds(enemy);
	}		
	else if (enemy->getY() < -yCorInit){								// outside of negative y axis
		enemy->setY(-1*yCorInit);
		cout << "Re-Positioning gamepiece " << enemy->getDisplayChar() << " " << enemy->getId();
		cout << ", gamepiece out of bounds, adding piece at (" << enemy->getX() << " , " << enemy->getY() << ")" << endl;	
		checkCartesianBounds(enemy);
	}
	checkEqualPosition(enemy);
}
// checkEqualPosition checks if two enemies are in the same location. If there are two enemies in the same position, the program
// moves the enemy to be placed on the board 2 position on either the x OR y axis. 
void GameBoard::checkEqualPosition(Enemy *e){
	int xOrY;
	for(Enemy* j : enemyVect){
		if (e->getX() == j->getX() && e->getY() == j->getY()){
			if(e != j){
				cout << "Enemy " << j->getDisplayChar() << j->getId();
				cout << " & " << e->getDisplayChar() << e->getId() << " are equal";
				xOrY = rand() % 2 + 1;
				int randNum = rand() %4 + (-2);
				if ( xOrY == 1){
					e->setX(j->getX() + randNum);
					cout << " Moving " << e->getDisplayChar() << e->getId();
					cout << " to (" << e->getX() << " , " << e->getY() << ")" << endl;
					checkEqualPosition(e);
					checkCartesianBounds(e);
				}
				else if (xOrY == 2){
					e->setY(j->getY() + randNum);
					cout << " Moving " << e->getDisplayChar() << e->getId();
					cout << " to (" << e->getX() << " , " << e->getY() << ")" << endl;
					checkEqualPosition(e);
					checkCartesianBounds(e);
				}
			}
		}
	}
}
