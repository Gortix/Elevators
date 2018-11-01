package data;

import java.util.ArrayList;
import java.util.List;

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

	public Elevator(int maxFloor) {
		super();
		this.actualFloor = 0;
		this.move = Move.STOP;
		this.floorToVisit = new boolean[maxFloor];
		this.doorOpen = false;
	}

	public int getMaxFloor() {
		return floorToVisit.length;
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
		} else {
			move = Move.DOWN;
		}

	}


	private void checkFloor() throws InterruptedException {
		if (floorToVisit[actualFloor]) {
			doorOpen = true;
			// Sprawdza jaki powinien być następny ruch windy[góra, dół, stop]
			checkNextStep();
			Thread.sleep(3000);

		}
		if (doorOpen) {
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
			System.out.print("");
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

	public void startElevator() {
		Thread thread = new Thread(() -> {

			try {
				runElevator();
				System.out.print("");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});
		thread.setPriority(Thread.MAX_PRIORITY);
		thread.start();

	}

}
