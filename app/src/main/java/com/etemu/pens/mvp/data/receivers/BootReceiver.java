package com.etemu.pens.mvp.data.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


import com.etemu.pens.mvp.data.database.DatabaseHelper;
import com.etemu.pens.mvp.data.database.model.Reminder;
import com.etemu.pens.mvp.utils.AlarmUtil;
import com.etemu.pens.mvp.utils.DateAndTimeUtil;

import java.util.Calendar;
import java.util.List;

public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        DatabaseHelper database = DatabaseHelper.getInstance(context);
        List<Reminder> reminderList = database.getNotificationList(Reminder.ACTIVE);
        database.close();
        Intent alarmIntent = new Intent(context, AlarmReceiver.class);

        for (Reminder reminder : reminderList) {
            Calendar calendar = DateAndTimeUtil.parseDateAndTime(reminder.getDateAndTime());
            calendar.set(Calendar.SECOND, 0);
            AlarmUtil.setAlarm(context, alarmIntent, reminder.getId(), calendar);
        }
    }
}