#include "List.h"
#include <cstddef>
#include <iostream>
using namespace std;

List::List(){
	head = NULL;
}

List::~List(){

}

void List::addNode(int value){
	cout << "adding value " << value << endl;
	if (head == NULL){
		head = new ListNode(value);
		return;
	}
	ListNode* temp = head;
	while (temp->getNext() != NULL){
		cout << "HERE1" << endl << temp->getValue() << endl;
		temp = temp->getNext();
	}
	cout << " TRYING TO ADD " << value << endl;
	ListNode* y = new ListNode (value);
	temp->setNext(y);
}

ListNode* List::search(int value){
	ListNode* temp = head;
	while (temp != NULL){
		if ( temp->getValue () == value){
			return temp;
		}
		temp = temp->getNext();
	}
	return NULL;
}

int List::getLength(){
	return 0;
}
void List::printList(){

}
