package com.isec.boxreminder.Classes;

import android.content.Context;

import java.util.GregorianCalendar;

/**
 * Created by Miguel on 14-12-2016.
 */

public class ThreadNotificacao extends Thread{

    Medicamento medicamento;

    boolean esperaClick = false;
    boolean notificado = false;

    Context context;

    int mins = 0*60; //numero de minutos vezes 60 segs

    public ThreadNotificacao(Medicamento medicamento, Context context){
        this.medicamento = medicamento;
        this.context = context;
    }

    @Override
    public void run() {

        long notificarDaquiA = new GregorianCalendar().getTimeInMillis()+(mins)*1000;
        while(!notificado){

            if(isEsperaClick())
                break;

            else{
                if((new GregorianCalendar().getTimeInMillis())>= notificarDaquiA && !notificado){
                    Notificacao notificacao = new Notificacao(context, medicamento);
                    notificacao.sendSms();
                    notificado = true;
                }
            }
        }
    }


    public boolean isEsperaClick() {
        return esperaClick;
    }

    public void setEsperaClick(boolean esperaClick) {
        this.esperaClick = esperaClick;
    }
}
