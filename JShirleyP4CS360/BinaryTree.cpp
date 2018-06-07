#include <cstdlib>
#include <iostream>
#include "BinaryTree.h"
#include "Node.h"
#include <cstring>
using namespace std;

BinaryTree::BinaryTree(){
	root = NULL;
}
BinaryTree::~BinaryTree(){
	cleanupTree();
    root = NULL;
}
void BinaryTree::cleanupTree(){
    cleanupTree(root);
}
void BinaryTree::cleanupTree(Node* leaf){
	/*(if(leaf != NULL){
		if (leaf->getRight() != NULL){
			cout << "Deleted Node: " << leaf->getRight()->getValue() << " addressed at " << leaf->getRight() << endl;
			delete leaf->getRight();
			cleanupTree(leaf->getRight());
		}
		if (leaf->getLeft() != NULL){
			cout << "Deleted Node: " << leaf->getLeft()->getValue() << " addressed at " << leaf->getLeft() << endl;
			delete leaf->getLeft();
			cleanupTree(leaf->getLeft());
		}
	}*/
	delete leaf;
}

void BinaryTree::addNode(int value){
	Node* leaf = new Node(value);
	if (root == NULL){
		root = leaf;
		return;
	}
	else if (root != NULL){
		addNode(value, root);
	}
	delete leaf;
}
void BinaryTree::addNode(int value, Node* leaf){
	Node* temp = new Node(value);
	if (value < leaf->getValue()){
		if (leaf->getLeft() == NULL){
			leaf->setLeft(temp);
			return;
		}
		else{
			addNode(value, leaf->getLeft());
		}
	}	
	else if (value > leaf->getValue()){
		if (leaf->getRight() == NULL){
			leaf->setRight(temp);
			return;
		}
		else{
			addNode(value, leaf->getRight());
		}
	}
	delete temp;
}
Node* BinaryTree::search(int value){
	Node* temp = root;
	if (temp != NULL){
		if(temp->getValue() == value){
			return temp;
		}
		if(temp->getValue() > value){
			temp = search(value, temp->getLeft());
		}
		if(temp->getValue() < value){
			temp = search(value, temp->getRight());
		}
	}
	return temp;
}
Node* BinaryTree::search(int value, Node* leaf){
	if(leaf != NULL){
		if (leaf->getValue() == value){
			return leaf;
		}
		if (leaf->getValue() > value && leaf->getLeft() != NULL){	
			leaf = search(value, leaf->getLeft());
		}
		if (leaf->getValue() < value && leaf->getRight() != NULL){
			leaf = search(value, leaf->getRight());
		}
	}
	return leaf;
}
void BinaryTree::printTree(bool ascending){
    if (root == 0){return;}
    if (root != NULL){
        if (ascending){printTreeAscending(root);}
        else { printTreeDescending(root); }
    }
}
void BinaryTree::printTreeAscending(Node* leaf){
	string order = "";
	if (leaf != NULL){
		printTreeAscending(leaf->getLeft());
		cout<< leaf->getValue() << endl;
		printTreeAscending(leaf->getRight());
	}
}
void BinaryTree::printTreeDescending(Node* leaf){
	string order = "";
	if (leaf != NULL){
		printTreeDescending(leaf->getRight());
		cout << leaf->getValue() << endl;
		printTreeDescending(leaf->getLeft());
	}
}
