package by.a1.andrikevich.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TimeDateHandler {



    public String getDateWithOffset (int offset){
        Calendar currentDate = new GregorianCalendar();
        currentDate.add(Calendar.DATE, offset);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        return dateFormat.format(currentDate.getTime());
    }

    public String getCurrentDate(){
        return this.getDateWithOffset(0);
    }

}
