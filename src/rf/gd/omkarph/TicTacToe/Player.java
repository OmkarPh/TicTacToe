package rf.gd.omkarph.TicTacToe;

public abstract class Player {
	char symbol;
	
    Player(char s) { 
    	symbol = s;
    }

    char getSymbol() {
    	return symbol; 
    }

    abstract int getMove(TicTacToe game);

	class ttt_move
	{
	    int index;
	    long score;
	}
}
