//CS 360 Programming Challenge 2.  
//Steve Sheehy, June 1, 2017.
//Due to stoi, compile with c++11:  g++ -std=c++0x *.cpp

#include <iostream>
#include <fstream>
#include <string>
#include "List.h"

using namespace std;

int main(){

	int value;
	ifstream inFile("data.txt");
	string line;

    cout << "creating list..." << endl;
    List *list = new List();

    int count = 0;
  	while (getline (inFile,line)){
		value = stoi(line);
        list->addNode(value);
        count++;
	}
	inFile.close();

    cout << "done. created " << count << " list entries." << endl;
    list->printList();

    cout << "enter a list value to search for: ";
    int searchValue = 0;
    cin >> searchValue;

    cout << endl << "searching list for " << searchValue << endl;

    ListNode* node = list->search(searchValue);

    if(node == NULL)
        cout << "no such value in list: " << searchValue << endl;
    else
        cout << "found value " << searchValue << " in list." << endl;

    cout << endl << "cleaning up list." << endl;
    delete list;

    cout << "goodbye." << endl;

}
