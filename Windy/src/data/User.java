package data;

public class User {
	private byte startFloor;
	private byte endFloor;
	private byte maxFloor;
	private boolean inside;
	private Elevator elevator;

	public User(Elevator elev) {
		super();
		this.elevator = elev;
		this.maxFloor = elev.getMaxFloor();
		this.startFloor = (byte) Math.round(Math.random() * maxFloor);
		this.endFloor = (byte) Math.round(Math.random() * maxFloor);
		this.inside = false;

		elev.callElevator(startFloor);

	}

	private void call() {
		elevator.callElevator(startFloor);
		//wezwanie windy gdy na zewnatrz
		while (!inside) {
			waiting(startFloor);
		}
		//wybranie piętradoceowego
		while (inside) {
			waiting(endFloor);
		}
	}

	private void waiting(byte floor) {
		if (elevator.isDoorOpen() && (elevator.getActualFloor() == floor)) {
			inside = !inside;
			if (inside)
				elevator.callElevator(endFloor);
		}

	}

}
