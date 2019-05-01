package com.etemu.pens.mvp.data.firebase;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.etemu.pens.mvp.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private final static String TAG="FCM Message";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());
        showNotification(remoteMessage);
    }

    private void showNotification(RemoteMessage remoteMessage){
        String title=remoteMessage.getNotification().getTitle();
        String body=remoteMessage.getNotification().getBody();

//        Intent intent=new Intent(this, MainNavigationActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 , intent,
//                PendingIntent.FLAG_ONE_SHOT);
//        Bitmap notifyImage = BitmapFactory.decodeResource(getResources(), R.drawable.ic_notifications_black_empty);
//        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
//                .setSmallIcon(R.drawable.ic_notifications_black_empty)
//                .setLargeIcon(notifyImage)
//                .setColor(Color.parseColor("#FFE74C3C"))
//                .setContentTitle(title)
//                .setContentText(body)
//                .setAutoCancel(true)
//                .setSound(defaultSoundUri)
//                .setContentIntent(pendingIntent);
//        NotificationManager notificationManager =
//                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.notify(0, notificationBuilder.build());
    }
}
