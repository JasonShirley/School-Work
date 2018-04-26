#include "Enemy.h"

class Goblin : public Enemy{
public:
	Goblin(int health, const int cStrength, const int eConstitution, const int startX, const int startY);
	void attack();
	void injure();
	void print() const;
	void update();
};

