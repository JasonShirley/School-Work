//CS 360 Programming Challenge 2.  
//Steve Sheehy, June 1, 2017.
#ifndef LIST_NODE_H
#define LIST_NODE_H

class ListNode {
    public:
        ListNode(int val);
        ~ListNode();
        int getValue();
        ListNode* getNext();
        void setNext(ListNode* n);
        void print();

    private:
        int value;
        ListNode *nextPtr;
};

#endif
