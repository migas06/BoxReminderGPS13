package com.isec.boxreminder.Classes;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.SmsManager;
import android.text.format.DateFormat;
import android.widget.Toast;

import java.text.SimpleDateFormat;

/**
 * Created by Miguel on 08-12-2016.
 */

public class Notificacao {
    private Medicamento medicamento;
    private Context context;
    private String mensagem;

    Ficheiro ficheiro = new Ficheiro();

    private java.text.DateFormat hourFormat = new SimpleDateFormat("HH:mm");

    public Notificacao(Context context, Medicamento medicamento){
        this.context = context;
        this.medicamento = medicamento;
        geraSMS();
    }

    private void geraSMS() {
        mensagem = "«BOXREMINDER», O seu paciente não tomou o "+ medicamento.getNome() +
                " às "+ hourFormat.format(medicamento.getHora()) +".";
    }


    public void sendSms(){

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(ficheiro.lerContacto().toString(), null, mensagem, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
