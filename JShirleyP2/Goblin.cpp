/*
/* This class file defines a Goblin. It will associate values to variables that methods require to.
 * The class will inherit methods from the Enemy header file for use of a new goblin.
 */
#include "Goblin.h"
#include <iostream>
using namespace std;
Goblin::Goblin(int health, int const cStrength, int const eConstitution, int startX, int startY):  
	Enemy(health, cStrength, eConstitution, startX, startY){   // Construct a new goblin and assign value to Enemy variables
    enemyID = GOB_ID;                                           // Assign Goblin with an id
    print();                                                    // Print the new goblin's attributes.
}
// update Will update a goblin's position. A random number between -3 to 3 will be chosen for the x value
// and a random number between -2 to 2 will be chosen to the y value.
void Goblin::update() {
        xLoc = xLoc + rand() % 6 - 3;
        yLoc = yLoc + rand() % 4 - 2;
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
        if (damage <= 0){
            cout << "The passer by tries to attack Goblin " << enemyID << ", but it's not very effective..."<< endl;
        }
        cout << "Goblin " << enemyID << " takes " << damage << " damage! Current hp = " << currentHealth << endl;
    }
}
void Goblin::print() const{
    cout << "Goblin " << enemyID << " @" << " (" << xLoc << ", " << yLoc << ") hp = " << currentHealth << endl;
}
