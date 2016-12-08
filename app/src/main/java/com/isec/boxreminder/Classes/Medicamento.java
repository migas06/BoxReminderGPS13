package com.isec.boxreminder.Classes;


import android.text.format.Time;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by miguel on 29/11/2016.
 */

public class Medicamento implements Serializable{
    String nome;
    Date dataInicio;
    Date dataFim;
    Date hora;
    String quantidade;
    String tipoQuantidade;
    String caminhoGravacao;

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
}
