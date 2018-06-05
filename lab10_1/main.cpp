/******************************************************************************
 * Observer Pattern Example
 * Author: Aaron Boudreaux
 *         Updated by Steve Sheehy 6/3/2018
 * Description: This program demonstrates the Observer design pattern.
 *****************************************************************************/
#include <iostream>
#include "AdditionObserver.h"
#include "MultObserver.h"
#include "Subject.h"

int main() {

//add code to create a subject
Subject* j = new Subject();

//add code to create Observers
AdditionObserver* i = new AdditionObserver(j, 1);
AdditionObserver* k = new AdditionObserver(j, 1);
AdditionObserver* l = new AdditionObserver(j, 1);

	int value;

	std::cout << "~*~*~* Observer test program *~*~*~\n";

	int turn = 0;
	do	{
		std::cout << "\nEnter an integer (0 to quit): ";
		std::cin >> value;

		if(!std::cin){
			std::cout << "Invalid value\n";
			value = -1;
			std::cin.clear();
			std::cin.ignore(1000, '\n');
		}
		else{
			if((turn % 2) == 0){
				std::cout << "updating subject 1\n";
				//add code to update subject
				j->setValue(value);

			}
			else{
				std::cout << "updating subject 2\n";
				//add code to update subject
				j->setValue(value);

			}
		}
		turn++;
	} while (value != 0);

	std::cout << "Goodbye!\n";
	return 0;

}

