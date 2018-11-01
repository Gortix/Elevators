import java.util.ArrayList;
import java.util.List;

import data.Elevator;
import data.User;

public class Main {
	//private static Elevator elevator;
	
	public static void main(String[] args) throws InterruptedException {
		Elevator elevator= new Elevator(13);
		Thread thread = new Thread(() -> {

			try {
				elevator.runElevator();
				System.out.print("winda");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});
		thread.setPriority(Thread.MAX_PRIORITY);
		thread.start();
		
//		elevator.callElevator(10);
//		elevator.callElevator(11);
//		Thread.sleep(1000);
//		elevator.callElevator(1);
//		Thread.sleep(1000);
//		elevator.callElevator(0);
//		Thread.sleep(1000);
//		elevator.callElevator(4);
		//
		User user[]= new User[5];
		Thread userThread0 = new Thread(() -> {
			System.out.print("user");
			try {
				 user[0]= new User(elevator);
				 user[3]= new User(elevator);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		//thread.setPriority(Thread.MIN_PRIORITY);
		Thread userThread1 = new Thread(() -> {
			System.out.print("");
			try {
				user[1]= new User(elevator);
				user[4]= new User(elevator);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		//thread.setPriority(Thread.MIN_PRIORITY);
		Thread userThread2 = new Thread(() -> {
			System.out.print("");
			try {
				user[2]= new User(elevator);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	//	userThread0.setPriority(Thread.MIN_PRIORITY);
		
		userThread0.start();
		userThread2.start();
		userThread1.start();
		


		  
//ABY Z USEREM DZIAŁAŁO PRAWIDŁOWO STWORZYĆ WĄTKI DO NICH. 
//MOŻE NOWY WĄTEK W USERZE?
		  
//		  Byte b= 3;
		
		/////////////////////////////
		///SPRAWDZA PRZY WEZWANIU WINDY CZY ZMIENIĆ KIERUNEK !!!!!!!!!!!!!!!!!!!!!
		////////////////////////////////////
		//Thread user1= createUser();
		//Thread user2= createUser();
		//Thread user3= createUser();
		/////////////////////////////
		///SPRAWDZA PRZY WEZWANIU WINDY CZY ZMIENIĆ KIERUNEK !!!!!!!!!!!!!!!!!!!!!
		////////////////////////////////////
			
		//	User user4= new User(elevator);
		//	User user5= new User(elevator);
//		elevator.callElevator(5);
//		elevator.callElevator(10);
//		elevator.callElevator(8);
//		Thread.currentThread().sleep(10000);
//		elevator.callElevator(2);
//		Thread.currentThread().sleep(2000);
//		elevator.callElevator(2);
//		Thread.currentThread().sleep(2000);
//		elevator.callElevator(12);
//		Thread.currentThread().sleep(2000);
//		elevator.callElevator(1);
//		Thread.currentThread().sleep(2000);
//		elevator.callElevator(13);

//		List<Integer> list= new ArrayList<>();
//		byte i= 5;
//		list.add(5);
//		System.out.println(list.contains(i));
	//	User user= new User(elevator);

	}
//	
//	public static Thread createUser() {
//		Thread thread = new Thread(() -> {
//			System.out.print("");
//			User user= new User(elevator);
//		});
//		//thread.setPriority(Thread.MIN_PRIORITY);
//		thread.start();
//		
//		return thread;
//	}

}
