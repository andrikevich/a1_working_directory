package by.dazer.Filter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeFilter {

    private Date calendar = new GregorianCalendar().getTime();

    /**
     * Filter for chcking if today number of a week equals to numberOfWeekDay of method isWeekDayNumber
     * <br>
     * <br> <b> numberOfWeekDay </b> == 1 for Monday
     * <br>....
     * <br> <b> numberOfWeekDay </b> == 7 for Sunday
     */

    public boolean isWeekDayNumber (int numberOfWeekDay){
        SimpleDateFormat sdf = new SimpleDateFormat("u");
        return numberOfWeekDay == (Integer.valueOf(sdf.format(calendar)));
    }




}
