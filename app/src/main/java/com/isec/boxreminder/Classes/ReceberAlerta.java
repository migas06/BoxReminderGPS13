package com.isec.boxreminder.Classes;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.isec.boxreminder.DetalhesMedicamento;

/**
 * Created by Miguel on 09-12-2016.
 */

public class ReceberAlerta extends BroadcastReceiver {

    Medicamento medicamento;
    Context context;

    public ReceberAlerta(Medicamento medicamento, Context context) {
        this.medicamento = medicamento;
        this.context = context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        gerarNotificacao(context, medicamento);
    }

    private void gerarNotificacao(Context context, Medicamento medicamento) {

        Intent intent = new Intent(context, DetalhesMedicamento.class);
        intent.putExtra("medicamento", medicamento);

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

}
