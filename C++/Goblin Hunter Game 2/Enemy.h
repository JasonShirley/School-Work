/*
 * Jason Shirley
 * CS 360 Spring 2018
 * Assignment #2
 * This header file sets up what methods and what variables will be used for the 
 * enemy class. Other classes will be able to inherit the methods and variables
 * defined in the enemy class.
*/
#ifndef ENEMY_H
#define ENEMY_H

#define GOB_ID 0
#define ORC_ID 1
#define TROLL_ID 2

class Enemy {
public:
   bool isAlive() const;                    // Checks for if an enemy is alive
   virtual void update() = 0;               // Calculates and updates the position of the enemy
   virtual void attack() = 0;               // Attacks a target for a random amount of damage
   virtual void injure(int damage) = 0;     // Calculates the amount of damage done to an enemy
   virtual void print() const = 0;          // Prints the status of an Enemy
   Enemy (int health, int const cStrength, int const eConstitution, int startX, int startY); // Enemy constructor
   
    
protected:
    int enemyID;                            // Identifies an enemy
    int currentHealth;                      // Health points
    int const strength;
    int const constitution;
    int xLoc;
    int yLoc;
    bool alive;
};
#endif
