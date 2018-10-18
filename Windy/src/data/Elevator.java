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

	public void callElevator(int floor) {
		if (!floorToVisit.contains(floor)) {
			floorToVisit.add(floor);
			floorToVisit.sort((o1, o2) -> o1 - o2);
			if (move == Move.STOP) {
				runElevator();
			}
		}

	}

	private void runElevator() {
		// if (floorToVisit.size() == 1) {
		if (floorToVisit.get(0) > actualFloor) {
			move = Move.UP;
		} else {
			move = Move.DOWN;
		}
		// } else {
//			int min = floorToVisit.first();
//			int max = floorToVisit.last();
////		}

	}

	private void stopElevator() {
		move = Move.STOP;
	}

	private void checkFloor() {
		if (floorToVisit.contains(actualFloor)) {
			try {
				;
				// sprawdzam czy czeka piętro powyżej w kolejce
				Thread.sleep(3000);
				int visitFloorIndex = floorToVisit.indexOf(actualFloor);
				if (visitFloorIndex == floorToVisit.size() - 1) {
					changeDirection();
				}
				floorToVisit.remove(actualFloor);
				if (floorToVisit.size() > 0) {
					changeFloor();
				} else {
					stopElevator();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			changeFloor();
		}

	}

	private void changeDirection() {
		if (move == Move.DOWN) {
			move = move.UP;
		} else {
			move = move.DOWN;
		}
	}

	private void changeFloor() {
		if ((move == Move.DOWN && actualFloor - 1 < 0) || (move == Move.UP && actualFloor > maxFloor + 1)) {
			changeDirection();
		}
		if (move == Move.DOWN) {
			actualFloor--;
		} else {
			actualFloor++;
		}
	}

}
