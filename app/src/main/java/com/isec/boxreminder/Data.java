package com.isec.boxreminder;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.isec.boxreminder.Classes.Ficheiro;
import com.isec.boxreminder.Classes.Medicamento;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import static android.R.attr.dial;
import static android.R.attr.onClick;

public class Data extends Activity {

    final SimpleDateFormat formatoData = new SimpleDateFormat("dd-MM-yyyy");

    Calendar c     = Calendar.getInstance();
    int startYear  = c.get(Calendar.YEAR);
    int startMonth = c.get(Calendar.MONTH)+1;     //POR ALGUM MOTIVO O NUMERO ASSOCIADO AO Mês É MENOS 1 (PE Jan = 0, Fev = 1, etc)
    int startDay   = c.get(Calendar.DAY_OF_MONTH);

    Date dateDataInicio;
    Date dateDataFim;
    Date dateHora;

    //TRUE  PARA DATA_INICIAL
    //FALSE PARA DATA_FINAL
    boolean change;

    ImageView dataInicio;
    ImageView dataFim;

    EditText editTextDataInicio;
    EditText editTextDataFim;
    EditText editTextHora;
    EditText editTextMin;

    CheckBox segunda, terca, quarta, quinta, sexta, sabado, domingo;

    Button next;
    Context context = this;

    Medicamento medicamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        try{
            dateDataInicio = dateDataFim = formatoData.parse(startDay + "-" + startMonth + "-" + startYear);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //RECEBER O MEDICAMENTO DA ATIVIDADE ANTERIOR
        medicamento = (Medicamento) getIntent().getSerializableExtra("medicamento");

        //Declaração de variaveis
        editTextDataInicio = (EditText) findViewById(R.id.editTextDataInicio);
        editTextDataFim    = (EditText) findViewById(R.id.editTextDataFim);
        editTextHora       = (EditText) findViewById(R.id.horas);
        editTextMin        = (EditText) findViewById(R.id.minutos);

        editTextDataInicio.setText(startDay + "-" + startMonth + "-" + startYear);
        editTextDataFim.setText(startDay + "-" + startMonth + "-" + startYear);

        segunda = (CheckBox) findViewById(R.id.segunda);
        terca = (CheckBox) findViewById(R.id.terca);
        quarta = (CheckBox) findViewById(R.id.quarta);
        quinta = (CheckBox) findViewById(R.id.quinta);
        sexta = (CheckBox) findViewById(R.id.sexta);
        sabado = (CheckBox) findViewById(R.id.sabado);
        domingo = (CheckBox) findViewById(R.id.domingo);

        dataInicio = (ImageView) findViewById(R.id.imagemDataInicio);
        dataFim    = (ImageView) findViewById(R.id.imagemDataFim);


        //DEFENIÇãO DOS LISTENERS DAS DATAS
        dataInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change = true;
                DialogFragment dialogFragment = new StartDatePicker();
                dialogFragment.show(getFragmentManager(), "start_date_picker");
            }
        });

        dataFim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change = false;
                DialogFragment dialogFragment = new StartDatePicker();
                dialogFragment.show(getFragmentManager(), "start_date_picker");
            }
        });

        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            //verifica se
            if(editTextHora.getText().toString().equals("") || editTextMin.getText().toString().equals(""))
            {
                Toast.makeText(context, "Os campos data e hora têm de ser preenchidos", Toast.LENGTH_LONG).show();
            //verifica se os numeros introduzidos nas horas e minutos são validos
            }else if(Integer.parseInt(editTextHora.getText().toString())<24 && Integer.parseInt(editTextHora.getText().toString())>0
                    && Integer.parseInt(editTextMin.getText().toString())<60
                    && Integer.parseInt(editTextMin.getText().toString())>=0)
            {
                if (addAoMedicamento()) {
                    inserirNoFicheiro();
                    Intent intent = new Intent(context, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(context, "Tem de introduzir data e hora válidos", Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(context, "Tem de introduzir uma hora válida", Toast.LENGTH_LONG).show();
                editTextHora.setText("");
                editTextMin.setText("");
            }
            }
        });
    }

    private boolean addAoMedicamento() {

        //CRIAR DATA E HORA
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
        try {
            dateHora = formatoHora.parse(editTextHora.getText().toString() + ":" + editTextMin.getText().toString());

            medicamento.setDataInicio(dateDataInicio);
            medicamento.setDataFim(dateDataFim);
            medicamento.setHora(dateHora);
            medicamento.setRepeticao(setRepeticoes());

        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    private boolean [] setRepeticoes() {
        boolean [] repeticoesList = new boolean[7];

        if(segunda.isChecked())
            repeticoesList[0] = true;
        if(terca.isChecked())
            repeticoesList[1] = true;
        if(quarta.isChecked())
            repeticoesList[2] = true;
        if(quinta.isChecked())
            repeticoesList[3] = true;
        if(sexta.isChecked())
            repeticoesList[4] = true;
        if(sabado.isChecked())
            repeticoesList[5] = true;
        if(domingo.isChecked())
            repeticoesList[6] = true;

        return repeticoesList;
    }

    //INSERIR NOVO MEDICAMENTO NO FICHEIRO
    private void inserirNoFicheiro() {
        Ficheiro ficheiro = new Ficheiro();
        ficheiro.escreverFicheiro(medicamento);
    }

    //COLOCA DATAS CORRETAS NAS TEXTBOX
    private void setData(int year, int month, int day) {

        String sData = day+"-"+month+"-"+year;

        //CRIAR DATA E HORA
        try {
            if (change == true){
                editTextDataInicio.setText(sData);
                dateDataInicio = formatoData.parse(sData);
            }else {
                dateDataFim = formatoData.parse(sData);
                editTextDataFim.setText(sData);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


    //INNER CLASS QUE GERA UM FRAGMENTO EM QUE O UTILIZADOR PODE ESCOLHER A DATA
    class StartDatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        long millisecs;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            DatePickerDialog dialog = new DatePickerDialog(Data.this, this, startYear, startMonth, startDay);

            //INDICA QUE A DATA MINIMA É IGUAL OU SUPERIOR À DATA INICIO!
            if(change==false) {
                millisecs = dateDataInicio.getTime();
                dialog.getDatePicker().setMinDate(millisecs);
            }

            return dialog;
        }

        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            //POR ALGUM MOTIVO O NUMERO DO MES APARECE ATRASADO.
            setData(year, monthOfYear, dayOfMonth);
        }
    }
}


