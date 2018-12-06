package data;

public class User {
	private int startFloor;
	private int endFloor;
	private int maxFloor;
	private boolean inside;
	private Elevator elevator;

	public User(Elevator elev) throws InterruptedException {

		this.elevator = elev;
		this.maxFloor = elev.getMaxFloor();
		this.startFloor = (int) Math.round(Math.random() * maxFloor);
		this.endFloor = (int) Math.round(Math.random() * maxFloor);
		while(endFloor == startFloor) {
			this.endFloor = (int) Math.round(Math.random() * maxFloor);
		}
		this.inside = false;
		//System.out.println(startFloor+"\n"+endFloor);
		//call();
		//elev.call(startFloor);

	}
	
	

	public int getStartFloor() {
		return startFloor;
	}



	public void call() throws InterruptedException {

		elevator.callElevator(startFloor);
	//	System.out.println("wezwana");
		//wezwanie windy gdy na zewnatrz
		while (!inside) {
			waiting(startFloor);
		}
//		System.out.println("jestem");
		//wybranie piętra docelowego
		while (inside) {
			waiting(endFloor);
		}
	//	System.out.println("wysiadam");
	}

	private void waiting(int floor) throws InterruptedException {
		//System.out.print("a");
		
		if (elevator.isDoorOpen() && (elevator.getActualFloor() == floor)) {
			inside = !inside;
			if (inside) {
				elevator.addPassenger();
				elevator.callElevator(endFloor);
			}else {
				elevator.substractPassenger();
			}
		}
		Thread.sleep(300);


	}

}
