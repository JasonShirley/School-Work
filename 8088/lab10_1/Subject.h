/******************************************************************************
 * Observer Pattern Example
 * Author: Aaron Boudreaux
 *         Updated by Steve Sheehy 6/3/2018
 * Description: This file is the Subject. It is an observable.
 * It notifies the observers whenever the int member "value" changes.
 *****************************************************************************/

#ifndef SUBJECT_H
#define SUBJECT_H

#include <vector>
//#include "Observer.h"
#include "Observable.h"

class Subject : public Observable	{
	public:
		void setValue(int newVal);
		void notify();
	protected:
		int value;

};

#endif
