package com.isec.boxreminder;

import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by João André on 02/12/2016.
 */

public class DefinicoesUnitTest
{
    private static Activity definicoes;

    @BeforeClass
    public static void setUp()
    {
        definicoes = new Definicoes();
    }

    @Before
    public void definicoesTestBefore()
    {
        EditText mEditText = (EditText) definicoes.findViewById(R.id.editTextContacto);

        mEditText.setText("919666333"); //número de telemóvel aleatório entre 200000000 e 969999999

        long contacto = Long.parseLong(mEditText.getText().toString());

        ((Button)definicoes.findViewById(R.id.definicoesBotaoVoltar)).performClick();
    }

    @Test
    public void definicoesTest() throws AssertionError
    {
        assertTrue(definicoes.isFinishing());
    }

    @AfterClass
    public static void finish()
    {
        definicoes = null;
    }
}
