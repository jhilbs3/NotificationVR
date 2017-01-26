package jeh3.notificationvr;

import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

/**
 *  Main class that handles listening to notifications. Works fine for now.
 */
public class NotificationListener extends NotificationListenerService {

    String TAG = "NOTIFICATION LISTENER: ";

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {

        // weeds out unnecessary hidden android system notifications
        if(sbn.getNotification().priority < 0) return;
        super.onNotificationPosted(sbn);
        //Log.e(TAG, sbn.getNotification().toString());

        String pkg = sbn.getPackageName();
        String title = sbn.getNotification().extras.getString("android.title");
        String text;
        try {
            text = sbn.getNotification().extras.getCharSequence("android.text").toString();
        } catch(NullPointerException e){
            text = "NullPointerException: TEXT COULD NOT BE OBTAINED";
        }
        Log.e(TAG, pkg);
        Log.e(TAG, title);
        Log.e(TAG, text);
        //SocketClient s = null;
        //s = new SocketClient();
        //s.setItems("10.196.64.1", 6789, sbn);

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
