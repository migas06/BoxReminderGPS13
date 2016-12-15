package com.isec.boxreminder;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import com.isec.boxreminder.Classes.Ficheiro;


public class ChamadaSOS extends AppWidgetProvider {

    Ficheiro ficheiro = new Ficheiro();

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        for (int appWidgetId : appWidgetIds) {

            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:"+ficheiro.lerContacto()));
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, callIntent, 0);

            // Get the layout for the App Widget and attach an on-click listener to the button
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.chamada_sos);
            views.setOnClickPendingIntent(R.id.sos, pendingIntent);

            // Tell the AppWidgetManager to perform an update on the current App Widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }
}

