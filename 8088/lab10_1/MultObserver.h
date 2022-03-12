/******************************************************************************
 * Observer Pattern Example
 * Author: Aaron Boudreaux
 *         Updated by Steve Sheehy 6/3/2018
 * Description: This is the declaration of the MultObserver class. When notified
 *	of a change, it multiplies its internal value (multVal) to the value it receives 
 *	from the Subject class.
 *****************************************************************************/

#ifndef MULT_OBSERVER_H
#define MULT_OBSERVER_H

#include "Observer.h"
#include "Observable.h"

class MultObserver : public Observer {
	private:
		int id;
		int multVal;
	public:
		MultObserver(Observable *sub, int a);
		void update(int newVal);
};

#endif
