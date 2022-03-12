/* This class file defines a Orc. It will associate values to variables that methods require to.
 * The class will inherit methods from the Enemy header file for use of a new Orc.
 */
#include "Orc.h"
#include <iostream>
#include <ctime>
#include <cstdlib>
using namespace std;
Orc::Orc(int health, int const cstr, int const econ, int startX, int startY):
	Enemy(health, cstr, econ, startX, startY)
{
    id = idGenerator;
    idGenerator++;
    print();
}
// update Will update a Orc's position. A random number between -5 and 5 on both the x and y
// values to represent diagonal movement.
void Orc::update() {
        int pos = rand() % 11 - 5;
        xPosition = xPosition + pos;
        yPosition = yPosition + pos;
    }
// Attack will calculate an damage value to deal to a random passerby. The damge is caluculated by a d6 random
// number generation. When a random number is generated the number is added to the str value to determine the
// amount of damage done to a passerby.
void Orc::attack() const{
    int attackDam;
    if (alive){
        int dice = rand() % 6 + 1;
        attackDam = str + dice;
    }
    cout << "Orc " << id << " attacks a random passerby for " << attackDam << " damage!" << endl;
}
// Injure will determine the amount of damage done to a Orc. The method is passed an attack value from a driver.
// The method then calculates the damage done by subtracting half the value of the goblins con from the attack damage.
// The method then applies the damage done to the health and prints off the current health status of the Orc.
void Orc::injure(int attDamage){
    int damage = attDamage - con;
    if (damage < 0) { damage = 0; }
        health = health - damage;
    // If the current health falls below 0, then change alive value to false to represent that the Orc is no longer with us.
    if (health <= 0){
        alive = false;
        cout << "Orc " << id << " takes " << damage << " damage! Current hp = " << health << endl;
        cout << "Orc " << id << " has been slain!" << endl;
    }
    else if (alive){
        if (damage <= 0){
            cout << "The passer by tries to attack Orc " << id << ", but it's not very effective..."<< endl;
        }
        cout << "Orc " << id << " takes " << damage << " damage! Current hp = " << health << endl;
    }

}
// Prints out an Orc's current status
void Orc::print() const{
    cout << "Orc " << id << " @" << " (" << xPosition << ", " << yPosition << ") hp = " << health << endl;
}
const char Orc::getDisplayChar() const{
	if (alive){
		return 'O';
	}
	return 'o';
}
