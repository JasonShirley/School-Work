#include "Enemy.h"
#include <cstdlib>
#include <iostream>
using namespace std;
Enemy::Enemy(int health, int eStrength, int eConstitution, int startX, int startY):
        currentHealth(health), strength(eStrength), constitution(eConstitution),
        xLoc(startX), yLoc(startY), alive(true)
{
//    enemyId = rand % 100;
}
bool Enemy::isAlive() const{
		return alive;
}
