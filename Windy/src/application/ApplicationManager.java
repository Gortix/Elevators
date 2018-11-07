package application;

import java.util.List;

import data.Elevator;
import data.User;

public class ApplicationManager {
	private static Elevator elevator;
	private static ApplicationStatus status;
	private static Thread[] users;

	public static ApplicationStatus getStatus() {
		return status;
	}

	public static void setStatus(ApplicationStatus status) {
		ApplicationManager.status = status;
	}
	
	public static void createUsers(int numberOfUsers) {
		if (users == null) {
			users = new Thread[numberOfUsers];
		}

		for (int i = 0; i < numberOfUsers; i++) {
			users[i] = createOneUser();
		}
	}

	private static Thread createOneUser() {
		Thread thread = new Thread(() -> {
			try {
				while (status == ApplicationStatus.START) {
					User user = new User(elevator);
					int time = (int) (Math.random() * 15000);
					System.out.println("TIME: " + time);
					Thread.sleep(time);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		thread.setPriority(Thread.MIN_PRIORITY);
		thread.start();
		return thread;
	}

	public static void createElevator(int numberOfFloors) {
		if (elevator == null) {
			elevator = new Elevator(numberOfFloors);
			status = ApplicationStatus.START;
			Thread thread = new Thread(() -> {

				try {
					elevator.runElevator();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			});
			thread.setPriority(Thread.MAX_PRIORITY);
			thread.start();
		}
	}

}
