/*
 * This header file defines what methods the Orc class will use. It inherits methods from
 * Enemy to drive the code.
 */
#ifndef ORC_H
#define ORC_H
#include "Enemy.h"
class Orc : public Enemy{
public:
	Orc(int health, const int cStrength, const int eConstitution, int startX, int startY);
	void attack();
	void injure(int attDamage);
	void print() const;
	void update();
};
#endif
