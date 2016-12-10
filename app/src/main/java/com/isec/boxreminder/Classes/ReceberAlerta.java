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

    Medicamento medicamento;

    @Override
    public void onReceive(Context context, Intent intent) {
        medicamento = (Medicamento) intent.getSerializableExtra("medicamento");
        gerarNotificacao(context);
    }

    private void gerarNotificacao(Context context) {

        Intent intent = new Intent(context, DetalhesMedicamento.class);
        intent.putExtra("medicamento", medicamento);

        PendingIntent notificacao = PendingIntent.getActivity(context, 0, intent,0 );

        NotificationCompat.Builder construirNotificacao = new NotificationCompat.Builder(context);
        construirNotificacao.setSmallIcon(android.R.drawable.ic_notification_overlay);
        construirNotificacao.setContentTitle("BOXREMINDER");
        construirNotificacao.setTicker("Tome o seu medicamento agora!");
        construirNotificacao.setContentText("Hora de tomar "+medicamento.getNome());


        construirNotificacao.setContentIntent(notificacao);
        construirNotificacao.setDefaults(NotificationCompat.DEFAULT_SOUND);
        construirNotificacao.setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, construirNotificacao.build());

    }
}
