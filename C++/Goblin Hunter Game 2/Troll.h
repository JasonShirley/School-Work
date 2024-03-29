/*
 * This header file defines what methods the Troll class will use. It inherits methods from
 * Enemy to drive the code.
 */
#ifndef TROLL_H
#define TROLL_H
#include "Enemy.h"
class Troll : public Enemy{
public:
	Troll(int health, const int cStrength, const int eConstitution, int startX, int startY);
	void attack();
	void injure(int attDamage);
	void print() const;
	void update();
private:
   const int startingHealth;
};
#endif
