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
// Attack will calculate an damage value to deal to a random passerby. The damge is caluculated by a d4 random
// number generation. When a random number is generated the number is added to the strength value to determine the
// amount of damage done to a passerby
void Goblin::attack(){
    int attackDam;
    int dice = rand() % 4 + 1;
    attackDam = strength + dice;
    cout << "Goblin " << enemyID << " attacks a random passerby for " << attackDam << " damage!" << endl;
}
// Injure will determine the amount of damage done to a goblin. The method is passed an attack value from a driver.
// The method then calculates the damage done by subtracting half the value of the goblins constitution from the attack damage.
// The method then applies the damage done to the health and prints off the current health status of the goblin.
void Goblin::injure(int attDamage){
    int damage = attDamage - (.5 * constitution);
    if (damage < 0) { damage = 0; }
        currentHealth = currentHealth - damage;
    // If the current health falls below 0, then change alive value to false to represent that the goblin is no longer with us.
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
// Prints the current status of the goblin.
void Goblin::print() const{
    cout << "Goblin " << enemyID << " @" << " (" << xLoc << ", " << yLoc << ") hp = " << currentHealth << endl;
}
