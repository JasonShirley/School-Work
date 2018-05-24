#include "GameBoard.h"
#include <vector>
#include "Goblin.h"

GameBoard::GameBoard(int xCor, int yCor){
	xCoordinate = xCor;
	yCoordinate = yCor;
}

void GameBoard::addGamePiece(Enemy *enemy){
	enemyVect.push_back(enemy);
}

void GameBoard::resetBoard() const {

}

void GameBoard::printBoard() const{
}
