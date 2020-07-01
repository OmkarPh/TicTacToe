package rf.gd.omkarph.TicTacToe;

import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class boardVsHuman {
	HashMap<Integer, Button> cells = new HashMap<Integer, Button>();
	HashMap<Button, Integer> cellNo = new HashMap<Button, Integer>();
	@FXML
	private Label userChar, aiChar;
	private Button temp;
	@FXML
	private Button cell1, cell2, cell3, cell4, cell5, cell6, cell7, cell8, cell9 ;
	@FXML
	private TextField player1Text, player2Text;
	TicTacToe game;
	HumanPlayer x_human;
	HumanPlayer o_human;
	AI x_AI;
	AI o_AI;
	Player p1, p2;
	Player currentPlayer;
	int indexAbsolute;
	boolean isP1X;
	String player1, player2;
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
		p1 = x_human;
		p2 = o_human;
		currentPlayer = p1;
		isP1X = true;
		player1 = "Player1";
		player2 = "Player2";
		player1Text.setText(player1);
		player2Text.setText(player2);
		x_human.setName(player1);
		o_human.setName(player2);
	    int index;
	    game.reset();
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
	}
	public void showWinner(Player p) {
		Alert winner;
		if(p == null)
			winner = new Alert(Alert.AlertType.INFORMATION, " Game tied. \n Resetting the board.");
		else 
			winner = new Alert(AlertType.INFORMATION, p.getName()+" won. \n Resetting the board.");
		winner.showAndWait();
		game.reset();
		reset(null);
		//System.out.println("Resetting the board.");
	}
	public void humanMove(ActionEvent event) {
		workOutEmptyNames();
		indexAbsolute = cellNo.get(event.getSource());
		if(game.getChar(indexAbsolute-1) == ' ') {
	        game.doMove(currentPlayer.getSymbol(), indexAbsolute-1);
			try {
				draw(indexAbsolute, currentPlayer.getSymbol());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println("You played");
			//System.out.println(game);
			if(game.checkWinner(currentPlayer.getSymbol())) {
				showWinner(currentPlayer);
				return;
			}
			if(game.allPossibleMoves().size() == 0) {
				showWinner(null);
				return;
			}
				
			currentPlayer = (currentPlayer == p1) ? p2 : p1;
			
		}
	}
	public void updateNames() {
		player1 = player1Text.getText();
		player2 = player2Text.getText();
		p1.setName(player1);
		p2.setName(player2);
	}
	public void workOutEmptyNames() {
		if(player1.isEmpty()) {
			player1 = "Player1";
			p1.setName(player1);
			player1Text.setText("Player1");
			//System.out.println("Worked out p1");
		}else if(player2.isEmpty()){
			player2 = "Player2";
			p2.setName(player2);
			player2Text.setText("Player2");
			//System.out.println("Worked out p2");
		}
	}
	public void swap(ActionEvent event) {
		if(isP1X) {
			userChar.setText("O");
			aiChar.setText("X");
			userChar.setTextFill(Color.BLUE);
			aiChar.setTextFill(Color.RED);
			isP1X = false;
		}else {
			userChar.setText("X");
			aiChar.setText("O");
			userChar.setTextFill(Color.RED);
			aiChar.setTextFill(Color.BLUE);
			isP1X = true;
		}
		if(p1.getName() == player1) {
			p1.setName(player2);
			p2.setName(player1);
		}else {
			p1.setName(player1);
			p2.setName(player2);
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
		if(isP1X) {
			p1 = x_human;
			p2 = o_human;
			currentPlayer = p1;			
		}else {
			p1 = o_human;
			p2 = x_human;
			currentPlayer = p2;		
		}
		game.reset();
		//System.out.println(game);
	}
	public void goBack(ActionEvent event) {
		Stage thisStage = (Stage)cell1.getScene().getWindow();
		thisStage.close();
		//System.out.println("Bye");
	}
	public void about() {
		Alert aboutAlert = new Alert(AlertType.INFORMATION,"Minimalist TicTacToe game, Have Fun :)  \n Developed by Omkar Phansopkar. \n Visit me at http://omkarph.rf.gd ");
		aboutAlert.showAndWait();
	}
	
}
