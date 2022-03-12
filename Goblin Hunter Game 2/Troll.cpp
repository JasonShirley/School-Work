/* This class file defines a Troll. It will associate values to variables that methods require to.
 * The class will inherit methods from the Enemy header file for use of a new Troll.
 */
#include "Troll.h"
#include <iostream>
using namespace std;

Troll::Troll(int health, int const cStrength, int const eConstitution, int startX, int startY):
	Enemy(health, cStrength, eConstitution, startX, startY), startingHealth(health)
{
    enemyID = TROLL_ID;
    print();
}
// update Will update a Troll's position. A random number between -7 to -10 and 7 to 10 on the x axis.
// update will also regenerate a Troll's heal equal to the troll's constitution.
void Troll::update() {
    int posMinus = (rand() % 3 + (-10));
    int posPlus = (rand() % 3 + 7);
    // Compare two random generated values from 7 to 10 and -7 to -10. Change the negative value to positive and 
    // compare the two values.
    if (-(posMinus) > posPlus) { xLoc = xLoc + posMinus;}       // If the negative value was bigger, then set that value
    else {xLoc = xLoc + posPlus;}                               // If the positive value was bigger, then sat that value
    currentHealth = currentHealth + constitution;
    if (currentHealth > startingHealth) {currentHealth = startingHealth;}           // Regenerate health
    cout << "Troll " << enemyID << " regenerates " << constitution << " health" << endl;
}
// Attack will calculate an damage value to deal to a random passerby. The damge is caluculated by a d8 random
// number generation. When a random number is generated the number is added to the strength value to determine the
// amount of damage done to a passerby.
void Troll::attack(){
    int attackDam;
    int dice = rand() % 8 + 1;
    attackDam = strength + dice;
    cout << "Troll " << enemyID << " attacks a random passerby for " << attackDam << " damage!" << endl;
}
// Injure will determine the amount of damage done to a Troll. The method is passed an attack value from a driver.
// The method then calculates the damage done by subtracting half the value of the goblins constitution from the attack damage.
// The method then applies the damage done to the health and prints off the current health status of the Troll.
void Troll::injure(int attDamage){
    int damage = attDamage - (1.5 * constitution);
    if (damage < 0) { damage = 0; }
    currentHealth = currentHealth - damage;
    // If the current health falls below 0, then change alive value to false to represent that the Troll is no longer with us.
    if (currentHealth <= 0){
        alive = false;
        cout << "Troll " << enemyID << " takes " << damage << " damage! Current hp = " << currentHealth << endl;
        cout << "Troll " << enemyID << " has been slain!" << endl;
    }
    else if (alive){
        if (damage <= 0){
            cout << "The passer by tries to attack Troll " << enemyID << ", but it's not very effective..."<< endl;
        }
        cout << "Troll " << enemyID << " takes " << damage << " damage! Current hp = " << currentHealth << endl;
    }
}
void Troll::print() const{
    cout << "Troll " << enemyID << " @" << " (" << xLoc << ", " << yLoc << ") hp = " << currentHealth << endl;
}
