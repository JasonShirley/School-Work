//CS 360 Programming Challenge 2.  
//Steve Sheehy, June 1, 2017.
#ifndef LIST_H
#define LIST_H

#include "ListNode.h"

class List {
    public:
        List();
        ~List();

        void addNode(int value);
        ListNode *search(int value);
        int getLength();
        void printList();
        //void sortList();

    private:
        ListNode *head;
};

#endif
