#ifndef BINARY_TREE_H
#define BINARY_TREE_H

#include "Node.h"

class BinaryTree {
    public:
        BinaryTree();
        ~BinaryTree();

        void addNode(int value);
        Node *search(int value);
        void cleanupTree();
        void printTree(bool ascending);

    private:
        void cleanupTree(Node *leaf);
        void printTreeAscending(Node *leaf);
        void printTreeDescending(Node *leaf);
        void addNode(int value, Node *leaf);
        Node *search(int value, Node *leaf);
        Node *root;
};

#endif
