/*
 * Jason Shirley
 * CS360 Spring 2018
 * ConnectFourBoard.h
 * This is the header file for ConnectFourBoard
 */

class ConnectFourBoard{
    public:
        void playerAction(char color); // sets the players move to the board
        void validColumn(int columnChoice);   // checks if the current move is valid
        bool checkEndGame(); // checks to see if the current move has satisfied end game conditions
        bool checkLAndR() const; // counts number of same color on the horizontal 
        bool checkDown() const; // counts the number of same color on the vertical
        bool checkRightDiagonal() const; // counts the number of same color on the right diagonal
        bool checkLeftDiagonal() const; // counts the number of same colore on the left diagonal
        void printBoard() const; // print the board's current state
        ConnectFourBoard(); // Create a new ConnectFourBoard
    private:
        int tieCount = 0; // check for tie
        char board[6][7]; // Playing board
        char color;
        int column = 0;
        int row = 0;

};
