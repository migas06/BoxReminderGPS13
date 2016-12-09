package com.isec.boxreminder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.isec.boxreminder.Classes.Alarme;
import com.isec.boxreminder.Classes.Ficheiro;
import com.isec.boxreminder.Classes.Medicamento;
import com.isec.boxreminder.Classes.MinhaListaAdaptavel;

import java.util.ArrayList;

public class VerRegistos extends Activity {

    Ficheiro ficheiro = new Ficheiro();

    ArrayList<Medicamento> lista = new ArrayList<Medicamento>();

    ListView listView;

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_registos);

        ficheiro.lerFicheiro();
        lista = ficheiro.getLista();

        povoarListView();
    }

    private void povoarListView() {
        ArrayAdapter<Medicamento> adapta = new MinhaListaAdaptavel(context, lista);
        listView = (ListView) findViewById(R.id.listRegisto);
        listView.setAdapter(adapta);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Alarme alarme = new Alarme(context, lista.get(i));
                alarme.criaAlarme();

                Intent intent = new Intent(context, DetalhesMedicamento.class);
                intent.putExtra("medicamento", lista.get(i));
                startActivity(intent);
            }
        });
    }
}
