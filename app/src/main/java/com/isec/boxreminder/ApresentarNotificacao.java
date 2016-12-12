package com.isec.boxreminder;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.isec.boxreminder.Classes.Medicamento;

public class ApresentarNotificacao extends Activity
{
    private Medicamento medicamento;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apresentar_notificacao);

        medicamento = (Medicamento) getIntent().getSerializableExtra("medicamento");

        TextView notificacao = (TextView) findViewById(R.id.textViewNotificação);

        notificacao.setText("Hora de tomar "+medicamento.getNome());

        //TODO: layout minimalista                                                              -> FEITO
        //TODO: Alterar "textViewNotificação" para mostrar mensagem específica de medicamento   -> POR FAZER
        //TODO: reproduzir gravação que corresponde ao medicamento                              -> PREENCHER reproduzGravacao()
        //TODO: adicionar listeners para todos os botões físicos que o dispositivo tiver        -> FEITO

        reproduzGravacao();

        //OnClickListener partilhado por todos os componentes na atividade
        View.OnClickListener clickListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //sair assim que qualquer porção do ecrã seja premida
                finish();
            }
        };

        findViewById(R.id.activity_apresentar_notificacao).setOnClickListener(clickListener);
        findViewById(R.id.layout_background).setOnClickListener(clickListener);
        findViewById(R.id.textViewNotificação).setOnClickListener(clickListener);
    }

    private void reproduzGravacao()
    {
        //Cria thread para fazer Loop pela gravação a cada X segundos/minutos?

    }

    private void paraReproducao()
    {
        Toast.makeText(this, "ALARME DESLIGADO", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        //sair assim que qualquer botão tenha sido premido
        finish();

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onStop()
    {
        super.onStop();

        //ao sair, interromper a reprodução
        paraReproducao();
    }
}
