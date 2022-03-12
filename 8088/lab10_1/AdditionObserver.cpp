/******************************************************************************
 * Observer Pattern Example
 * Author: Aaron Boudreaux
 *         Updated by Steve Sheehy 6/3/2018
 * Description: This is the implementation of the AdditionObserver class. 
 * When notified of a change, it adds its internal value (addVal) to the value
 * it receives from the Subject class.
 *****************************************************************************/

#include <iostream>
#include "AdditionObserver.h"

//The constructor.
//Make sure that it registers with the Subject class so it can get updates!
//Initialize addVal to the value of a, and set the id appropriately.
AdditionObserver::AdditionObserver(Observable *obs, int a)	{

	obs->addObserver(this);
	addVal = a;
	id = idGenerator++;

}

/*****************************************************************************/
//This method gets called by the Subject class when the state of the Subject changes.
//All this method should do is to print to stdout, showing the observer id,
//and adding newVal to the addVal.
void AdditionObserver::update(int newVal) {
	addVal = newVal + addVal;
	std::cout << "id: " << id << "\nadding newVal: " << addVal << "\n";

}//end of update
