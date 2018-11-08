package FXdata;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FloorCollection {
	private ObservableList<Floor> floorList;

	public ObservableList<Floor> getSongList() {
		return floorList;
	}

	public void addSong(Floor floor) {
		floorList.add(floor);
	}
	
	public void clear() {
		floorList.clear();
	}

	public FloorCollection() {
		this.floorList = FXCollections.observableArrayList();
	}
}
