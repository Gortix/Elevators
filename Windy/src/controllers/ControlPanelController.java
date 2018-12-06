package controllers;


import java.net.URL;
import java.util.ResourceBundle;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.Text;

public class ControlPanelController implements Initializable {

	@FXML
	private TextField floorQuantity;
	
	@FXML
	private TextField numberOfUsers;

	@FXML
	private ToggleButton controlButton;
	
	@FXML
	private Text infoText;

	
	


	public TextField getFloorQuantity() {
		return floorQuantity;
	}

	public TextField getNumberOfUsers() {
		return numberOfUsers;
	}

	public ToggleButton getControlButton() {
		return controlButton;
	}
	
	public Text getInfoText() {
		return infoText;
	}





	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Control panel initialized");
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
