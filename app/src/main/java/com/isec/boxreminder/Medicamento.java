package com.isec.boxreminder;


import java.util.ArrayList;
import java.util.Date;

/**
 * Created by migue on 29/11/2016.
 */

public class Medicamento {
    String nome;
    Boolean RepeticoesMensal;
    Boolean[] RepeticoesSemanal;
    Date DataInicio;
    Date DataFim;
    Float Quantidade;
    String TipoQuantidade;
    String CaminhoGrav;

    public Medicamento(){}
    void setNome(String nome){
        this.nome = nome;
    }
    void setRepeticoes(int op, ArrayList<Boolean> ar){
        //????????????????????
        if(op>0&&op<=2){

        }
        if(op==3){

        }
    }
    void setDataInicio(Date d){
        DataInicio = d;
    }
    void setDataFim(Date d){
        DataFim = d;
    }
    public void setQuantidade(Float quantidade) {
        Quantidade = quantidade;
    }
    void setTipoQuantidade(String tq){
        TipoQuantidade = tq;
    }
    void setCaminhoGrav(String cg){
        CaminhoGrav=cg;
    }
    String getNome(){return nome;}
    String getTipoQuantidade(){return TipoQuantidade;}
    String getCaminhoGrav(){return CaminhoGrav;}
    Date getDataInicio(){return DataInicio;}
    Date getDataFim(){return DataFim;}
    Float getQuantidade(){return Quantidade;}

}
