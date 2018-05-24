#ifndef GAMEBOARD_H
#define GAMEBOARD_H
#include <vector>
#include "Enemy.h"
using namespace std;

class GameBoard{
	
	private:
		int xCoordinate;
		int yCoordinate;
		vector <Enemy*> enemyVect;
	public:
		GameBoard(int xCor, int yCor);
		void addGamePiece(Enemy *enemy);
		void resetBoard() const;
		void printBoard() const;
};

#endif