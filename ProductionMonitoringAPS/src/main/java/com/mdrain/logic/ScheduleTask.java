package com.mdrain.logic;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class ScheduleTask {

	
	public static void main(){


		Timer timer = new Timer();
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_WEEK, 13);
		date.set(Calendar.MINUTE, 56);
		date.set(Calendar.SECOND, 0);
		date.set(Calendar.MILLISECOND, 0);
		
		timer.schedule(
				new TimerTask() {

					@Override
					public void run() {
						System.out.println("run");
						
					}
					
				}, date.getTime(),
				1000
				);
		
	}
}
