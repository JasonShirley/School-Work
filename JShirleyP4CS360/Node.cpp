#include "Node.h"
#include <cstdlib>
#include <iostream>
using namespace std;

Node::Node(int val){
	value = val;
	left = NULL;
	right = NULL;
};
Node::~Node(){
	delete left;
	delete right;
};
int Node::getValue(){
	return value;
};
Node* Node::getLeft(){
	return left;
};
Node* Node::getRight(){
	return right;
};
void Node::setLeft(Node* l){
	left = l;
};
void Node::setRight(Node* r){
	right = r;
};


