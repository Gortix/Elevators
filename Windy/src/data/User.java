package data;

public class User {
	private int startFloor;
	private int endFloor;
	private int maxFloor;
	private boolean inside;
	private Elevator elevator;

	public User(Elevator elev) {

		System.out.println("USER "+elev);
		this.elevator = elev;
		this.maxFloor = elev.getMaxFloor();
		this.startFloor = (int) Math.round(Math.random() * maxFloor);
		this.endFloor = (int) Math.round(Math.random() * maxFloor);
		this.inside = false;
		System.out.println(startFloor+"\n"+endFloor);
		call();
		//elev.call(startFloor);

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

	private void waiting(int floor) {

		System.out.print("");
		if (elevator.isDoorOpen() && (elevator.getActualFloor() == floor)) {
			System.out.println("jestem");
			inside = !inside;
			if (inside)
				elevator.callElevator(endFloor);
		}

	}

}
