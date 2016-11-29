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

import java.util.Calendar;

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

    Button next;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        //Declaração de variaveis
        editTextDataInicio = (EditText) findViewById(R.id.editTextDataInicio);
        editTextDataFim    = (EditText) findViewById(R.id.editTextDataFim);

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

                //CODIGO PARA CRIAR UM MEDICAMENTO
                //INSERIR NO FICHEIRO DE TEXTO

                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


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


