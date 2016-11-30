package com.isec.boxreminder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.isec.boxreminder.Classes.Medicamento;

import java.io.Serializable;

public class InserirRegisto extends Activity
{
    Button next;
    Context context;
    Medicamento medicamento;
    EditText editTextNomeMedicamento;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_registo);

        context = this;
        medicamento = new Medicamento();
        editTextNomeMedicamento = (EditText) findViewById(R.id.nomeMedicamento);


        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            medicamento.setNome(editTextNomeMedicamento.getText().toString());
            /*
             * APLICAR GRAVAçÃO DE VOZ
             *
             */

            Intent intent = new Intent(context, InserirQuantidade.class);
            intent.putExtra("medicamento", medicamento);
            startActivity(intent);
            }
        });
    }
}
