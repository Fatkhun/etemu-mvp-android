package com.etemu.pens.mvp.data.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.etemu.pens.mvp.data.database.DatabaseHelper;
import com.etemu.pens.mvp.data.database.model.Reminder;
import com.etemu.pens.mvp.utils.NotificationUtil;


public class SnoozeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        DatabaseHelper database = DatabaseHelper.getInstance(context);
        int reminderId = intent.getIntExtra("NOTIFICATION_ID", 0);
        if (reminderId != 0 && database.isNotificationPresent(reminderId)) {
            Reminder reminder = database.getNotification(reminderId);
            NotificationUtil.createNotification(context, reminder);
        }
        database.close();
    }
}