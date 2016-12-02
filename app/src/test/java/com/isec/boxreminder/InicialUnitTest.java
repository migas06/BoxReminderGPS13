package com.isec.boxreminder;

import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by João André on 02/12/2016.
 */

//public class InicialUnitTest
//{
//    private static Activity inicial;
//
//    @BeforeClass
//    public static void setUp()
//    {
//        inicial = new Inicial();
//    }
//
//    @Before
//    public void definicoesTestBefore()
//    {
//        EditText mEditText = (EditText) inicial.findViewById(R.id.editTextContacto);
//
//        mEditText.setText("919666333"); //número de telemóvel aleatório entre 200000000 e 969999999
//
//        long contacto = Long.parseLong(mEditText.getText().toString());
//
//        ((Button)inicial.findViewById(R.id.definicoesBotaoSeguinte)).performClick();
//    }
//
//    @Test
//    public void definicoesTest() throws AssertionError
//    {
//        assertTrue(inicial.isFinishing());
//    }
//
//    @AfterClass
//    public static void finish()
//    {
//        inicial = null;
//    }
//}
