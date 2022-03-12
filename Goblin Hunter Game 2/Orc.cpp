/* This class file defines a Orc. It will associate values to variables that methods require to.
 * The class will inherit methods from the Enemy header file for use of a new Orc.
 */
#include "Orc.h"
#include <iostream>
using namespace std;
Orc::Orc(int health, int const cStrength, int const eConstitution, int startX, int startY):
	Enemy(health, cStrength, eConstitution, startX, startY)
{
    enemyID = ORC_ID;
    print();
}
// update Will update a Orc's position. A random number between -5 and 5 on both the x and y
// values to represent diagonal movement.
void Orc::update() {
        int pos = rand() % 10 - 5;
        xLoc = xLoc + pos;
        yLoc = yLoc + pos;
    }
}
// Attack will calculate an damage value to deal to a random passerby. The damge is caluculated by a d6 random
// number generation. When a random number is generated the number is added to the strength value to determine the
// amount of damage done to a passerby.
void Orc::attack(){
    int attackDam;
    if (alive){
        int dice = rand() % 6 + 1;
        attackDam = strength + dice;
    }
    cout << "Orc " << enemyID << " attacks a random passerby for " << attackDam << " damage!" << endl;
}
// Injure will determine the amount of damage done to a Orc. The method is passed an attack value from a driver.
// The method then calculates the damage done by subtracting half the value of the goblins constitution from the attack damage.
// The method then applies the damage done to the health and prints off the current health status of the Orc.
void Orc::injure(int attDamage){
    int damage = attDamage - constitution;
    if (damage < 0) { damage = 0; }
        currentHealth = currentHealth - damage;
    // If the current health falls below 0, then change alive value to false to represent that the Orc is no longer with us.
    if (currentHealth <= 0){
        alive = false;
        cout << "Orc " << enemyID << " takes " << damage << " damage! Current hp = " << currentHealth << endl;
        cout << "Orc " << enemyID << " has been slain!" << endl;
    }
    else if (alive){
        if (damage <= 0){
            cout << "The passer by tries to attack Orc " << enemyID << ", but it's not very effective..."<< endl;
        }
        cout << "Orc " << enemyID << " takes " << damage << " damage! Current hp = " << currentHealth << endl;
    }

}
// Prints out an Orc's current status
void Orc::print() const{
    cout << "Orc " << enemyID << " @" << " (" << xLoc << ", " << yLoc << ") hp = " << currentHealth << endl;
}
