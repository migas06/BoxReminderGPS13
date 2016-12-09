package com.isec.boxreminder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.isec.boxreminder.Classes.Ficheiro;
import com.isec.boxreminder.Classes.Notificacao;

public class MainActivity extends Activity {
    Ficheiro ficheiro=new Ficheiro();
    Button next;
    Button settings;
    Button exit;
    Button registos;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        next = (Button) findViewById(R.id.menuButtonRegistarMedicamento);
        settings = (Button) findViewById(R.id.menuButtonSettings);
        registos = (Button) findViewById(R.id.menuButtonVerRegistos);
        exit = (Button) findViewById(R.id.menuButtonSair);

        if(ficheiro.lerContacto().equals("nofile")){
            Intent intent = new Intent(context, Inicial.class);
            startActivity(intent);
        }

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, InserirRegisto.class);
                startActivity(intent);
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Definicoes.class);
                startActivity(intent);
            }
        });
        registos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, VerRegistos.class);
                startActivity(intent);
            }
        });

    }

}
