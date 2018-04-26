#ifndef ENEMY_H
#define ENEMY_H
#include <stdlib.h> 
#include <iostream>

class Enemy {
public:
   bool isAlive() const;
   virtual void update() = 0;
   virtual void attack() = 0;
   virtual void injure() = 0;
   virtual void print() const = 0;
   Enemy (int health, const int cStrength, const int eConstitution, const int startX, const int startY);
   
    
protected:
    //int const enemyId;
    int currentHealth;
    int const strength;
    int const constitution;
    int const startXLocation;
    int const startYLocation;
    bool alive;
};
#endif
