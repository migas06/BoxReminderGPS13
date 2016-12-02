package com.isec.boxreminder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.isec.boxreminder.Classes.Gravacao;
import com.isec.boxreminder.Classes.Medicamento;

import java.io.IOException;
import java.io.Serializable;

public class InserirRegisto extends Activity
{
    Context context = this;
    Medicamento medicamento;

    boolean estadoAGravar = false;

    Button buttonGravar;
    Button buttonReproduzir;
    Button next;

    EditText editTextNomeMedicamento;

    String nomeFicheiro;
    String nomeMedicamentoInserido;

    Gravacao novaGravacao;

    public InserirRegisto()
    {
        nomeFicheiro = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Notifications/";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_registo);

        medicamento  = new Medicamento();
        novaGravacao = new Gravacao();

        editTextNomeMedicamento = (EditText) findViewById(R.id.nomeMedicamento);

        buttonReproduzir    = (Button) findViewById(R.id.buttonReproduzir);
        buttonGravar        = (Button) findViewById(R.id.buttonGravar);
        next                = (Button) findViewById(R.id.next);

        //LISTENER DO BOTAO DE GRAVAR
        buttonGravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nomeMedicamentoInserido = editTextNomeMedicamento.getText().toString();
                if(!nomeMedicamentoInserido.equals("")) {
                    if (estadoAGravar == false) {
                        nomeFicheiro += nomeMedicamentoInserido+".3gp";
                        novaGravacao.setCaminhoFicheiro(nomeFicheiro);
                        novaGravacao.comecaGravar();
                        estadoAGravar = true;
                        buttonGravar.setText("Parar Gravação");
                    }else{
                        novaGravacao.stopGravar();
                        buttonGravar.setText("Gravar");
                    }
                }else
                    Toast.makeText(context, "Tem de introduzir o nome do medicamento primeiro!", Toast.LENGTH_LONG).show();
            }
        });


        buttonReproduzir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!nomeMedicamentoInserido.equals(""))
                    novaGravacao.reproduzGravacao();
                else
                    Toast.makeText(context, "Tem de ter uma gravação!", Toast.LENGTH_LONG).show();
            }
        });

        //PROXIMA ATIVIDADE
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            medicamento.setNome(editTextNomeMedicamento.getText().toString());
            medicamento.setCaminhoGravacao(nomeFicheiro);

            Intent intent = new Intent(context, InserirQuantidade.class);
            intent.putExtra("medicamento", medicamento);
            startActivity(intent);
            }
        });
    }
}
