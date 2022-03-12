/******************************************************************************
 * Observer Pattern Example
 * Author: Aaron Boudreaux
 *         Updated by Steve Sheehy 6/3/2018
 * Description: This is the declaration of the Observer parent class. It is an
 *	abstract base class, and thus an instance of this class cannot be created.
 *	Since this declaration does everything we need, there is no Observer.cpp
 *****************************************************************************/

#ifndef OBSERVER_H
#define OBSERVER_H

class Observer	{
	public:
		//pure virtual function - must be overridden by any inheriting class
		virtual void update(int val) = 0; 
	protected:
		static int idGenerator;
};

#endif
