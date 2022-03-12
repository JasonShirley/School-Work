/******************************************************************************
 * Observer Pattern Example
 * Author: Stephen Sheehy
 * Description: This is the definition of the Observable class
 * for the Observer pattern.
 *****************************************************************************/

#ifndef OBSERVABLE_H
#define OBSERVABLE_H

#include <vector>
#include "Observer.h"

class Observable {
	public:
		void addObserver(Observer *obs);
		virtual void notify() = 0;

	protected:
		/* This is a vector of Observer pointers. We don't care what the pointers 
		 * are actually pointing at, as long as they inherit from Observer. This way,
		 * we can have lots of different types of Observers that do their own thing
		 * when notify is called.
		 * 
		 * Yay, polymorphism!
		 */
		std::vector<Observer*> views;
};

#endif
