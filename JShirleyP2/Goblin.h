/*
 * This header file defines what methods the goblin class will use. It inherits methods from
 * Enemy to drive the code.
 */
#ifndef GOBLIN_H
#define GOBLIN_H
#include "Enemy.h"
class Goblin : public Enemy{
public:
	Goblin(int health, const int cStrength, const int eConstitution, int startX, int startY);  // Constructor
	void attack();                         // Attack a passerby for a randomly generated amount of damage
	void injure(int attDamage);            // Goblin takes a random amount of damage from a passerby
	void print() const;                    // Prints out the goblin's current state
	void update();                         // Changes a goblin's position for a random amount of x and y value.
};
#endif
