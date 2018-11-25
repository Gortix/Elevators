package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import FXdata.Floor;
import FXdata.FloorCollection;
import application.ApplicationManager;
import application.ApplicationStatus;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
		Button startButton = controlPanelController.getStartButton();
		TextField floorQuantity = controlPanelController.getFloorQuantity();
		TextField numberOfUsers = controlPanelController.getNumberOfUsers();
		Text userInElevator = controlPanelController.getUserInElevator();
		Button stopButton = controlPanelController.getStopButton();
		
		
		startButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				int floors = Integer.valueOf(floorQuantity.getText());
				ApplicationManager.createElevator(floors);
				ApplicationManager.createFloors(floors);
				floorViewController.setContentTable(FloorCollection.getFloors());
				ApplicationManager.runTableSync(floorViewController.getContentTable(),userInElevator);
				
				
				int users = Integer.valueOf(numberOfUsers.getText());
				ApplicationManager.createUsers(users);
				ApplicationManager.setStatus(ApplicationStatus.START);
				
			}
			
			
		});
		
		stopButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ApplicationManager.setStatus(ApplicationStatus.STOP);
				System.out.println(ApplicationManager.getStatus());
				
			}
		});

	}


	

}
