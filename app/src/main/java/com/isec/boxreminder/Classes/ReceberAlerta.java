package com.isec.boxreminder.Classes;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.isec.boxreminder.DetalhesMedicamento;

import java.util.Calendar;

/**
 * Created by Miguel on 09-12-2016.
 */

public class ReceberAlerta extends BroadcastReceiver {

    /*public ReceberAlerta(Medicamento medicamento, Context context) {
        this.medicamento = medicamento;
        this.context = context;
    }*/

    @Override
    public void onReceive(Context context, Intent intent) {
        gerarNotificacao(context);
    }

    private void gerarNotificacao(Context context) {

        Intent intent = new Intent(context, DetalhesMedicamento.class);

        PendingIntent notificacao = PendingIntent.getActivity(context, 0, intent,0 );

        NotificationCompat.Builder construirNotificacao = new NotificationCompat.Builder(context);
        construirNotificacao.setSmallIcon(android.R.drawable.alert_dark_frame);
        construirNotificacao.setContentTitle("BOXREMINDER");
        construirNotificacao.setTicker("Tome o seu medicamneto agora");
        construirNotificacao.setContentText("Tome o seu medicamneto agora 1");


        construirNotificacao.setContentIntent(notificacao);
        construirNotificacao.setDefaults(NotificationCompat.DEFAULT_SOUND);
        construirNotificacao.setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, construirNotificacao.build());

    }


        /*@Override
        public void onReceive(Context context, Intent intent) {
            String message = "Hellooo, alrm worked ----";
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            Intent intent2 = new Intent(context, Medicamento.class);
            intent2.putExtra("medicamento", medicamento);
            intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent2);
        }

        public void setAlarm(Context context){
            Log.d("Carbon","Alrm SET !!");

            // get a Calendar object with current time
            Calendar cal = Calendar.getInstance();
            // add 30 seconds to the calendar object
            cal.add(Calendar.SECOND, 30);
            Intent intent = new Intent(context, ReceberAlerta.class);
            PendingIntent sender = PendingIntent.getBroadcast(context, 192837, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            // Get the AlarmManager service
            AlarmManager am = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
            am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), sender);
        }*/

}
