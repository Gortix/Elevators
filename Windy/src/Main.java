import java.util.ArrayList;
import java.util.List;

import data.Elevator;
import data.User;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Elevator elevator= new Elevator((byte)13);
		
		Thread thread = new Thread(new Runnable() 
		{

		     public void run() {
		    	
		         while (true)
		             {try {
						elevator.runElevator();
						 System.out.print("");
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}}
		     }
		   
		});

		  thread.start();
		  
//ABY Z USEREM DZIAŁAŁO PRAWIDŁOWO STWORZYĆ WĄTKI DO NICH. 
//MOŻE NOWY WĄTEK W USERZE?
		  
//		  Byte b= 3;
//			User user1= new User(elevator);
//			User user2= new User(elevator);
//			User user3= new User(elevator);
//			User user4= new User(elevator);
//			User user5= new User(elevator);
		elevator.callElevator( 5);
		elevator.callElevator(10);
		elevator.callElevator(8);
		Thread.currentThread().sleep(10000);
		elevator.callElevator(2);
		Thread.currentThread().sleep(2000);
		elevator.callElevator(2);
		Thread.currentThread().sleep(2000);
		elevator.callElevator(12);
		Thread.currentThread().sleep(2000);
		elevator.callElevator(1);
		Thread.currentThread().sleep(2000);
		elevator.callElevator(13);

//		List<Integer> list= new ArrayList<>();
//		byte i= 5;
//		list.add(5);
//		System.out.println(list.contains(i));
	//	User user= new User(elevator);

	}

}
