package com.isec.boxreminder;

import com.isec.boxreminder.Classes.Medicamento;
import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;

/**
 * Created by João André on 02/12/2016.
 */

public class MedicamentoUnitTest
{
    private Medicamento medicamento;

    @BeforeClass
    public void setUp()
    {
        medicamento = new Medicamento();
    }

    @Before
    public void nomeTestBefore()
    {
        medicamento.setNome("Medicamento Teste");
    }

    @Test
    public void nomeTest() throws AssertionError
    {
        assertEquals("Medicamento Teste", medicamento.getNome());
    }

    @Before
    public void dataInicioTestBefore()
    {
        medicamento.setDataInicio(new Date(2016, 12, 02));
    }

    @Test
    public void dataInicioTest() throws AssertionError
    {
        assertEquals(new Date(2016, 12, 2), medicamento.getDataInicio());
    }

    @Before
    public void dataFimTestBefore()
    {
        medicamento.setDataFim(new Date(2016, 12, 8));
    }

    @Test
    public void dataFimTest() throws AssertionError
    {
        assertEquals(new Date(2016, 12, 2), medicamento.getDataFim());
    }

    @Before
    public void horaTestBefore()
    {
        medicamento.setHora(new Date(0, 0, 0, 12, 30));
    }

    @Test
    public void horaTest() throws AssertionError
    {
        assertEquals(new Date(0, 0, 0, 12, 30), medicamento.getHora());
    }

    @Before
    public void quantidadeTestBefore()
    {
        medicamento.setQuantidade("0.19");
    }

    @Test
    public void quantidadeTest() throws AssertionError
    {
        assertEquals("0.19", medicamento.getQuantidade());
    }

    @Before
    public void tipoQuantidadeTestBefore()
    {
        medicamento.setTipoQuantidade("mg/L");
    }

    @Test
    public void tipoQuantidadeTest() throws AssertionError
    {
        assertEquals("mg/L", medicamento.getTipoQuantidade());
    }

    @Before
    public void caminhoGravacaoTestBefore()
    {
        medicamento.setCaminhoGravacao("/gravações/grav1.wav");
    }

    @Test
    public void caminhoGravacaoTest() throws AssertionError
    {
        assertEquals("/gravações/grav1.wav", medicamento.getCaminhoGravacao());
    }

    @AfterClass
    public void finish()
    {
        medicamento = null; // apagar qualquer referência para o objeto ( = apagar)
    }
}
