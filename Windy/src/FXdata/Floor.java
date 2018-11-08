package FXdata;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;

enum Action{
	NONE(Color.LIGHTYELLOW), ON_FLOOR(Color.LIGHTCYAN), DOOR_OPEND(Color.LIGHTGREEN);
	
	private Color color;
	
	Action(Color color){
		this.color=color;
	}
	
	public Color getActionColor() {
		return color;
	}
}

public class Floor {
	private IntegerProperty waitingUsers;
	private IntegerProperty floorNumber;
	private StringProperty elevator;
	private Action action;
	
	public Floor(int floorNumber) {
		super();
		this.waitingUsers = new SimpleIntegerProperty();
		this.floorNumber = new SimpleIntegerProperty();
		this.action = Action.NONE;
		
		this.floorNumber.setValue(floorNumber);
	}

	public Integer getWaitingUsers() {
		return waitingUsers.getValue();
	}

	public void setWaitingUsers(int waitingUsers) {
		this.waitingUsers.setValue(waitingUsers);;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public Integer getFloorNumber() {
		return floorNumber.getValue();
	}
		
	
}
