/******************************************************************************
 * Observer Pattern Example
 * Author: Aaron Boudreaux
 *         Updated by Steve Sheehy 6/3/2018
 * Description: This is the declaration of the AdditionObserver class. 
 * When notified of a change, it adds its internal value (addVal) to the value
 * it receives from the Subject class.
 *****************************************************************************/

#ifndef ADDITION_OBSERVER_H
#define ADDITION_OBSERVER_H

#include "Observer.h"
#include "Observable.h"

class AdditionObserver : public Observer {
	private:
		int id;
		int addVal;
	public:
		AdditionObserver(Observable *obs, int a);
		void update(int newVal);
	};

#endif
