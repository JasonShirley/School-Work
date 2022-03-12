/*
 * Jason Shirley
 * CS 360 Spring 2018
 * Assignment #1
 * main.cpp drives the game. It prompts the user to start the game, builds a new ConnectFourBoard,
 * and calls ConnectFourBoard methods to play the game.
 */

#include <iostream>
#include "ConnectFourBoard.h"

using namespace std;

/* startGame is a helper method for main to get the game started. It prompts the user to enter either 
 * 1 to start a game or 0 to quit. If the input is invalid it will ask the user to re-enter an input 
 * until it is correct.
 */

void startGame(){
    int playerInput;    // Used for menu choice, either to play game or quit.
    cout << endl << "~*~*~*~ Welcome to Connect Four! *~*~*~" << endl << endl;
    cout << "0: quit" << endl << "1: new game" << endl << "Enter Choice: ";
    cin >> playerInput;
    // Check to see if a valid input is made, either 0 or 1. If the choice is 
    // invalid, keep prompting the user to enter an input until 0 or 1 is chosen.
    while (cin.fail() || playerInput < 0 || playerInput > 1 ){
        cout << "I'm sorry, but that is not a valid choice. Please try again." << endl;
        cout << "0: quit" << endl << "1: new game" << endl << "Enter Choice: ";
        cin.clear();    // clear cin
        cin.ignore(10000, '\n');
        cin >> playerInput;
    }
    if (playerInput == 0){
        cout << "Exiting Game" << endl;
        exit(1);
    }
    
}


int main(){
    const char red = 'r';
    const char black = 'b';    
    int columnChoice;    
    startGame();
    ConnectFourBoard board = ConnectFourBoard();
    
    board.printBoard();
    
    while (true){
        cout << endl << "It is red's turn." << endl << "Enter column (0-6): " << endl;
        cin >> columnChoice;
        board.validColumn(columnChoice);
        cin.clear();
        board.playerAction(red);
        if (board.checkEndGame()){
            startGame();
            board = ConnectFourBoard();
        }
        board.printBoard();
        cout << endl << "It is black's turn." << endl << "Enter column (0-6): " << endl;
        cin >> columnChoice;
        board.validColumn(columnChoice);
        cin.clear();
        board.playerAction(black);
        if (board.checkEndGame()){
            startGame();
            board = ConnectFourBoard();
        }
        board.printBoard();
    }
}


