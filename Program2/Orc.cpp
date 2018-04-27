#include "Orc.h"
#include <iostream>
using namespace std;
	Orc::Orc(int health, int const cStrength, int const eConstitution, int startX, int startY):
	Enemy(health, cStrength, eConstitution, startX, startY)
{
    enemyID = ORC_ID;
    print();
}
void Orc::update() {
    if (currentHealth > 0){
        int pos = rand() % -5 + 5;
        xLoc = xLoc + pos;
        yLoc = yLoc + pos;
    }

}
void Orc::attack(){
    if (currentHealth > 0){
        int dice = rand() % 6 + 1;
        int attack = strength + dice;
    }
}
void Orc::injure(int attDamage){
    if (currentHealth > 0 && attDamage > constitution){
        int damage = attDamage - constitution;
        if (damage < 0) { damage = 0; }
        currentHealth = currentHealth - damage;
    }
    if (currentHealth < 0){
        currentHealth = 0;
        alive = false;
        cout << "Orc " << enemyID << " has been slain!" << endl;
    }
}
void Orc::print() const{
    cout << "Orc " << enemyID << " @" << " (" << xLoc << ", " << yLoc << ") hp = " << currentHealth << endl;
}
