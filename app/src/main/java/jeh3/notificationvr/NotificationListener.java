package jeh3.notificationvr;

import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

/**
 * Created by Joseph on 1/24/2017.
 */
public class NotificationListener extends NotificationListenerService {

    String TAG = "NOTIFICATION LISTENER: ";

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {

        // weeds out unnecessary hidden android system notifications
        if(sbn.getNotification().priority < 0) return;
        super.onNotificationPosted(sbn);
        Log.e(TAG, sbn.getNotification().toString());

    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn){
        //Log.e(TAG, "Notification REMOVED!!");
    }

    @Override
    public void onListenerConnected(){
        Log.e(TAG, "*******Listener Connected*******");
    }
}
