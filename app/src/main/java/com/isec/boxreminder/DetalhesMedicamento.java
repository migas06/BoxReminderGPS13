package com.isec.boxreminder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.isec.boxreminder.Classes.Alarme;
import com.isec.boxreminder.Classes.Ficheiro;
import com.isec.boxreminder.Classes.Medicamento;
import com.isec.boxreminder.Classes.Notificacao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DetalhesMedicamento extends Activity {
    private Button button1;
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

        textViewDataFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        button1 =(Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(DetalhesMedicamento.this, button1);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.popup_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getTitle().equals("Editar")){
                            medicamento.setEditar(true);
                            Intent intent = new Intent(context, InserirRegisto.class);
                            intent.putExtra("medicamento", medicamento);
                            startActivity(intent);
                        }
                        if(item.getTitle().equals("Eliminar")){

                            //Intent intent = new Intent(context, MainActivity.class);
                            //startActivity(intent);
                            apagarMedicamento();
                            finish();
                        }
                        return true;
                    }
                });

                popup.show(); //showing popup menu
            }
        });
    }

    private void apagarMedicamento()
    {
        Ficheiro ficheiro = new Ficheiro();
        ficheiro.lerFicheiro();
        ArrayList<Medicamento> lista = ficheiro.getLista();

        lista.remove(medicamento);

        ficheiro.escreverFicheiro();
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
