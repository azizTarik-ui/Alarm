package com.example.medicinereminder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private int notiId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);
        findViewById(R.id.setButton).setOnClickListener(this);
        findViewById(R.id.cancelButton).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        EditText editText = findViewById(R.id.medicineName);
        TimePicker timePicker = findViewById(R.id.timePicker);
        Intent intent = new Intent(MainActivity.this, Alarm.class);
        intent.putExtra("notiId", notiId);
        intent.putExtra("MedicineName", editText.getText().toString());
        PendingIntent alarmIntent = PendingIntent.getBroadcast(MainActivity.this, 0 ,intent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        switch (v.getId()) {
            case R.id.setButton:
                int hour = timePicker.getCurrentHour();
                int min = timePicker.getCurrentMinute();
                Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY, hour);
                startTime.set(Calendar.MINUTE, min);
                startTime.set(Calendar.SECOND, 0);
                long alarmStartTime = startTime.getTimeInMillis();
                alarmManager.set(AlarmManager.RTC_WAKEUP, alarmStartTime, alarmIntent);
                Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
                break;

            case R.id.cancelButton:
                alarmManager.cancel(alarmIntent);
                Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}