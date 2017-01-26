package jeh3.notificationvr;

import android.app.NotificationManager;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.support.v4.app.NotificationCompat;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    NotificationCompat.Builder mBuilder;
    NotificationManager notMan;

    @Override // runs on app load
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent=new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
        startActivity(intent);
        startService(new Intent(this,NotificationListener.class));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // TODO: make a listener for notifications and log those
        final NotificationListener notListen = new NotificationListener();

        // notification maker
        mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(android.R.drawable.alert_dark_frame)
                .setContentTitle("Test Notification")
                .setContentText("to be used for notification listener");

        // button with it's listener for throwing the notification
        final Button notBut = (Button) findViewById(R.id.button);
        notBut.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                notMan = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notMan.notify(001, mBuilder.build());
                System.out.println(notListen.getActiveNotifications());
            }
        });



        // TODO: make a socket and get those notifications to a server
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}


