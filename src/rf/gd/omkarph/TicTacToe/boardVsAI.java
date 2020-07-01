package rf.gd.omkarph.TicTacToe;

import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.scene.shape.DrawMode;
import javafx.stage.Stage;

public class boardVsAI {
	boolean isPlayerX;
	HashMap<Integer, Button> cells = new HashMap<Integer, Button>();
	HashMap<Button, Integer> cellNo = new HashMap<Button, Integer>();
	@FXML
	private Label userChar, aiChar;
	private Button temp;
	@FXML
	private Button cell1, cell2, cell3, cell4, cell5, cell6, cell7, cell8, cell9 ;
	TicTacToe game;
	boolean playWithAi;
	HumanPlayer x_human;
	HumanPlayer o_human;
	AI x_AI;
	AI o_AI;
	Player p1, p2;
	int indexAbsolute;
	public void initialize() {
		cells.put(1, cell1);
		cells.put(2, cell2);
		cells.put(3, cell3);
		cells.put(4, cell4);
		cells.put(5, cell5);
		cells.put(6, cell6);
		cells.put(7, cell7);
		cells.put(8, cell8);
		cells.put(9, cell9);
		cellNo.put(cell1, 1);
		cellNo.put(cell2, 2);
		cellNo.put(cell3, 3);
		cellNo.put(cell4, 4);
		cellNo.put(cell5, 5);
		cellNo.put(cell6, 6);
		cellNo.put(cell7, 7);
		cellNo.put(cell8, 8);
		cellNo.put(cell9, 9);
		game = new TicTacToe();
		x_human = new HumanPlayer('X');
		o_human = new HumanPlayer('O');
		x_AI = new AI('X');
		o_AI = new AI('O');
		p1 = x_human;
		p2 = o_AI;
		char letter = 'X';
	    int index;
	    game.reset();
	    isPlayerX = true;
	}
	public void draw(int position, char letter) throws Exception {
		temp = cells.get(position);
		if( (temp.getText() == "X" || temp.getText() == "x"|| temp.getText() == "O" || temp.getText() == "o") && letter != ' ')
			throw new Exception("Already occupied !");
		temp  = null;
		if(letter == 'X' || letter == 'x') {
			temp = cells.get(position);
			temp.setText("X");
			temp.setTextFill(Color.RED);
		}else if(letter == 'O' || letter == 'o'){
			temp = cells.get(position);
			temp.setText("O");
			temp.setTextFill(Color.BLUE);
		}else {
			temp = cells.get(position);
			temp.setText("");
		}
		if(temp == null)
			throw new Exception("Invalid letter");
	}
	public void showWinner(Player p) {
		Alert winner;
		if(p == null)
			winner = new Alert(Alert.AlertType.INFORMATION, " Game tied. \n Resetting the board.");
		else {
			char symbol = p.getSymbol();
			if(symbol == 'X' || symbol == 'x') 
				winner = new Alert(AlertType.INFORMATION, "X won. \n Resetting the board.");
			else
				winner = new Alert(AlertType.INFORMATION, "O won. \n Resetting the board.");
		}
		winner.showAndWait();
		game.reset();
		reset(null);
		System.out.println("Resetting the board.");
	}
	public void humanMove(ActionEvent event) {
		indexAbsolute = cellNo.get(event.getSource());
		if(game.getChar(indexAbsolute-1) == ' ') {
	        game.doMove(p1.getSymbol(), indexAbsolute-1);
			try {
				draw(indexAbsolute, p1.getSymbol());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("You played");
			System.out.println(game);
			if(game.checkWinner(p1.getSymbol())) {
				showWinner(p1);
				return;
			}
			if(game.allPossibleMoves().size() == 0) {
				showWinner(null);
				return;
			}
			aiMove();
			
		}
	}
	public void aiMove() {
		int index0 = p2.getMove(game);
        game.doMove(p2.getSymbol(), index0);
		System.out.println("AI played at "+index0+1);
		System.out.println(game);
		try {
			draw(index0+1, p2.getSymbol());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(game.checkWinner(p2.getSymbol())) {
			showWinner(p2);
			return;
		}
		if(game.allPossibleMoves().size() == 0) {
			showWinner(null);
			return;
		}
	}
	public void swap(ActionEvent event) {
		if(isPlayerX) {
			p1 = o_human;
			p2 = x_AI;
			userChar.setText("O");
			aiChar.setText("X");
			userChar.setTextFill(Color.BLUE);
			aiChar.setTextFill(Color.RED);
			isPlayerX = false;
		}else {
			p1 = x_human;
			p2 = o_AI;
			userChar.setText("X");
			aiChar.setText("O");
			userChar.setTextFill(Color.RED);
			aiChar.setTextFill(Color.BLUE);
			isPlayerX = true;
		}
		reset(null);
	}
	public void reset(ActionEvent event) {
		for(int i=1; i<10; i++)
			try {
				draw(i,' ');
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		game.reset();
		if(!isPlayerX)
			aiMove();
		System.out.println(game);
	}
	public void goBack(ActionEvent event) {
		Stage thisStage = (Stage)cell1.getScene().getWindow();
		thisStage.close();
		System.out.println("Bye");
	}
}



// default
/*
TicTacToe game = new TicTacToe();
boolean playWithAi;
HumanPlayer x_human = new HumanPlayer('X');
HumanPlayer o_human = new HumanPlayer('O');
AI x_AI = new AI('X');
AI o_AI = new AI('O');

public static void play(TicTacToe game, Player x_player, Player o_player)
{
    char letter = 'X';
    int index;
    game.reset();
    while (game.isMovePossible())
    {
        if (letter == 'O')
            index = o_player.getMove(game);
        else
            index = x_player.getMove(game);

        game.doMove(letter, index);
        System.out.println("\n"+letter+"makes move to "+index+1);
        System.out.println(game);
        
        if (game.checkWinner(letter))
        {
        	System.out.println(letter+" wins! ");
            return;
        }

        letter = (letter == 'X') ? 'O' : 'X';
    }
    System.out.println("Draw :/");
}
*/