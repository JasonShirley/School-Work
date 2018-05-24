#include "GameBoard.h"
#include <vector>
#include "Goblin.h"
#include <iostream>
using namespace std;

GameBoard::GameBoard(int xCor, int yCor){
	xCoordinate = xCor;
	yCoordinate = yCor;
}

void GameBoard::addGamePiece(Enemy *enemy){
	enemyVect.push_back(enemy);
	for (Enemy *s : enemyVect){
		s->print();
	}
}

void GameBoard::resetBoard() const {

}

void GameBoard::printBoard() const{
}
