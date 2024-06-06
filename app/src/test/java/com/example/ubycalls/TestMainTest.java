package com.example.ubycalls;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestMainTest {

    private TestMain tm;

    @Before
    public void setUp() {
        tm = new TestMain();
    }

    @Test
    public void testIsSameWeek_SelectedDateInSameWeekAsCurrentDate() {
        Calendar currentDate = Calendar.getInstance();
        Calendar selectedDate = (Calendar) currentDate.clone();
        selectedDate.add(Calendar.DAY_OF_WEEK, -3);

        assertTrue(tm.isMismaSemana(selectedDate, currentDate));
    }

    @Test
    public void testIsSameWeek_SelectedDateBeforeCurrentWeek() {
        Calendar selectedDate = Calendar.getInstance();
        Calendar currentDate = (Calendar) selectedDate.clone();
        selectedDate.add(Calendar.DAY_OF_WEEK, -7);

        assertFalse(tm.isMismaSemana(selectedDate, currentDate));
    }

    @Test
    public void testIsSameWeek_SelectedDateAfterCurrentWeek() {
        Calendar selectedDate = Calendar.getInstance();
        Calendar currentDate = (Calendar) selectedDate.clone();
        selectedDate.add(Calendar.DAY_OF_WEEK, 7);

        assertFalse(tm.isMismaSemana(selectedDate, currentDate));
    }
}

