package com.login.huntvision.managers.alarms;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.login.huntvision.AgendaActivity_;
import com.login.huntvision.AgendaDetalheActivity_;
import com.login.huntvision.R;
import com.login.huntvision.models.Agenda;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            Bundle bundle = intent.getExtras();
            String message = bundle.getString("alarm_message");
            Agenda agenda = (Agenda) bundle.getSerializable("agenda");

            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(context)
                            .setSmallIcon(R.drawable.icone_cliente)
                            .setContentText(message)
                            .setAutoCancel(true)
                            .setContentTitle("Lembrete HuntVision")
                            .setStyle(new NotificationCompat.InboxStyle().setBigContentTitle("Lembrete HuntVision"));

            Intent resultIntent = new Intent(context, AgendaDetalheActivity_.class);

            resultIntent.putExtra("agenda", agenda);

            TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);

            stackBuilder.addParentStack(AgendaActivity_.class);

            stackBuilder.addNextIntent(resultIntent);
            PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_CANCEL_CURRENT);
            mBuilder.setContentIntent(resultPendingIntent);
            NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            mNotificationManager.notify(1, mBuilder.build());

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}