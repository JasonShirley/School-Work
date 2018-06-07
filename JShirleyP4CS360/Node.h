#ifndef NODE_H
#define NODE_H

class Node {
    public:
        Node(int val);
        ~Node();
        int getValue();
        Node* getLeft();
        Node* getRight();
        void setLeft(Node* l);
        void setRight(Node* r);

    private:
        int value;
        Node *left;
        Node *right;
};

#endif
