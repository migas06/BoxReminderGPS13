package com.isec.boxreminder;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.isec.boxreminder.Classes.Alarme;
import com.isec.boxreminder.Classes.Medicamento;
import com.isec.boxreminder.Classes.Notificacao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DetalhesMedicamento extends Activity {

    Medicamento medicamento;
    Context context = this;

    TextView textViewNome;
    TextView textViewQuantidadeETipo;
    TextView textViewDataInicio;
    TextView textViewDataFinal;
    TextView textViewHora;
    TextView textViewFrequencia;

    DateFormat hourFormat = new SimpleDateFormat("HH:mm");
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_medicamento);

        medicamento = (Medicamento) getIntent().getSerializableExtra("medicamento");

        textViewNome = (TextView) findViewById(R.id.NomeMedDetalhes);
        textViewQuantidadeETipo = (TextView) findViewById(R.id.DetQuantETipo);
        textViewDataInicio = (TextView) findViewById(R.id.DetDataI);
        textViewDataFinal  = (TextView) findViewById(R.id.DetDataF);
        textViewQuantidadeETipo = (TextView) findViewById(R.id.DetQuantETipo);
        textViewHora = (TextView) findViewById(R.id.DetProxMedHora);
        textViewFrequencia = (TextView) findViewById(R.id.DetFreqTempo);

        textViewNome.setText(medicamento.getNome());
        textViewQuantidadeETipo.setText(medicamento.getQuantidade() + " " + medicamento.getTipoQuantidade());
        textViewDataInicio.setText(dateFormat.format(medicamento.getDataInicio()));
        textViewDataFinal.setText(dateFormat.format(medicamento.getDataFim()));
        textViewHora.setText(hourFormat.format(medicamento.getHora()));

        String frequencia = criarFrequencia();
        textViewFrequencia.setText(frequencia);
    }

    private String criarFrequencia() {
        String frequencia = "";

        if(medicamento.getRepeticao(0) == true)
            frequencia += "Seg ";
        if(medicamento.getRepeticao(1) == true)
            frequencia += "Ter ";
        if(medicamento.getRepeticao(2) == true)
            frequencia += "Qua ";
        if(medicamento.getRepeticao(3) == true)
            frequencia += "Qui ";
        if(medicamento.getRepeticao(4) == true)
            frequencia += "Sex ";
        if(medicamento.getRepeticao(5) == true)
            frequencia += "Sab ";
        if(medicamento.getRepeticao(6) == true)
            frequencia += "Dom ";

        return frequencia;
    }
}
