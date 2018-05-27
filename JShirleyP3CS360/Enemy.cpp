/*
 * The enemy class file constructs a new enemy. It also sets a randomizer seed. The last bit it does
 * is set the isAlive() method.
 */
#include "Enemy.h"
#include <ctime>
#include <cstdlib>
using namespace std;
int Enemy::idGenerator = 1;

Enemy::Enemy(int health, int eStrength, int eConstitution, int startX, int startY):
        health(health), str(eStrength), con(eConstitution),
        xPosition(startX), yPosition(startY), alive(true)
{
    srand(time(NULL));          // seed RNG for other classes
}

	
// Return whether or not an enemy is alive. True = yes False = no.
bool Enemy::isAlive() const{
    return alive;
}

int Enemy::getId() const{
	return id;
}
int Enemy::getHealth() const{
	return health;
}
int Enemy::getStr() const{
	return str;
}
int Enemy::getCon() const{
	return con;
}
int Enemy::getX() const{
	return xPosition;
}
int Enemy::getY() const{
	return yPosition;
}
