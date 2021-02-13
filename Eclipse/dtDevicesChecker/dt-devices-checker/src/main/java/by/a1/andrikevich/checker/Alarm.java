package by.a1.andrikevich.checker;

import java.awt.Toolkit;
import java.util.logging.Logger;

public class Alarm {
	Logger logger = Logger.getLogger(Alarm.class.getName());

	private String message;

	public Alarm(String message) {
		this.message = message;
	}

	public void doAlarm() {
		int j = 0;
		while (j < 10) {
			logger.severe(">>> A L A R M ! ! ! " + message);
			for (int i = 0; i < 100; i++) {
				Toolkit.getDefaultToolkit().beep();
				System.out.print("\007");

			}
			try {
				Thread.sleep(1000);
				j++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}

}
