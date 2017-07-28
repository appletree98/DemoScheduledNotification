package sg.edu.rp.c346.demoschedulednotification;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    int reqCode = 12345;
    Button btnSchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSchedule = (Button)findViewById(R.id.btnSchedule);

        btnSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // choose second import as first is for api >24
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.SECOND, 5);

                // create a new PendingIntent and add it to the AlarmManger
                Intent intent = new Intent(MainActivity.this,ScheduledNotificationReceiver.class);
                PendingIntent pendingIntent= PendingIntent.getActivity(MainActivity.this,reqCode,intent,PendingIntent.FLAG_CANCEL_CURRENT);

                AlarmManager am = (AlarmManager)getSystemService(Activity.ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),pendingIntent);
            }
        });

    }
}
