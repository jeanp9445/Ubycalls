package com.example.ubycalls;

import java.util.Calendar;

public class TestMain {

    public boolean isMismaSemana(Calendar selectedDate, Calendar currentDate) {
        Calendar startOfWeek = (Calendar) currentDate.clone();
        startOfWeek.set(Calendar.DAY_OF_WEEK, currentDate.getFirstDayOfWeek());

        Calendar endOfWeek = (Calendar) startOfWeek.clone();
        endOfWeek.add(Calendar.DAY_OF_WEEK, 6);

        return !selectedDate.before(startOfWeek) && !selectedDate.after(endOfWeek);
    }
}
