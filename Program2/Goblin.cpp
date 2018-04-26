#include "Goblin.h"
#include <iostream>
using namespace std;
	Goblin::Goblin(int health, const int cStrength, const int eConstitution, const int startX, const int startY):Enemy(currentHealth, strength, constitution, startXLocation, startYLocation)
{
}
	void Goblin::attack(){
		int dice = rand() % 4 + 1;
		int attack = strength + dice;
        cout << "STRENGTH IS " << strength << endl;
		cout << "ATTACK WAS " << attack << endl;
	}
	void Goblin::injure(){
        
    }
	void Goblin::print() const{}
	void Goblin::update() {}
