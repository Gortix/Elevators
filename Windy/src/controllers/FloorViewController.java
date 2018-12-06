package controllers;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import FXdata.Floor;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.util.Callback;

public class FloorViewController implements Initializable  {

    @FXML
    private  TableView<Floor> contentTable;
    

	public TableView<Floor> getContentTable() {
		return contentTable;
	}

	public void setContentTable(ObservableList<Floor> floors) {
		this.contentTable.setItems(floors);
	}
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		configureTable();
		
	}
	
	private void configureTable() {
		TableColumn<Floor, Integer> floorColumn = new TableColumn<>("Piętro");
		floorColumn.setCellValueFactory(new PropertyValueFactory<>("floorNumber"));
		floorColumn.setSortable(false);
		
		TableColumn<Floor, Integer> peopleInQueue = new TableColumn<>("Oczekujący");
		peopleInQueue.setCellValueFactory(new PropertyValueFactory<>("waitingUsers"));
		peopleInQueue.setSortable(false);
		
		
		TableColumn<Floor, Integer> elevator = new TableColumn<>("Winda");
		elevator.setCellValueFactory(new PropertyValueFactory<>("elevator"));
		
		
		elevator.setCellFactory(        
				new Callback<TableColumn<Floor, Integer>, TableCell<Floor, Integer>>() {
            public TableCell call(TableColumn p) {
                TableCell cell = new TableCell<Floor, HashMap<String,Integer>>() {
                    @Override
                    public void updateItem(HashMap<String,Integer> item, boolean empty) {
                        super.updateItem(item, empty);
                    	//String cl="";
                        if(item != null && item.get("action") == Floor.FLOOR_EMPTY) {
                        	setStyle("-fx-background-color: #ffffe0ff");
                        	setText("");
                        }else if(item != null && item.get("action")== Floor.ON_FLOOR) {
                        	setStyle("-fx-background-color:#e0ffffff");
                        	setText(item.get("users")+"");
                        }else if(item != null && item.get("action")== Floor.DOOR_OPENED) {
                        	setStyle("-fx-background-color:#90ee90ff");
                        	setText(item.get("users")+"");
                        }
                        
                       
                    }


                };


                return cell;
            }
        } 
        );
		
		
		
		elevator.setSortable(false);
		
		contentTable.getColumns().add(floorColumn);
		contentTable.getColumns().add(peopleInQueue);
		contentTable.getColumns().add(elevator);
		contentTable.setSelectionModel(null);
		
		
	}

}
