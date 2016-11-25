package com.isec.boxreminder;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Definicoes extends Activity
{
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definicoes);

        final EditText mEditText = (EditText) findViewById(R.id.editTextContacto);


        context = this;
        ((Button)findViewById(R.id.definicoesBotaoVoltar)).setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                double contacto = Double.parseDouble(mEditText.getText().toString());
                if((contacto>=100000000&&contacto<=999999999)){

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
