package com.isec.boxreminder.Classes;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.text.method.KeyListener;
import android.util.Log;
import android.widget.Toast;

import com.isec.boxreminder.ApresentarNotificacao;
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

        if(new Ficheiro().vozEstaAtiva())
            criaAtividade(context, (int) System.currentTimeMillis());
        else
            gerarNotificacao(context, (int) System.currentTimeMillis());
    }

    private void gerarNotificacao(Context context, int id) {

        Intent intent = new Intent(context, DetalhesMedicamento.class);
        intent.putExtra("medicamento", medicamento);

        PendingIntent notificacao = PendingIntent.getActivity(context, id, intent, 0);

        NotificationCompat.Builder construirNotificacao = new NotificationCompat.Builder(context);
        construirNotificacao.setSmallIcon(android.R.drawable.ic_notification_overlay);
        construirNotificacao.setContentTitle("BOXREMINDER");
        construirNotificacao.setTicker("Tome o seu medicamento agora!");
        construirNotificacao.setContentText("Hora de tomar "+medicamento.getNome());


        construirNotificacao.setContentIntent(notificacao);
        construirNotificacao.setDefaults(NotificationCompat.DEFAULT_SOUND);
        construirNotificacao.setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(id, construirNotificacao.build());
    }

    private void criaAtividade(Context context, int id)
    {
        //Atividade com listeners para os botões físicos
        //e que reproduz a gravação

        Intent intent = new Intent(context, ApresentarNotificacao.class);
        intent.putExtra("medicamento", medicamento);

        PendingIntent alarmIntent = PendingIntent.getActivity(context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        //setExact() reduz a vida da bateria mas é mais exato no tempo de entra
        //set() aumenta a vida da bateria mas tem alguns segundos de atraso (dependendo da carga do Sistema)
        ((AlarmManager) context.getSystemService(Context.ALARM_SERVICE)).setExact(AlarmManager.RTC_WAKEUP, 0, alarmIntent);
    }
}
