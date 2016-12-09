package com.isec.boxreminder;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

        final EditText mEditText = (EditText) findViewById(R.id.editTextContacto);

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
                    Intent intent = new Intent(context, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();

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
    }

}
