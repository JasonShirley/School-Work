#include "Goblin.h"
#include <iostream>
using namespace std;
	Goblin::Goblin(int health, int const cStrength, int const eConstitution, int startX, int startY):
	Enemy(health, cStrength, eConstitution, startX, startY){
    cout << currentHealth << " HEALTH" << endl;
    enemyID = GOB_ID;
    print();
}
void Goblin::update() {
    if (currentHealth > 0){									//CHANGE STATEMENT TO if (alive)
        yLoc = yLoc + rand() % 6 - 3;
        xLoc = xLoc + rand() % 4 - 2;
    }
}
void Goblin::attack(){
    int attackDam;
    if (currentHealth > 0){									//CHANGE STATEMENT TO if (alive)
        int dice = rand() % 4 + 1;
        attackDam = strength + dice;
    }
    cout << "Goblin " << enemyID << " attacks a random passerby for " << attackDam << " damage!" << endl;
}
void Goblin::injure(int attDamage){
    int damage = attDamage - (.5 * constitution);
    if (damage < 0) { damage = 0; }
        currentHealth = currentHealth - damage;
    if (currentHealth <= 0){
        alive = false;
        cout << "Goblin " << enemyID << " takes " << damage << " damage! Current hp = " << currentHealth << endl;
        cout << "Goblin " << enemyID << " has been slain!" << endl;
    }
    else if (alive){
        cout << "Goblin " << enemyID << " takes " << damage << " damage! Current hp = " << currentHealth << endl;
    }
}
void Goblin::print() const{
    cout << "Goblin " << enemyID << " @" << " (" << xLoc << ", " << yLoc << ") hp = " << currentHealth << endl;
}
