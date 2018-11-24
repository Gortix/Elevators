package FXdata;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;

//enum Action{
//	NONE(Color.LIGHTYELLOW), ON_FLOOR(Color.LIGHTCYAN), DOOR_OPEND(Color.LIGHTGREEN);
//	
//	private Color color;
//	
//	Action(Color color){
//		this.color=color;
//	}
//	
//	public Color getActionColor() {
//		return color;
//	}
//}

public class Floor {
	public final static Integer FLOOR_EMPTY=0;
	public final static Integer ON_FLOOR=1;
	public final static Integer DOOR_OPENED=2;
	
	private IntegerProperty waitingUsers;
	private IntegerProperty floorNumber;
	private IntegerProperty elevator;

	
	public Floor(int floorNumber) {
		super();
		this.waitingUsers = new SimpleIntegerProperty();
		this.floorNumber = new SimpleIntegerProperty(floorNumber);
		elevator= new SimpleIntegerProperty(FLOOR_EMPTY);
		//this.action = new SimpleIntegerProperty(FLOOR_EMPTY);
		
	}

	public Integer getWaitingUsers() {
		return waitingUsers.getValue();
	}

	public void setWaitingUsers(int waitingUsers) {
		this.waitingUsers.setValue(waitingUsers);;
	}

//	public ObjectProperty<Action>  getAction() {
//		return action;
//	}
//
//	public void setAction(Action action) {
//		
//		this.action.setValue(action);;
//	}
	
	public void setElevator(Integer value) {
		this.elevator.set(value);
	}
	
	public Integer getElevator() {
		return this.elevator.getValue();
	}

	public Integer getFloorNumber() {
		return floorNumber.getValue();
	}
		
	
}
