/******************************************************************************
 * Observer Pattern Example
 * Author: Aaron Boudreaux
 * 	       Updated by Steve Sheehy, 6/3/2018
 * Description: This is the implementation of the Subject for the Observer pattern.
 *****************************************************************************/
#include "Subject.h"
#include <iostream>
using namespace std;
/* Standard notify function - For each Observer that is registered, call
 * the update function
 */
void Subject::notify() {

	for (auto &x : views){
		x->update(value);
	} 

}

/*****************************************************************************/

/* Set this objects value to the newVal, then call notify to let the observers 
 * know that the state has changed.
 */
void Subject::setValue(int newVal) {

	value = newVal;
	notify();


}//end of setValue
