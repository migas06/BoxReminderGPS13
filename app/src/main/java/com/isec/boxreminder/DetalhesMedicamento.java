package com.isec.boxreminder;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.isec.boxreminder.Classes.Medicamento;

public class DetalhesMedicamento extends Activity {

    Medicamento medicamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_medicamento);

        medicamento = (Medicamento) getIntent().getSerializableExtra("medicamento");

        TextView textViewNome = (TextView) findViewById(R.id.NomeMedDetalhes);
        textViewNome.setText(medicamento.getNome());
    }
}
