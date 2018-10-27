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
	private int maxFloor;
	private List<Integer> floorToVisit;
	private Move move;
	private boolean doorOpen;

	public Elevator(byte maxFloor) {
		super();
		this.maxFloor = maxFloor;
		this.actualFloor = 0;
		this.move = Move.STOP;
		this.floorToVisit = new ArrayList<>(maxFloor);
		this.doorOpen = false;
	}

	public int getMaxFloor() {
		return maxFloor;
	}

	public boolean isDoorOpen() {
		return doorOpen;
	}

	public int getActualFloor() {
		return actualFloor;
	}

	public void callElevator(int floor) {
		if (!floorToVisit.contains(floor)) {
			floorToVisit.add(floor);
			floorToVisit.sort((o1, o2) -> o1 - o2);
			if (move == Move.STOP) {
				firstCall();
			}
		}

	}

	private void firstCall() {
		if (floorToVisit.get(0) > actualFloor) {
			move = Move.UP;
		} else {
			move = Move.DOWN;
		}

	}

	private void lastCall() {
		move = Move.STOP;
	}

	private void changeDirection() {
		if (move == Move.DOWN) {
			move = Move.UP;
		} else {
			move = Move.DOWN;
		}
	}

	private void checkFloor() throws InterruptedException {
		if (floorToVisit.contains(actualFloor)) {
			doorOpen = true;
			// Sprawdza jaki powinien być następny ruch windy[góra, dół, stop]
			flowController();
			Thread.sleep(3000);

		}
		if (doorOpen) {

			floorToVisit.remove(floorToVisit.indexOf(actualFloor));
			doorOpen = false;
		}

	}

	private void flowController() {
		int maxIndex = floorToVisit.size() - 1;
		if (maxIndex != 0) {
			// sprawdzam czy czeka piętro powyżej lub poniżej w kolejce
			int visitingFloorIndex = floorToVisit.indexOf(actualFloor);
			boolean checkDown = ((visitingFloorIndex == 0) && (move == Move.DOWN));
			boolean checkUp = ((visitingFloorIndex == maxIndex) && (move == Move.UP));
		//	boolean maxOrMinFoolr= actualFloor <= 0 || actualFloor>= maxFloor;
			if (checkUp || checkDown) {
				changeDirection();
			}
		} else {
			// Jeżeli to ostatni element w kolejce, czyli index == 0, to zatrzymaj windę
			lastCall();
		}
	}

	public void runElevator() throws InterruptedException {

		if (move != Move.STOP) {
			if (move == Move.DOWN) {
				actualFloor--;
			} else {
				actualFloor++;
			}
			System.out.println("floor: " + actualFloor);
			System.out.println(floorToVisit);
			checkFloor();
			Thread.sleep(1000);
		}
		// System.out.println("floor: "+actualFloor);

	}

}
