#ifndef GOBLIN_H
#define GOBLIN_H
#include "Enemy.h"
class Goblin : public Enemy{
public:
	Goblin(int health, const int cStrength, const int eConstitution, int startX, int startY);
	void attack();
	void injure(int attDamage);
	void print() const;
	void update();
};
#endif
