#include "Orc.h"
#include <iostream>
#include<ctime>

using namespace std;
	Orc::Orc(int health, int const cStrength, int const eConstitution, int startX, int startY):
	Enemy(health, cStrength, eConstitution, startX, startY)
{
    enemyID = ORC_ID;
    print();
}
void Orc::update() {
    if (currentHealth > 0){								//CHANGE STATEMENT TO if (alive)
        int pos = rand() % 10 - 5;
        xLoc = xLoc + pos;
        yLoc = yLoc + pos;
    }

}
void Orc::attack(){									//CHANGE STATEMENT TO if (alive)
    int attackDam;
    if (currentHealth > 0){
        int dice = rand() % 6 + 1;
        int attackDam = strength + dice;
    }
    cout << "Orc " << enemyID << " attacks a random passerby for " << attackDam << " damage!" << endl;
}
void Orc::injure(int attDamage){
    int damage = attDamage - constitution;
    if (currentHealth > 0 && attDamage > constitution){
        int damage = attDamage - constitution;
        if (damage < 0) { damage = 0; }
        currentHealth = currentHealth - damage;
    }
    cout << "The passer by treies to attack Orc " << enemyID << ", but it's not very effective..."<< endl;
    cout << "Orc " << enemyID << " takes " << damage << " damage! Current hp = " << currentHealth << endl;
    if (currentHealth < 0){						//CHANGE STATEMENT TO if (alive)
        currentHealth = 0;
        alive = false;
        cout << "Orc " << enemyID << " has been slain!" << endl;
    }
}
void Orc::print() const{
    cout << "Orc " << enemyID << " @" << " (" << xLoc << ", " << yLoc << ") hp = " << currentHealth << endl;
}
