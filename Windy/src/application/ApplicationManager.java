package application;

import java.util.List;

import FXdata.Floor;
import FXdata.FloorCollection;
import controllers.FloorViewController;
import data.Elevator;
import data.User;
import javafx.scene.control.TableView;

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
	
	public static void createFloors(int count) {
		for(int i=0; i <= count;i++) {
			FloorCollection.addFloor(new Floor(i));
		}
			
	}
	
	public static void runTableSync(TableView<Floor> tab) {
		Thread thread = new Thread(() -> {
			int actualFloor=0;
			boolean doorOpend= false;
			Floor floor = FloorCollection.getFloors().get(actualFloor);
			while(elevator != null) {
				System.out.print("");
				if(actualFloor != elevator.getActualFloor()) {
					//FloorCollection.getFloors().get(actualFloor).setElevator(Floor.FLOOR_EMPTY);; 
					actualFloor= elevator.getActualFloor();
					floor.setElevator(Floor.FLOOR_EMPTY);
					floor = FloorCollection.getFloors().get(actualFloor); 
					floor.setElevator(Floor.ON_FLOOR);
				//	System.out.println(FloorCollection.getFloors().get(actualFloor));
					//FloorViewController.refresh();
					
				}
				if(elevator.isDoorOpen() != doorOpend) {
					System.out.println("Door");
					doorOpend= !doorOpend;
					if(doorOpend) {
						floor.setElevator(Floor.DOOR_OPENED);
					}else{
						floor.setElevator(Floor.ON_FLOOR);
					}
				}
				tab.refresh();
				
				
			}
			

		});
		thread.setPriority(Thread.MAX_PRIORITY);
		thread.start();
		
	}

}
