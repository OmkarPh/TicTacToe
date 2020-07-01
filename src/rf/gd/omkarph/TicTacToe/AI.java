package rf.gd.omkarph.TicTacToe;

import java.util.Random;
import java.util.Vector;

public class AI extends Player{
    AI(char s){
    	super(s);
    }
    int[] firstMoves = {0,2,6,8};
    int getMove(TicTacToe game)
    {
        ttt_move move = new ttt_move();

		Random r = new Random();
        if (game.allPossibleMoves().size() == 9) 
            move.index = firstMoves[(int)r.nextInt(4)];
        else
            move = minimax(game, getSymbol());

        return move.index;
    }

    ttt_move minimax(TicTacToe state, char curr_player)
    {
        char max_player = this.getSymbol();
        char other_player = (curr_player == 'X') ? 'O' : 'X';
        ttt_move best = new ttt_move();
        ttt_move current;

        // base condition for win - lose
        if (state.whoWon() == other_player)
        {
            best.score = state.allPossibleMoves().size() + 1;
            if (other_player != max_player)
                best.score = -best.score;
            return best;
        }
        // base condtion for draw
        if (!state.isMovePossible())
        {
            best.score = 0;
            return best;
        }

        if (curr_player == max_player)
            best.score = -1000000;
        else
            best.score = 1000000;

        Vector<Integer> move_set = state.allPossibleMoves();

        for (int i = 0; i < move_set.size(); i++)
        {
            state.doMove(curr_player, move_set.get(i));
            current = minimax(state, other_player);
            state.setChar(' ', move_set.get(i));
            state.resetWinner();
            current.index = move_set.get(i);

            if (curr_player == max_player)
            {
                if (current.score > best.score)
                    best = current;
            }
            else if (current.score < best.score)
                    best = current;
        }
        return best;
    }
}
