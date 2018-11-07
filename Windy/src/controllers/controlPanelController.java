package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.ApplicationManager;
import application.ApplicationStatus;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class controlPanelController implements Initializable {

	@FXML
	private TextField floorQuantity;
	
	@FXML
	private TextField numberOfUsers;

	@FXML
	private Button startButton;

	@FXML
	private Button stopButton;
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("initialized");

		floorQuantity.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					floorQuantity.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}

		});
		
		numberOfUsers.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					numberOfUsers.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}

		});
	};

	@FXML
	public void runApplication(ActionEvent event) {

		int floors = Integer.valueOf(floorQuantity.getText());
		ApplicationManager.createElevator(floors);
		
		
		int users = Integer.valueOf(numberOfUsers.getText());
		ApplicationManager.createUsers(users);
		ApplicationManager.setStatus(ApplicationStatus.START);

	}

	public void stopApplication(ActionEvent event) {

		ApplicationManager.setStatus(ApplicationStatus.STOP);
		System.out.println(ApplicationManager.getStatus());

	}

}
