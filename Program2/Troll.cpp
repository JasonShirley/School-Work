#include "Troll.h"
#include <iostream>
using namespace std;
	Troll::Troll(int health, int const cStrength, int const eConstitution, int startX, int startY):
	Enemy(health, cStrength, eConstitution, startX, startY)
{
    enemyID = TROLL_ID;
    print();
}
void Troll::update() {
    if (currentHealth > 0){								//CHANGE STATEMENT TO if (alive)
        yLoc = yLoc + rand() % -7 + 10;
    }

}
void Troll::attack(){								//CHANGE STATEMENT TO if (alive)
    if (currentHealth > 0){
        int dice = rand() % 8 + 1;
        int attack = strength + dice;
    }
}
void Troll::injure(int attDamage){
    if (currentHealth > 0){							//CHANGE STATEMENT TO if (alive)    
        int damage = attDamage - (1.5 * constitution);
        if (damage < 0) { damage = 0; }
        currentHealth = currentHealth - damage;
    }
    if (currentHealth < 0){
        currentHealth = 0;
        alive = false;
        cout << "Troll " << enemyID << " has been slain!" << endl;
    }
}
void Troll::print() const{
    cout << "Troll " << enemyID << " @" << " (" << xLoc << ", " << yLoc << ") hp = " << currentHealth << endl;
}
