package data;

import java.util.ArrayList;

enum Move {
	UP, DOWN, STOP
};

public class Elevator {
	private byte actualFloor;
	private byte maxFloor;
	private ArrayList<Integer> floorToVisit;
	private Move move;

	public Elevator(byte maxFloor) {
		super();
		this.maxFloor = maxFloor;
		this.actualFloor = 0;
		move = Move.STOP;
		floorToVisit = new ArrayList<>(maxFloor);
	}

	public byte getMaxFloor() {
		return maxFloor;
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
			//Sprawdza jaki powinien być następny ruch windy[góra, dół, stop]
			flowController();
			Thread.sleep(3000);
			floorToVisit.remove(actualFloor);
		}

	}

	private void flowController() {
		int maxIndex = floorToVisit.size() - 1;
		if (maxIndex != 0) {
			// sprawdzam czy czeka piętro powyżej lub poniżej w kolejce
			int visitingFloorIndex = floorToVisit.indexOf(actualFloor);
			boolean checkDown = ((visitingFloorIndex == 0) && (move == Move.DOWN));
			boolean checkUp = ((visitingFloorIndex == maxIndex) && (move == Move.UP));
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
			checkFloor();
			Thread.sleep(1000);
		}
	}

}
