package com.isec.boxreminder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InserirRegisto extends Activity
{
    Button next;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_registo);

        context = this;

        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, InserirQuantidade.class);
                startActivity(intent);
            }
        });
    }
}
