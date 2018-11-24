package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import FXdata.Floor;
import FXdata.FloorCollection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;

public class FloorViewController implements Initializable  {

    @FXML
    private  TableView<Floor> contentTable;
    
    
//    private FloorCollection coll;
//    public static void refresh() {
//    	contentTable.refresh();
//    }

	public TableView<Floor> getContentTable() {
		return contentTable;
	}

	public void setContentTable() {
		this.contentTable.setItems(FloorCollection.getFloors());
	}
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		//coll= new FloorCollection();
		configureTable();
//		FloorCollection.getInstance().addFloor(new Floor(0));
//		FloorCollection.getInstance().addFloor(new Floor(1));
//		FloorCollection.getInstance().addFloor(new Floor(2));
//		
//		contentTable.setItems(FloorCollection.getInstance().getFloors());
//		FloorCollection.getInstance().getFloors().get(0).setElevator(Floor.DOOR_OPENED);
		
	}
	
	private void configureTable() {
		TableColumn<Floor, Integer> floorColumn = new TableColumn<>("Piętro");
		floorColumn.setCellValueFactory(new PropertyValueFactory<>("floorNumber"));
		floorColumn.setSortable(false);
		
		TableColumn<Floor, Integer> peopleInQueue = new TableColumn<>("Oczekujący");
		peopleInQueue.setCellValueFactory(new PropertyValueFactory<>("waitingUsers"));
		peopleInQueue.setSortable(false);
		
		
		TableColumn<Floor, Integer> elevator = new TableColumn<>("Winda");
//		elevator.setCellValueFactory(x-> x.getValue().getAction());
		elevator.setCellValueFactory(new PropertyValueFactory<>("elevator"));
		
		
		elevator.setCellFactory(        
        new Callback<TableColumn<Floor, Integer>, TableCell<Floor, Integer>>() {
            public TableCell call(TableColumn p) {
                TableCell cell = new TableCell<Floor, Integer>() {
                    @Override
                    public void updateItem(Integer item, boolean empty) {
                        super.updateItem(item, empty);
                    	String cl="";
                        if(item != null && item == Floor.FLOOR_EMPTY) {
                        	setStyle("-fx-background-color: #ffffe0ff");
                        	
                        }else if(item != null && item == Floor.ON_FLOOR) {
                        	setStyle("-fx-background-color:#e0ffffff");
                        }else if(item != null && item == Floor.DOOR_OPENED) {
                        	setStyle("-fx-background-color:#90ee90ff");
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
