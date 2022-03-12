/*
 * Jason Shirley
 * CS 360 Spring 2018
 * ConnectFourBoard.cpp
 * ConnectFourBoard is a class for creating a ConnectFourBoard. From within
 * this class you can create a new ConnectFourBoard as well as destroy the
 * same ConnectFourBoard. The ConnectFourBoard starts by creating a new 
 * ConnectFourBoard with 6 rows and 7 columns. The columns initially start out
 * with '_' in each cell. When an action is played, th class takes care of putting
 * the appropriate colored token into the appropriate slot. The class keeps track
 * durning every turn if there is a winning condition of connect four.
 */

#include <iostream>
#include "ConnectFourBoard.h"
#define MAX_ROW  5
#define MAX_COL  6
#define ARR_MIN  0

using namespace std;

// Constructor for new ConnectFourBoard. The board fills a board array of 6X7
// with '_' as each position's default value.
ConnectFourBoard::ConnectFourBoard(){
    for (int i = 0; i <= MAX_ROW ; i++){
            for (int j = 0; j <= MAX_COL; j++){
                board[i][j] = '_';
            }
    }
}
  
// Prints the current state of the board
void ConnectFourBoard::printBoard() const{
    cout << endl << "Current Board Status:" << endl;
    // print out 0-6 at the top of the board to show valid inputs
    for (int i = 0; i <= MAX_COL; i++){
        cout << i << " ";
    }
    cout << endl;
    // print the board
    for (int i = 0; i <= MAX_ROW ; i++){  
            for (int j = 0; j <= MAX_COL; j++){
                cout << board[i][j] << " "; // print what currently occupies this space as well as a space character.
            }
            cout << endl;
    }
}

/* validColumn checks to see if the current selection is a valid choice for the game board. If the 
 * choice goes out of bounds, the game will reprompt the player to choose a correct position until
 * a valid choice is made.
 */ 
void ConnectFourBoard::validColumn(int columnChoice){
    column = columnChoice;
    while (cin.fail() || column < 0 || column > 6 || board[0][column] == 'r' || board[0][column] == 'b'){
        cout << "\n\nI'm sorry, but that is not a valid choice. Please try again.\n\n";
        cout << "Enter column (0-6): ";
        cin.clear();    // clear cin
        cin.ignore(100000, '\n');   // ignore the invalid input
        printBoard();
        cin >> column;
    }
}

/* playerAction records the current players move onto the gameboard. The method will find the appropriate 
 * in the connect four board and  
 */
void ConnectFourBoard::playerAction(char playColor){
    color = playColor;
    tieCount ++;
    int i = 0;
    while (board[i][column] != 0 && board[i][column] != 'r' && board[i][column] != 'b')
    {
        if (board[i+1][column] != '_' && i <= MAX_ROW){
            row = i;
            board[i][column] = color;
        }
        i++;
    }
}

/* checkEndGame checks for the win condition. It calls four helper functions to assist
 * in doing the math for finding out who has won the game. The checks are explained in the
 * following code;
 */
bool ConnectFourBoard::checkEndGame(){
    // check the horizontal axis for connect four
    string fullColorName;
    if (color == 'r'){
        fullColorName = "red";
    }
    else{
        fullColorName = "black";
    }
    if (checkLAndR() || checkDown() || checkRightDiagonal() || checkLeftDiagonal()){
        printBoard();
        cout << "The winner is " << fullColorName << endl;
        return true;
    }
    if (tieCount == 42){
        cout << "\nThe game has ended in a tie" << endl;
        return true;
    }
    // there was no connect four this turn
    return false;
}

/* checkLAndR is used as a helper funciton for checkEndGame() as a way to do the math for fiding
 * if there was a connect four on the horizontal axis of the board. It traverses the array on the
 * horizontal axis counting the number of matching colored tokens according to whatever color is set
 * for the color variable. 
 */
bool ConnectFourBoard::checkLAndR() const{
    int checkColumn = column;   // temp variable to allow manipulation of column
    int checkRow = row;         // temp variable to allow maniuplation of row
    int count = 1;              // connect four check
    // check if the position right of the token matches in color and is in bounds of the column array
    while (board[checkRow][checkColumn+1] == color && checkColumn +1 <= MAX_COL){
            count ++;           
            checkColumn++;      // move right
        }
    checkColumn = column;       // reset position of column to check the oposite direction
    // check if the position left of the token is the same color and is in bounds of the column array
    while (board[checkRow][checkColumn-1] == color && checkColumn -1 >= ARR_MIN){
            count++;
            checkColumn--;      // move left
    }
    // connect four has happened
    if (count >= 4){
        return true;
    }
    // connect four hasn't happened this turn
    return false;
}
/* checkRightDiagonal is a helper function for checkEndGame() to check if there is a connect four in
 * a rightward facing diagonal. It traverses going up and right, resets to the drop position, then traverses
 * going lower left. The method counts going along if the color of the token matches. The method returns true
 * if the count reaches 4 or higher. It returns false if the count didn't reach 4.
 */
bool ConnectFourBoard::checkRightDiagonal() const{
    int checkColumn = column;       // temp variable to allow manipulation of column
    int checkRow = row;             // temp variable to allow manipulation of row
    int count = 1;                  // connect four check
    // check if the position up and right of where the token landed is the same color and if row and column are in bounds
    while (board[checkRow-1][checkColumn+1] == color && checkRow >= ARR_MIN && checkColumn < MAX_COL){
        count ++;
        checkColumn++; // move right
        checkRow--;    // move up
    }
    checkColumn = column;   // reset temp column for reverse check
    checkRow = row;         // reset temp row for reverse check
    // check if the position dow and left of where the token has leded is the same color and if row and column are in bounds
    while (board[checkRow+1][checkColumn-1] == color && checkColumn >= ARR_MIN && checkRow < MAX_ROW){
        count++;
        checkColumn--;      // move left
        checkRow++;         // move down
    }
    // connect four has happened
    if (count >=4){
        return true;
    }
    // connect four hasn't happened this turn
    return false;
}
/* checkLeftDiagonal checks is a helper function for checkEndGame() to check if there is a connect four in 
 * a leftward facing diagonal. It traverses going down and right, resets to the drop position, then traverses
 * going up and left. The method counts going along for if the token matches and returns when 4 matches happen.
 */
bool ConnectFourBoard::checkLeftDiagonal() const{
    int checkColumn = column;
    int checkRow = row;
    int count = 1;
    // go up and to the right of the diagonal until there are no more matching colors
    while (board[checkRow+1][checkColumn+1] == color && checkRow + 1 <= MAX_ROW && checkColumn +1 <= MAX_COL){
        count ++;
        checkColumn++;  // move right
        checkRow++;     // move down
    }
    checkColumn = column;   // reset the temp column for reverse check
    checkRow = row;         // reset the temp row for reverse check
    // go down and left of the diagonal until there are no more matching colors
    while (board[checkRow-1][checkColumn-1] == color && checkRow - 1 >= ARR_MIN && checkColumn - 1 >= ARR_MIN){
        count++;
        checkColumn--;  // move left
        checkRow--;     // move up
    }
    // if the count is four, the current player has won
    if (count >=4){
        return true;
    }
    // connect four hasn't happend
    return false;
}
// checkDown is a helper fuction for checkEndGame() to check if there is a connect four in the doward position
// it traverses down and keeps count of all the matching colored tokens. 
bool ConnectFourBoard::checkDown() const{
    int checkColumn = column;
    int checkRow = row;
    int count = 1;
    // check if the downward position is the same color and in bounds. If so move down.
    while (board[checkRow+1][checkColumn] == color && checkRow + 1 <= MAX_ROW){
        count++;
        checkRow++; // move down
        }            
    // connect four has happened
    if (count >= 4){
        return true;
    }
    // connect four hasn't happened yet
    return false;
}
    
