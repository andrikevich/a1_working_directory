package by.a1.andrikevich.test;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ToDelete {

	public static void main(String[] args) {
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DATE, 1);
		SimpleDateFormat formater = new SimpleDateFormat("yyyy.MM.dd");
		
	
		System.out.println(formater.format(date));
		System.out.println(formater.format(calendar.getTime()));
		
		A temp = new B();
	
		System.out.println("num = " + temp.num);
	}
	

}


class A {
	
	public int num = 1;
}

class B extends A {
	public int num = 1000;
	
}