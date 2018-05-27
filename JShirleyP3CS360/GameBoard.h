#ifndef GAMEBOARD_H
#define GAMEBOARD_H
#include <vector>
#include "Enemy.h"
using namespace std;

class GameBoard{
	
	private:
		int xCoordinate;
		int yCoordinate;
		int xCorInit;
		int yCorInit;
		vector <Enemy*> enemyVect;
		char **gameBoard;
	public:
		GameBoard(int xCor, int yCor);
		~GameBoard();
		void addGamePiece(Enemy *enemy);
		void resetBoard() const;
		void printBoard() const;
};

#endif
