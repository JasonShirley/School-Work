/******************************************************************************
 * Observer Pattern Example
 * Author: Stephen Sheehy
 * Description: This is the implementation of the Observable class
 * for the Observer pattern.
 *****************************************************************************/
#include <iostream>
#include "Observable.h"

//This method should add the Observer pointer to the vector of observers.
void Observable::addObserver(Observer *obs)	{

	views.push_back(obs);

}



