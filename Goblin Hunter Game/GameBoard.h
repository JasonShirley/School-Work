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
		int vect = 0;
		int xPos;
		int yPos;
		vector <Enemy*> enemyVect;
		char **gameBoard;
		int setRow = 0;
		int setCol = 0;
	public:
		GameBoard(int xCor, int yCor);
		~GameBoard();
		void checkCartesianBounds(Enemy *enemy);
		void arrayBoundCheck(Enemy *enemy);
		void checkEqualPosition(Enemy *enemy);
		void addGamePiece(Enemy *enemy);
		void resetBoard() const;
		void printBoard() ;
};

#endif
