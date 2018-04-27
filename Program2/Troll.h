#include "Enemy.h"

class Troll : public Enemy{
public:
	Troll(int health, const int cStrength, const int eConstitution, const int startX, const int startY);
	void attack();
	void injure(int attDamage);
	void print() const;
	void update();
};
