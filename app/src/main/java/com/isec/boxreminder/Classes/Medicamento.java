package com.isec.boxreminder.Classes;


import android.text.format.Time;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by miguel on 29/11/2016.
 */

public class Medicamento implements Serializable{
    private boolean editar;
    private String nome;
    private Date dataInicio;
    private Date dataFim;
    private Date hora;
    private String quantidade;
    private String tipoQuantidade;
    private String caminhoGravacao;
    private int id;

    private static int contador = 0;

    public Medicamento()
    {
        id = contador++;
    }

    public boolean isEditar() {return editar;}

    public void setEditar(boolean editar) {
        this.editar = editar;
    }

    boolean [] repeticao = new boolean[7];

    //GETTERS AND SETTERS

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getTipoQuantidade() {
        return tipoQuantidade;
    }

    public void setTipoQuantidade(String tipoQuantidade) {
        this.tipoQuantidade = tipoQuantidade;
    }

    public String getCaminhoGravacao() {
        return caminhoGravacao;
    }

    public void setCaminhoGravacao(String caminhoGravacao) {
        this.caminhoGravacao = caminhoGravacao;
    }

    public boolean getRepeticao( int position) {
        return repeticao[position];
    }

    public void setRepeticao(boolean[] repeticao) {
            this.repeticao = repeticao;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(!(obj instanceof Medicamento))
            return false;

        Medicamento med = (Medicamento) obj;

        return id == med.id;
    }
}
