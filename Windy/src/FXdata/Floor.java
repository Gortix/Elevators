package FXdata;

import java.util.HashMap;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;



public class Floor {
	public final static Integer FLOOR_EMPTY=0;
	public final static Integer ON_FLOOR=1;
	public final static Integer DOOR_OPENED=2;
	
	private IntegerProperty waitingUsers;
	private IntegerProperty floorNumber;
//	private IntegerProperty elevator;
	private ObjectProperty<HashMap<String, Integer>> elevator;

	
	public Floor(int floorNumber) {
		super();
		this.waitingUsers = new SimpleIntegerProperty();
		this.floorNumber = new SimpleIntegerProperty(floorNumber);
		HashMap<String, Integer> elevatorMap= new HashMap<>();
		elevatorMap.put("action", FLOOR_EMPTY);
		elevatorMap.put("users", 0);
		elevator= new SimpleObjectProperty<>(elevatorMap);
		
	}

	public Integer getWaitingUsers() {
		return waitingUsers.getValue();
	}

	public void addWaitingUser() {
		Integer users= waitingUsers.get();
		this.waitingUsers.setValue(users +1);
	}
	
	public void clearWaitingUsers() {
		this.waitingUsers.setValue(0);
	}

	
	public void setElevator(HashMap<String, Integer> value) {
		this.elevator.set(value);
	}
	
	
	public HashMap<String, Integer> getElevator() {
		 
		return this.elevator.getValue();
	}

	public Integer getFloorNumber() {
		return floorNumber.getValue();
	}
		
	
}
