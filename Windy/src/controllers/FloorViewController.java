package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import FXdata.Floor;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class FloorViewController implements Initializable  {

    @FXML
    private TableView<Floor> contentTable;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		configureTable();
	}
	
	private void configureTable() {
		TableColumn<Floor, Integer> floorColumn = new TableColumn<>("Piętro");
		floorColumn.setCellValueFactory(new PropertyValueFactory<>("floorNumber"));
		
		TableColumn<Floor, Integer> peopleInQueue = new TableColumn<>("Ludzie w kolejce");
		peopleInQueue.setCellValueFactory(new PropertyValueFactory<>("waitingUsers"));
		
		TableColumn<Floor, String> elevator = new TableColumn<>("Winda");
		elevator.setCellValueFactory(new PropertyValueFactory<>("album"));
		
		contentTable.getColumns().add(floorColumn);
		contentTable.getColumns().add(peopleInQueue);
		contentTable.getColumns().add(elevator);
	}

}
