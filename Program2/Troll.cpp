#include "Troll.h"
#include <iostream>
using namespace std;

	Troll::Troll(int health, int const cStrength, int const eConstitution, int startX, int startY):
	Enemy(health, cStrength, eConstitution, startX, startY)
{
    enemyID = TROLL_ID;
    startingHealth = currentHealth;
    print();
}
void Troll::update() {
    if (alive){
        int posMinus = (rand() % 3 + (-10));
        int posPlus = (rand() % 3 + 7);
        if (-(posMinus) > posPlus) { xLoc = xLoc + (rand() % 3 + (-10));}
        else {xLoc = xLoc + (rand() % 3 + 7);}       
    }
    currentHealth = currentHealth + constitution;
    if (currentHealth > startingHealth) {currentHealth = startingHealth;}
    cout << "Troll " << enemyID << " regenerates " << constitution << " health" << endl;
}
void Troll::attack(){
    int attackDam;
    if (currentHealth > 0){
        int dice = rand() % 8 + 1;
        attackDam = strength + dice;
    }
    cout << "Troll " << enemyID << " attacks a random passerby for " << attackDam << " damage!" << endl;
}
void Troll::injure(int attDamage){
    int damage = attDamage - (1.5 * constitution);
							//CHANGE STATEMENT TO if (alive)    
    if (damage < 0) { damage = 0; }
    currentHealth = currentHealth - damage;
    if (currentHealth <= 0){
        alive = false;
        cout << "Troll " << enemyID << " takes " << damage << " damage! Current hp = " << currentHealth << endl;
        cout << "Troll " << enemyID << " has been slain!" << endl;
    }
    else if (alive){
        cout << "The passer by tries to attack Troll " << enemyID << ", but it's not very effective..."<< endl;
        cout << "Troll " << enemyID << " takes " << damage << " damage! Current hp = " << currentHealth << endl;
    }
}
void Troll::print() const{
    cout << "Troll " << enemyID << " @" << " (" << xLoc << ", " << yLoc << ") hp = " << currentHealth << endl;
}
