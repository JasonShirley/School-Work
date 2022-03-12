/*
 * The enemy class file constructs a new enemy. It also sets a randomizer seed. The last bit it does
 * is set the isAlive() method.
 */
#include "Enemy.h"
#include <ctime>
#include <cstdlib>
using namespace std;
Enemy::Enemy(int health, int eStrength, int eConstitution, int startX, int startY):
        currentHealth(health), strength(eStrength), constitution(eConstitution),
        xLoc(startX), yLoc(startY), alive(true)
{
    srand(time(NULL));          // seed RNG for other classes
}
// Return whether or not an enemy is alive. True = yes False = no.
bool Enemy::isAlive() const{
    return alive;
}
