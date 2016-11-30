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
    Boolean RepeticoesMensal;
    Boolean[] RepeticoesSemanal;
    Date DataInicio;
    Date DataFim;
    Date Hora;
    String Quantidade;
    String TipoQuantidade;
    String CaminhoGrav;

    public Medicamento(){

    }

    public Date getHora() {
        return Hora;
    }

    public void setHora(Date hora) {
        Hora = hora;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getRepeticoesMensal() {
        return RepeticoesMensal;
    }

    public void setRepeticoesMensal(Boolean repeticoesMensal) {
        RepeticoesMensal = repeticoesMensal;
    }

    public Boolean[] getRepeticoesSemanal() {
        return RepeticoesSemanal;
    }

    public void setRepeticoesSemanal(Boolean[] repeticoesSemanal) {
        RepeticoesSemanal = repeticoesSemanal;
    }

    public Date getDataInicio() {
        return DataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        DataInicio = dataInicio;
    }

    public Date getDataFim() {
        return DataFim;
    }

    public void setDataFim(Date dataFim) {
        DataFim = dataFim;
    }

    public String getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(String quantidade) {
        Quantidade = quantidade;
    }

    public String getTipoQuantidade() {
        return TipoQuantidade;
    }

    public void setTipoQuantidade(String tipoQuantidade) {
        TipoQuantidade = tipoQuantidade;
    }

    public String getCaminhoGrav() {
        return CaminhoGrav;
    }

    public void setCaminhoGrav(String caminhoGrav) {
        CaminhoGrav = caminhoGrav;
    }
}
