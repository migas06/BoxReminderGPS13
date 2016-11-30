package com.isec.boxreminder;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import com.isec.boxreminder.Classes.Medicamento;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.R.attr.onClick;

public class Data extends Activity {

    Calendar c     = Calendar.getInstance();
    int startYear  = c.get(Calendar.YEAR);
    int startMonth = c.get(Calendar.MONTH);
    int startDay   = c.get(Calendar.DAY_OF_MONTH);

    //TRUE PARA DATA INICIAL
    //FALSE PARA DATA FINAL
    boolean change;

    ImageView dataInicio;
    ImageView dataFim;

    EditText editTextDataInicio;
    EditText editTextDataFim;
    EditText editTextHora;
    EditText editTextMin;

    Button next;
    Context context = this;

    Medicamento medicamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        //RECEBER O MEDICAMENTO DA ATIVIDADE ANTERIOR
        medicamento = (Medicamento) getIntent().getSerializableExtra("medicamento");

        //Declaração de variaveis
        editTextDataInicio = (EditText) findViewById(R.id.editTextDataInicio);
        editTextDataFim    = (EditText) findViewById(R.id.editTextDataFim);
        editTextHora       = (EditText) findViewById(R.id.horas);
        editTextMin        = (EditText) findViewById(R.id.minutos);

        editTextDataInicio.setText(startDay + " - " + startMonth + " - " + startYear);
        editTextDataInicio.setText(startDay + " - " + startMonth + " - " + startYear);

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


        /**
         *
         * CRIAR MECANISMO PARA COLOCAR DATA INICIO SEMPRE ANTERIOR A DATA FIM
         * sugestão, criar variaves data para datafim e datainicio respetivamente
         *
         * */

        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //CODIGO PARA ADICIONAR DADOS AO MEDICAMENTO
                addAoMedicamento();
                inserirNoFicheiro();

                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void addAoMedicamento() {

        //CRIAR DATA E HORA
        SimpleDateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
        try {
            Date dataInicio = formatoData.parse(editTextDataInicio.getText().toString());
            Date dataFim = formatoData.parse(editTextDataFim.getText().toString());
            Date hora = formatoHora.parse(editTextHora.getText().toString() + ":" + editTextMin.getText().toString());

            medicamento.setDataInicio(dataInicio);
            medicamento.setDataFim(dataFim);
            medicamento.setHora(hora);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    //INSERIR NOVO MEDICAMENTO NO FICHEIRO
    private void inserirNoFicheiro() {
        /*TODO HERE*/
    }

    //COLOCA DATAS CORRETAS NAS TEXTBOX
    private void update() {
        if (change == true)
            editTextDataInicio.setText(startDay + " - " + startMonth + " - " + startYear);
        else
            editTextDataFim.setText(startDay + " - " + startMonth + " - " + startYear);
    }


    //INNER CLASS QUE GERA UM FRAGMENTO EM QUE O UTILIZADOR PODE ESCOLHER A DATA
    class StartDatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener{

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            DatePickerDialog dialog = new DatePickerDialog(Data.this, this, startYear, startMonth, startDay);
            return dialog;
        }

        public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
            startYear = year;
            startMonth = monthOfYear+1; //POR ALGUM MOTIVO O NUMERO DO MES APARECE ATRASADO.
            startDay = dayOfMonth;
            update();
        }
    }
}


