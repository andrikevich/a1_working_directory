package by.a1.andrikevich.runner;


import by.a1.andrikevich.checker.Alarm;

public class EmptyFldrCheckebaleRunner implements Runnable {

	private String folderName;
	
	

	public EmptyFldrCheckebaleRunner( String folderName) {
		this.folderName=folderName;
	}
	
	
	@Override
	public void run() {
		while(true) {

				new Alarm("The monitored folder is EMPTY: " + folderName).doAlarm();
			
		}

	}

}
