package rf.gd.omkarph.TicTacToe;

import java.util.Vector;

public class TicTacToe {
	char board[][];
    char winner;

    // constructor
    public TicTacToe() { 
    	board = new char[3][3];
    	reset(); 
    }

    // reset
    void resetBoard()
    {
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
                board[i][j] = ' ';
        }
    }
    void resetWinner() {
    	winner = ' ';
    }
    public void reset()
    {
        resetBoard();
        resetWinner();
    }

    // setters and getters
    public void setChar(char symbol, int i) { board[i / 3][i % 3] = symbol; }
    public void setChar(char symbol, int i, int j) { board[i][j] = symbol; }
    public char getChar(int i) { return board[i / 3][i % 3]; }
    public char getChar(int i, int j) { return board[i][j]; }
    public char whoWon() { return winner; }

    // possible moves
    public boolean isMovePossible()
    {
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (board[i][j] == ' ')
                    return true;
            }
        }
        return false;
    }
    public Vector<Integer> allPossibleMoves()
    {
        Vector<Integer> move_set = new Vector<Integer>();
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (board[i][j] == ' ')
                	move_set.add(3*i+j);
            }
        }
        return move_set;
    }

    // check winner
    public boolean checkWinner(char symbol)
    {
        // horizontal check
        for (int i = 0; i < 3; i++)
        {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][2] == symbol)
                return true;
        }

        // vertical check
        for (int i = 0; i < 3; i++)
        {
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[2][i] == symbol)
                return true;
        }

        // diagonal 1 check
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[1][1] == symbol)
            return true;

        // diagonal 2 check
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[1][1] == symbol)
            return true;

        return false;
    }

    // do move
    public void doMove(char symbol, int index)
    {
        int i = index / 3, j = index % 3;
        board[i][j] = symbol;

        if (checkWinner(symbol))
            winner = symbol;
    }
    public String toString() {
		String result = "";
		result += ".-----------.\n";
		for(int i=0; i<3; i++) {
			result += "| ";
			for(int j=0; j<3; j++) {
				result += board[i][j] + " | ";
			}
			result += "\n";
		}
		result += "'-----------'";
		return result;
	}
}
