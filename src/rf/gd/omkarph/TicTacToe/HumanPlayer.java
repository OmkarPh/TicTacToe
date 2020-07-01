package rf.gd.omkarph.TicTacToe;

import java.util.Scanner;

public class HumanPlayer extends Player {
	
	HumanPlayer(char s) {
		super(s);
	}

    int getMove(TicTacToe game)
    {
        int index;
        Scanner sc = new Scanner(System.in);
        while (true)
        {
            System.out.println("\nEnter index: ");
            index = sc.nextInt();
            if (game.getChar(index - 1) == ' ')
                return index - 1;
            System.out.println("Wrong Move");
        }
    }

}

