#include "Goblin.h"
#include <iostream>
using namespace std;
	Goblin::Goblin(int health, int const cStrength, int const eConstitution, int startX, int startY):
	Enemy(health, cStrength, eConstitution, startX, startY)
{
    cout << currentHealth << " HEALTH" << endl;
    enemyID = GOB_ID;
    print();
}
void Goblin::update() {
    if (currentHealth > 0){									//CHANGE STATEMENT TO if (alive)
        yLoc = yLoc + rand() % -3 + 3;
        xLoc = xLoc + rand() % -2 + 2;
    }

}
void Goblin::attack(){
    if (currentHealth > 0){									//CHANGE STATEMENT TO if (alive)
        int dice = rand() % 4 + 1;
        int attack = strength + dice;
    }
}
void Goblin::injure(int attDamage){

    int damage = attDamage - (.5 * constitution);
    if (damage < 0) { damage = 0; }
    currentHealth = currentHealth - damage;
    if (currentHealth < 0){
        currentHealth = 0;
        alive = false;
        cout << "Goblin " << enemyID << " has been slain!" << endl;
    }
}
void Goblin::print() const{
    cout << "Goblin " << enemyID << " @" << " (" << xLoc << ", " << yLoc << ") hp = " << currentHealth << endl;
}
