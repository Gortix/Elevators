package data;

enum Move {
	UP, DOWN, STOP
};

/**
 * @author pawel
 *
 */
public class Elevator {
	private int actualFloor;
	private boolean[] floorToVisit;
	private Move move;
	private boolean doorOpen;
	private int passengers;

	public Elevator(int maxFloor) {
		super();
		this.actualFloor = 0;
		this.move = Move.STOP;
		this.floorToVisit = new boolean[maxFloor+1];
		this.doorOpen = false;
		this.passengers=0;
	}
	

	public int getPassengers() {
		return passengers;
	}


	public void addPassenger() {
		this.passengers++;
	}
	
	public void substractPassenger() {
		this.passengers--;
	}



	public int getMaxFloor() {
		return floorToVisit.length -1;
	}

	public boolean isDoorOpen() {
		return doorOpen;
	}

	public int getActualFloor() {
		return actualFloor;
	}

	public void callElevator(int floor) throws InterruptedException {

		floorToVisit[floor] = true;
		checkFloor();
		if (move == Move.STOP) {
			firstCall(floor);
		}

	}

	private void firstCall(int floor) {
		if (floor > actualFloor) {
			move = Move.UP;
		} else if (floor < actualFloor) {
			move = Move.DOWN;
		}

	}


	private void checkFloor() throws InterruptedException {
		if (floorToVisit[actualFloor]) {
			Thread.sleep(1000);
			doorOpen = true;
			// Sprawdza jaki powinien być następny ruch windy[góra, dół, stop]
			checkNextStep();
			Thread.sleep(3000);
			floorToVisit[actualFloor]= false;
			doorOpen = false;
			

		}

	}

	private void checkNextStep() {
		boolean above=false;
		boolean below=false;
		int i= 0;
		while(i<actualFloor && !below) {
			if(floorToVisit[i])
				below=true;
			i++;
		}
		i=actualFloor+1;
		while(i<floorToVisit.length && !above) {
			if(floorToVisit[i])
				above=true;
			i++;
		}
		
		if(!above && !below) {
			move= Move.STOP;
		}else if(move == Move.UP && !above) {
			move = Move.DOWN;
			
		}else if(move == Move.DOWN && !below) {
			move = Move.UP; 
		}
		//System.out.println(move);
		
	}

	public void runElevator() throws InterruptedException {
		while (true) {
			Thread.sleep(10);
			if (move != Move.STOP) {
				if (move == Move.DOWN) {
					actualFloor--;
				} else {
					actualFloor++;
				}
				System.out.println("floor: " + actualFloor);
				//System.out.println(floorToVisit);
				checkFloor();
				Thread.sleep(1000);
			}
		}
		// System.out.println("floor: "+actualFloor);

	}


}
