package application;

import java.util.HashMap;

import FXdata.Floor;
import FXdata.FloorCollection;
import data.Elevator;
import data.User;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

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
					FloorCollection.getFloors().get(user.getStartFloor()).addWaitingUser();
					user.call();
					int time = (int) (Math.random() * 15000);
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
		FloorCollection.clear();
		for(int i=0; i <= count;i++) {
			FloorCollection.addFloor(new Floor(i));
		}
			
	}
	
	public static <T> void runTableSync(TableView<T> tab, Text people) {
		Thread thread = new Thread(() -> {
			int actualFloor=0;
			boolean doorOpend= false;
			Floor floor = FloorCollection.getFloors().get(actualFloor);
//			HashMap<String, Integer> floor = FloorCollection.getFloors();
			while(elevator != null) {
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(actualFloor != elevator.getActualFloor()) {
					//FloorCollection.getFloors().get(actualFloor).setElevator(Floor.FLOOR_EMPTY);; 
					actualFloor= elevator.getActualFloor();
					floor.getElevator().put("action",Floor.FLOOR_EMPTY);
//					floor.setElevator(Floor.FLOOR_EMPTY);
					floor = FloorCollection.getFloors().get(actualFloor); 
					floor.getElevator().put("action",Floor.ON_FLOOR);
					//floor.setElevator(Floor.ON_FLOOR);
				//	System.out.println(FloorCollection.getFloors().get(actualFloor));
					//FloorViewController.refresh();
					
					
				}
				if(elevator.isDoorOpen() != doorOpend) {
					doorOpend= !doorOpend;
					if(doorOpend) {
						floor.getElevator().put("action",Floor.DOOR_OPENED);
//						floor.setElevator(Floor.DOOR_OPENED);
						floor.clearWaitingUsers();
					}else{
						floor.getElevator().put("action",Floor.ON_FLOOR);
//						floor.setElevator(Floor.ON_FLOOR);
					}
				}
				floor.getElevator().put("users", elevator.getPassengers());
//				people.textProperty().set(String.valueOf(elevator.getPassengers()) );
				tab.refresh();
				
				
			}
			

		});
		thread.setPriority(Thread.MAX_PRIORITY);
		thread.start();
		
	}

}
