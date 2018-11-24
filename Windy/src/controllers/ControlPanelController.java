package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import FXdata.FloorCollection;
import application.ApplicationManager;
import application.ApplicationStatus;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ControlPanelController implements Initializable {

	@FXML
	private TextField floorQuantity;
	
	@FXML
	private TextField numberOfUsers;

	@FXML
	private Button startButton;

	@FXML
	private Button stopButton;
	


	public TextField getFloorQuantity() {
		return floorQuantity;
	}

	public TextField getNumberOfUsers() {
		return numberOfUsers;
	}

	public Button getStartButton() {
		return startButton;
	}

	public Button getStopButton() {
		return stopButton;
	}

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("initialized control panel");
		configureFields();
		
	};


	
	private void configureFields() {
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
	}

}
