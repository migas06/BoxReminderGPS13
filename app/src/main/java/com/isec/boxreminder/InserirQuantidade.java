package com.isec.boxreminder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.isec.boxreminder.Classes.Medicamento;

public class InserirQuantidade extends Activity
{
    Button next;
    Context context;
    Medicamento medicamento;

    EditText editTextQuantidade;
    Spinner spinnerUnidade;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_quantidade);

        //RECEBER O MEDICAMENTO DA ATIVIDADE ANTERIOR
        medicamento = (Medicamento) getIntent().getSerializableExtra("medicamento");

        editTextQuantidade = (EditText) findViewById(R.id.quantidade);
        spinnerUnidade     = (Spinner)  findViewById(R.id.spinnerUnidade);

        context = this;
        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //ADICIONAR NOVOS CAMPOS ASSOCIADOS À ATIVIDADE
                medicamento.setQuantidade(editTextQuantidade.getText().toString());
                medicamento.setTipoQuantidade(spinnerUnidade.getSelectedItem().toString());

                Intent intent = new Intent(context, Data.class);
                intent.putExtra("medicamento", medicamento);
                startActivity(intent);
                finish();
            }
        });
    }
}
