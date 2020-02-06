package sg.grp4.DengueSG;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity /*implements NavigationView.OnNavigationItemSelectedListener*/ {

    private BottomNavigationView bottomNavigationView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.navigationMyProfile:
                    selectedFragment = new UserFragment();
                    break;
                case R.id.navigationReport:
                    selectedFragment = new ShopFragment();
                    break;
                case R.id.navigationHotspots:
                    selectedFragment = new HotspotFragment();
                    break;
                case  R.id.navigationInfo:
                    selectedFragment = new InfoFragment();
                    break;
                case  R.id.navigationPosts:
                    selectedFragment = new PostsFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, selectedFragment).commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Calendar calendar = Calendar.getInstance();

        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bottomNavigationView.getLayoutParams();
//        layoutParams.setBehavior(new BottomNavigationBehavior());

        bottomNavigationView.setSelectedItemId(R.id.navigationHotspots);

        // Daily Notification

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Calendar calendar = Calendar.getInstance();

                calendar.set(Calendar.HOUR_OF_DAY,7);
                calendar.set(Calendar.MINUTE,30);

                Intent intent = new Intent(getApplicationContext(), Notification_receiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

            }
        });
    }


}
