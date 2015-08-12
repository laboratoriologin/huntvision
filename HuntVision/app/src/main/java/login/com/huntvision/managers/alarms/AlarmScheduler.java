package login.com.huntvision.managers.alarms;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

import login.com.huntvision.models.Agenda;

/**
 * Created by login on 11/08/15.
 */
public final class AlarmScheduler {

    public static final int CODE = 192837;

    private AlarmScheduler() {
    }

    public static void schedule(Agenda agenda, Context context) {


        Calendar cal = Calendar.getInstance();
        cal.setTime(agenda.getDataHora());
        cal.add(Calendar.MINUTE, -10);
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra("alarm_message", "Em 10 minutos visita para " + agenda.getCliente().getNome());
        intent.putExtra("agenda", agenda);

        PendingIntent sender = PendingIntent.getBroadcast(context, Integer.valueOf(agenda.getId()), intent, PendingIntent.FLAG_CANCEL_CURRENT);

        // Get the AlarmManager service
        AlarmManager am = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
        am.set(AlarmManager.RTC, cal.getTimeInMillis(), sender);


    }
}
