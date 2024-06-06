package com.example.ubycalls;

import android.app.TimePickerDialog;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.TimePicker;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView tvTimeIn, tvTimeOut;
    private Button btnTimeIn, btnTimeOut;
    private CalendarView calendarView;

    private SQLiteDatabase database;
    private static final String DATABASE_NAME = "TimeTrackingDB";
    private static final String TABLE_NAME = "TimeRecords";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTimeIn = findViewById(R.id.tvTimeIn);
        tvTimeOut = findViewById(R.id.tvTimeOut);
        btnTimeIn = findViewById(R.id.btnTimeIn);
        btnTimeOut = findViewById(R.id.btnTimeOut);
        calendarView = findViewById(R.id.calendarView);

        SQLiteOpenHelper helper = new SQLiteOpenHelper(this, DATABASE_NAME, null, 1) {
            @Override
            public void onCreate(SQLiteDatabase db) {
                db.execSQL("CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, date TEXT, time_in TEXT, time_out TEXT)");
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
                onCreate(db);
            }
        };

        database = helper.getWritableDatabase();

        btnTimeIn.setOnClickListener(v -> pickTime(tvTimeIn, "time_in"));
        btnTimeOut.setOnClickListener(v -> pickTime(tvTimeOut, "time_out"));

        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            Calendar selectedDate = Calendar.getInstance();
            selectedDate.set(year, month, dayOfMonth);
            Calendar currentDate = Calendar.getInstance();
            if (isSameWeek(selectedDate, currentDate)) {
                btnTimeIn.setEnabled(true);
                btnTimeOut.setEnabled(true);
            } else {
                btnTimeIn.setEnabled(false);
                btnTimeOut.setEnabled(false);
            }
        });

        Calendar currentDate = Calendar.getInstance();
        calendarView.setDate(currentDate.getTimeInMillis(), false, true);
    }

    private void pickTime(TextView textView, String timeType) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, (view, selectedHour, selectedMinute) -> {
            String time = String.format(Locale.getDefault(), "%02d:%02d", selectedHour, selectedMinute);
            textView.setText(time);
            saveTime(timeType, time);
        }, hour, minute, true);

        timePickerDialog.show();
    }

    private void saveTime(String timeType, String time) {
        String date = new java.text.SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new java.util.Date());
        database.execSQL("INSERT INTO " + TABLE_NAME + " (date, " + timeType + ") VALUES (?, ?)", new Object[]{date, time});
    }

    private boolean isSameWeek(Calendar selectedDate, Calendar currentDate) {
        Calendar startOfWeek = (Calendar) currentDate.clone();
        startOfWeek.set(Calendar.DAY_OF_WEEK, currentDate.getFirstDayOfWeek());

        Calendar endOfWeek = (Calendar) startOfWeek.clone();
        endOfWeek.add(Calendar.DAY_OF_WEEK, 6);

        return !selectedDate.before(startOfWeek) && !selectedDate.after(endOfWeek);
    }
}
