#ifndef ENEMY_H
#define ENEMY_H

class Enemy	{
	protected:
		int id;
		int health;
		int str;
		int con;
		int xPosition;
		int yPosition;
		bool alive;
		static int idGenerator;

	public:
		Enemy(int, int, int, int, int);
		bool isAlive() const;
		virtual void update() = 0;
		virtual void print() const = 0;
		virtual void attack() const = 0;
		virtual void injure(int) = 0;
		virtual const char getDisplayChar() const = 0;

		int getId() const;
		int getHealth() const;
		int getStr() const;
		int getCon() const;
		int getX() const;
		int getY() const;

}; //end of class

#endif
