package com.isec.boxreminder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Definicoes extends ActionBarActivity
{

    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definicoes);

        context = this;
        ((Button)findViewById(R.id.definicoesBotaoVoltar)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(context, MainActivity.class);
                startActivity(a);
            }
        });
    }
}
