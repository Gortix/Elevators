package application;


import FXdata.Floor;
import FXdata.FloorCollection;
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

			users = new Thread[numberOfUsers];


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
	
	public static void createFloors(int count) {
		FloorCollection.clear();
		for(int i=0; i <= count;i++) {
			FloorCollection.addFloor(new Floor(i));
		}
			
	}
	
	public static <T> void runTableSync(TableView<T> tab) {
		Thread thread = new Thread(() -> {
			int actualFloor=1;
			boolean doorOpend= false;
			Floor floor = FloorCollection.getFloors().get(actualFloor);
			while(elevator != null) {
				if(actualFloor != elevator.getActualFloor()) {
					actualFloor= elevator.getActualFloor();
					floor.getElevator().put("action",Floor.FLOOR_EMPTY);
					floor = FloorCollection.getFloors().get(actualFloor); 
					floor.getElevator().put("action",Floor.ON_FLOOR);

					
					
				}
				if(elevator.isDoorOpen() != doorOpend) {
					doorOpend= !doorOpend;
					if(doorOpend) {
						floor.getElevator().put("action",Floor.DOOR_OPENED);
						floor.clearWaitingUsers();
					}else{
						floor.getElevator().put("action",Floor.ON_FLOOR);
					}
				}
				floor.getElevator().put("users", elevator.getPassengers());
				tab.refresh();
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			

		});
		thread.setPriority(Thread.MAX_PRIORITY);
		thread.start();
		
	}

}
