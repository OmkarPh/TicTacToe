package rf.gd.omkarph.TicTacToe;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.Window;

public class GameController {
	@FXML
	private RadioButton aiMode, humanMode;
	boolean playWithAi;
	@FXML
	Label welcomeLabel;
	
	public void startGame(ActionEvent event) {
		Stage thisStage = (Stage) ((Button)event.getSource()).getScene().getWindow();
		try {
			if(aiMode.isSelected()) {
				playWithAi = true;
				// Play game against AI
				//System.out.println("Playing against AI");
				
				Stage newStage =new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("playVsAI.fxml"));
				Scene scene = new Scene(root,640,380);
				newStage.setTitle("Playing V/S AI");
				newStage.setScene(scene);
				newStage.getIcons().add(new Image(GameController.class.getResourceAsStream("icon.png")));
				thisStage.hide();
				newStage.showAndWait();
				thisStage.show();
			}else {
				// Play game against another Human
				//System.out.println("Playing against Human");
				
				Stage newStage =new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("playVsHuman.fxml"));
				Scene scene = new Scene(root,660,400);
				newStage.setTitle("Playing V/S Player");
				newStage.setScene(scene);
				newStage.getIcons().add(new Image(GameController.class.getResourceAsStream("icon.png")));
				thisStage.hide();
				newStage.showAndWait();
				thisStage.show();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void exitApp() {
		System.exit(1);
	}
	public void about() {
		Alert aboutAlert = new Alert(AlertType.INFORMATION,"Developed by Omkar Phansopkar. \n Minimalist TicTacToe game, Have Fun :)");
		aboutAlert.showAndWait();
	}
}
