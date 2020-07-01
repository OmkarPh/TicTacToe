package rf.gd.omkarph.TicTacToe;

public abstract class Player {
	char symbol;
	String playerName;
    Player(char s) { 
    	symbol = s;
    	playerName = "";
    }
    public void setName(String name) {
    	playerName = name;
    }
    public String getName() {
    	return playerName;
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
