#include "Enemy.h"
#include <cstdlib>
#include <iostream>

Enemy::Enemy(int health, const int eStrength, const int eConstitution, const int startX, const int startY):
        currentHealth(health), strength(eStrength), constitution(eConstitution),
        startXLocation(startX), startYLocation(startY), alive(true)
{
//    enemyId = rand % 100;
}
bool Enemy::isAlive() const{
		return alive;
}
