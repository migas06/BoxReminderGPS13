package com.isec.boxreminder.Classes;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.isec.boxreminder.R;

public class ServicoNotificacao extends Service
{
    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Medicamento medicamento = (Medicamento) intent.getSerializableExtra("medicamento");

        Alarme alarme = new Alarme(this, medicamento);

        alarme.test();

        return super.onStartCommand(intent, flags, startId);
    }
}
