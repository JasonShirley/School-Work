#800080/* This class file defines a Goblin. It will associate values to variables that methods require to.
 * The class will inherit methods from the Enemy header file for use of a new goblin.
 */
#include "Goblin.h"
#include <iostream>
#include <ctime>
#include <cstdlib>
using namespace std;
Goblin::Goblin(int health, int const cstr, int const econ, int startX, int startY):  
	Enemy(health, cstr, econ, startX, startY){   // Construct a new goblin and assign value to Enemy variables
    id = idGenerator;
    idGenerator++;
    print();                                                    // Print the new goblin's attributes.
}
// update Will update a goblin's position. A random number between -3 to 3 will be chosen for the x value
// and a random number between -2 to 2 will be chosen to the y value.
void Goblin::update() {
        xPosition = xPosition + rand() % 6 - 3;
        yPosition = yPosition + rand() % 4 - 2;
    }
// Attack will calculate an damage value to deal to a random passerby. The damge is caluculated by a d4 random
// number generation. When a random number is generated the number is added to the str value to determine the
// amount of damage done to a passerby
void Goblin::attack() const{
    int attackDam;
    int dice = rand() % 4 + 1;
    attackDam = str + dice;
    cout << "Goblin " << id << " attacks a random passerby for " << attackDam << " damage!" << endl;
}
// Injure will determine the amount of damage done to a goblin. The method is passed an attack value from a driver.
// The method then calculates the damage done by subtracting half the value of the goblins con from the attack damage.
// The method then applies the damage done to the health and prints off the current health status of the goblin.
void Goblin::injure(int attDamage){
    int damage = attDamage - (.5 * con);
    if (damage < 0) { damage = 0; }
        health = health - damage;
    // If the current health falls below 0, then change alive value to false to represent that the goblin is no longer with us.
    if (health <= 0){
        alive = false;
        cout << "Goblin " << id << " takes " << damage << " damage! Current hp = " << health << endl;
        cout << "Goblin " << id << " has been slain!" << endl;
    }
    else if (alive){
        if (damage <= 0){
            cout << "The passer by tries to attack Goblin " << id << ", but it's not very effective..."<< endl;
        }
        cout << "Goblin " << id << " takes " << damage << " damage! Current hp = " << health << endl;
    }
    cout << "HERE" << endl;		

}
// Prints the current status of the goblin.
void Goblin::print() const{
    cout << "Goblin " << id << " @" << " (" << xPosition << ", " << yPosition << ") hp = " << health << endl;
}
const char Goblin::getDisplayChar() const{
	if (alive){
		return 'G';
	}
	else return 'g';
}
