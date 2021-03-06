package FXdata;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FloorCollection {
	private static ObservableList<Floor> floorList;
	private static final FloorCollection instance= new FloorCollection();

	public static ObservableList<Floor> getFloors() {
		return floorList;
	}

	public static void  addFloor(Floor floor) {
		floorList.add(floor);
	}

	
	public static void clear() {
		floorList.clear();
	}

	private FloorCollection() {
		floorList = FXCollections.observableArrayList();
	}
	
	
	
	
}
