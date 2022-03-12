#ifndef ENEMY_H
#define ENEMY_H
#include <stdlib.h> 
#include <iostream>

#define GOB_ID 0
#define ORC_ID 1
#define TROLL_ID 2

class Enemy {
public:
   bool isAlive() const;
   virtual void update() = 0;
   virtual void attack() = 0;
   virtual void injure(int damage) = 0;
   virtual void print() const = 0;
   Enemy (int health, int const cStrength, int const eConstitution, int startX, int startY);
   
    
protected:
    int enemyID;
    int currentHealth;
    int const strength;
    int const constitution;
    int xLoc;
    int yLoc;
    bool alive;
};
#endif
