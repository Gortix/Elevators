package controllers;

import java.net.URL;
import java.util.ResourceBundle;


import FXdata.FloorCollection;
import application.ApplicationManager;
import application.ApplicationStatus;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.Text;

public class MainPanelController implements Initializable {
	
	 @FXML
	 private ControlPanelController controlPanelController;
	 
	 @FXML
	 private FloorViewController floorViewController;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		//controlPanelController.getStartButton().addEventHandler(, eventHandler);
		//floorViewController.setContentTable();
		configureButtons();
	}
	
	@FXML
	public void configureButtons() {
		ToggleButton controlButton = controlPanelController.getControlButton();
		TextField floorQuantity = controlPanelController.getFloorQuantity();
		TextField numberOfUsers = controlPanelController.getNumberOfUsers();
		Text infoText = controlPanelController.getInfoText();
		
		
		controlButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(controlButton.isSelected()){
					FloorCollection.clear();
					int floors = Integer.valueOf(floorQuantity.getText());
					ApplicationManager.createElevator(floors);
					ApplicationManager.createFloors(floors);
					floorViewController.setContentTable(FloorCollection.getFloors());
					ApplicationManager.runTableSync(floorViewController.getContentTable());
					
					
					int users = Integer.valueOf(numberOfUsers.getText());
					ApplicationManager.createUsers(users);
					ApplicationManager.setStatus(ApplicationStatus.START);
					controlButton.setText("Stop");
					infoText.setText("");
				}else {
					ApplicationManager.setStatus(ApplicationStatus.STOP);
					
					System.out.println(ApplicationManager.getStatus());
					controlButton.setText("Start");
					infoText.setText("Aplikacja zatrzyma się po zakończeniu podróży przez wszystkich użytkowników");
				}
				
			}
			
			
		});
		


	}


	

}
