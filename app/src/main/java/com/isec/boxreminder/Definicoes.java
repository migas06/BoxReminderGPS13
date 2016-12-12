package com.isec.boxreminder;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.isec.boxreminder.Classes.Ficheiro;

public class Definicoes extends Activity
{
    Context context;
    long contacto;
    Ficheiro ficheiro = new Ficheiro();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definicoes);

        MainActivity.getActivityStack().add(this);

        final EditText mEditText = (EditText) findViewById(R.id.editTextContacto);
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBoxVoz);

        EditText editTextContacto = (EditText) findViewById(R.id.editTextContacto) ;

        editTextContacto.setText( ficheiro.lerContacto());

        context = this;
        ((Button)findViewById(R.id.definicoesBotaoVoltar)).setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {

                try {
                    contacto = Long.parseLong(mEditText.getText().toString());
                }catch (Exception e){
                    Log.d("Definicoes","not a number " + e);
                    Toast.makeText(context,"Introduza valores númericos", Toast.LENGTH_SHORT).show();
                }

                if((contacto>=200000000 && contacto<=969999999)){
                    ficheiro.delete();
                    ficheiro.escreverFicheiroContacto(contacto);
                    Toast.makeText(context,"Operação bem sucedida", Toast.LENGTH_SHORT).show();

                    //fechar esta atividade em vez de abrir uma nova MainActivity
                    MainActivity.closeActivitiesInStack();
                }
                else{
                    AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(context);

                    dlgAlert.setMessage("Contacto de Emergencia Errado!");
                    dlgAlert.setTitle("Error Contacto");
                    dlgAlert.setPositiveButton("OK", null);
                    dlgAlert.setCancelable(true);
                    dlgAlert.create().show();
                }
            }
        });

        checkBox.setChecked(new Ficheiro().vozEstaAtiva());
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                Ficheiro ficheiro = new Ficheiro();
                ficheiro.ativarVoz(isChecked);
            }
        });
    }

}
