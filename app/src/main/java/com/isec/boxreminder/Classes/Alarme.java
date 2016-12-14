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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Miguel on 08-12-2016.
 */

public class Alarme {

    private Medicamento medicamento;
    private Context context;
    private int id;

    private int hora;
    private int minuto;

    private int diaInicio;
    private int mesInicio;
    private int anoInicio;
    private int diaFim;
    private int mesFim;
    private int anoFim;

    Calendar calendarInicio;
    Calendar calendarFim;

    SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");

    public Alarme(Context context, Medicamento medicamento){
        this.context = context;
        this.medicamento = medicamento;

        formataHora(medicamento.getHora());
        formataData(medicamento.getDataInicio());
        formataDataFim(medicamento.getDataFim());

        criaDiaInicio();

        if(medicamento.getFrequencia().equals("Diário"))
            criaAlarmeDiario();
        if(medicamento.getFrequencia().equals("Semanal"))
            criaAlarmeSemanal();
        if(medicamento.getFrequencia().equals("Mensal"))
            criaAlarmeMensal();
    }


    public void criaAlarmeMensal(){

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context, ReceberAlerta.class);
        intent.putExtra("medicamento", medicamento);

        id = (int) System.currentTimeMillis();

        PendingIntent alarmIntent = PendingIntent.getBroadcast(context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT);


        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendarInicio.getTimeInMillis(), 1000*2592000, alarmIntent);
    }

    public void criaAlarmeSemanal(){

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context, ReceberAlerta.class);
        intent.putExtra("medicamento", medicamento);

        id = (int) System.currentTimeMillis();

        PendingIntent alarmIntent = PendingIntent.getBroadcast(context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT);


        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendarInicio.getTimeInMillis(), 1000*604800, alarmIntent);
    }

    public void criaAlarmeDiario(){

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);


        Intent intent = new Intent(context, ReceberAlerta.class);
        intent.putExtra("medicamento", medicamento);

        id = (int) System.currentTimeMillis();

        PendingIntent alarmIntent = PendingIntent.getBroadcast(context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendarInicio.getTimeInMillis(), AlarmManager.INTERVAL_DAY, alarmIntent);
        //alarmManager.set(AlarmManager.RTC_WAKEUP, alertTime, alarmIntent);
    }

    public void formataHora(Date hora){
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");

        String sHora = formatoHora.format(hora);
        String split[] = sHora.split(":");

        this.hora = Integer.parseInt(split[0]);
        this.minuto = Integer.parseInt(split[1]);
    }

    public void formataData(Date data){
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

        String sData = formatoData.format(data);
        String split[] = sData.split("/");

        this.diaInicio = Integer.parseInt(split[0]);
        this.mesInicio = Integer.parseInt(split[1]);
        this.anoInicio = Integer.parseInt(split[2]);
    }

    public void formataDataFim(Date data){
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

        String sData = formatoData.format(data);
        String split[] = sData.split("/");

        this.diaFim = Integer.parseInt(split[0]);
        this.mesFim = Integer.parseInt(split[1]);
        this.anoFim = Integer.parseInt(split[2]);
    }

    private void criaDiaInicio() {
        calendarInicio = Calendar.getInstance();

        calendarInicio.set(Calendar.DAY_OF_MONTH, diaInicio);
        calendarInicio.set(Calendar.MONTH, mesInicio);
        calendarInicio.set(Calendar.YEAR, anoInicio);
        calendarInicio.set(Calendar.HOUR_OF_DAY, hora);
        calendarInicio.set(Calendar.MINUTE, minuto);
    }


    //SE O DIA ATUAL FOR MAIOR QUE O DIA FIM CANCELA
    private void criaDiaFim() {
        calendarFim = Calendar.getInstance();

        calendarFim.set(Calendar.DAY_OF_MONTH, diaFim);
        calendarFim.set(Calendar.MONTH, mesFim);
        calendarFim.set(Calendar.YEAR, anoFim);
        calendarFim.set(Calendar.HOUR_OF_DAY, hora);
        calendarFim.set(Calendar.MINUTE, minuto);
    }
}


