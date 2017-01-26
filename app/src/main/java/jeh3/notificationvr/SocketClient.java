package jeh3.notificationvr;

import android.service.notification.StatusBarNotification;
import android.util.Log;

import java.io.*;
import java.net.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Currently not working but will be used in the future to send notifications to a server
 */

public class SocketClient {

    private static String TAG = "SOCKET CLIENT: ";
    private String IP;
    private int PORT;
    private StatusBarNotification sbn;

    public void setItems(String IP, int PORT, StatusBarNotification sbn){
        this.IP = IP;
        this.PORT = PORT;
        this.sbn = sbn;
        initiateConnection();
    }

    private void initiateConnection(){

        // create the socket
        Socket mySocket = null;
        BufferedReader in;
        PrintWriter out;
        try {
            mySocket = new Socket(IP, PORT);
        } catch(IOException e){
            Log.e(TAG, "Couldn't establish connection.");
        }

        // preparing for message  confirmation
        try {
            in = new BufferedReader(new InputStreamReader(mySocket.getInputStream(), "UTF-8"));
            out = new PrintWriter(mySocket.getOutputStream(), true);
            out.println(sbn.getNotification().toString());
            String resp = in.readLine();
            Log.e(TAG, resp);
            mySocket.close();
            in.close();
            out.close();
        } catch(IOException e){
            Log.e(TAG, "error handling reading and writing data");
        }
    }
}
