package models;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Date {
    private GregorianCalendar dateMin;
    private GregorianCalendar dateNow;
    private GregorianCalendar dateSelected;
    private static final int MONTHS = 12;

    public Date() {
        dateMin = new GregorianCalendar(1871, Calendar.JANUARY, 1);
        dateNow = new GregorianCalendar();
    }

    public int getYearMin() {
        return dateMin.get(Calendar.YEAR);
    }

    public int getYearMax() {
        return dateNow.get(Calendar.YEAR);
    }

    public int getMonths() {
        return MONTHS;
    }

    public void dateSelected(int year, int month, int day) {
        dateSelected = new GregorianCalendar(year, month, day);
    }

    public int getDaysInMonth() {
        return dateSelected.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public GregorianCalendar getDateSelected() {
        return dateSelected;
    }

}
