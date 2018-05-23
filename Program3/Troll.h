#ifndef TROLL_H
#define TROLL_H

#include "Enemy.h"

class Troll: public Enemy {
	private:
		int maxHealth;

	public:
		Troll(int, int, int, int, int);
		void update();
		void print() const;
		void attack() const;
		void injure(int);
		const char getDisplayChar() const;
};//end of class

#endif

