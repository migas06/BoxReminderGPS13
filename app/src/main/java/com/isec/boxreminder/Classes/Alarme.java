package com.isec.boxreminder.Classes;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.isec.boxreminder.ApresentarNotificacao;
import com.isec.boxreminder.DetalhesMedicamento;
import com.isec.boxreminder.MainActivity;
import com.isec.boxreminder.R;

import java.util.GregorianCalendar;

/**
 * Created by Miguel on 08-12-2016.
 */

public class Alarme {

    private Medicamento medicamento;
    private Context context;

    private int idNotificacao = 0;

    private NotificationManager notificationManager;
    private boolean notificacaoEstaAtiva = false;

    public Alarme(Context context, Medicamento medicamento){
        this.context = context;
        this.medicamento = medicamento;
    }

    public void criaNotificacao(){


        NotificationCompat.Builder construirNotificacao = new NotificationCompat.Builder(context);
        construirNotificacao.setContentTitle("Tomar Medicamento");
        construirNotificacao.setContentText("Está na hora de tomar " + medicamento.getQuantidade() + " "
                + medicamento.getTipoQuantidade() + " de "
                + medicamento.getNome());
        construirNotificacao.setTicker("ALERTA: Tomar Medicamento");

        Intent intent = new Intent(context, DetalhesMedicamento.class);
        intent.putExtra("medicamento", medicamento);

        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(context);
        taskStackBuilder.addParentStack(DetalhesMedicamento.class);
        taskStackBuilder.addNextIntent(intent);

        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        construirNotificacao.setContentIntent(pendingIntent);

        notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        notificationManager.notify(idNotificacao, construirNotificacao.build());

        notificacaoEstaAtiva = true;


    }

    public void destroiNotificacao(){

        if(notificacaoEstaAtiva){
            notificationManager.cancel(idNotificacao);
            notificacaoEstaAtiva=false;
        }

        idNotificacao++;
    }

    public void criaAlarme(){

        Long alertTime = new GregorianCalendar().getTimeInMillis()+2*1000;

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context, ReceberAlerta.class);
        intent.putExtra("medicamento", medicamento);

        int id = (int) System.currentTimeMillis();

        PendingIntent alarmIntent = PendingIntent.getBroadcast(context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.set(AlarmManager.RTC_WAKEUP, alertTime, alarmIntent);
    }
}
