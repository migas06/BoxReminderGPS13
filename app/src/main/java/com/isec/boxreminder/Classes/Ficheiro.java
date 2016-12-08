package com.isec.boxreminder.Classes;

import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Miguel on 02-12-2016.
 */

public class Ficheiro {

    ArrayList<Medicamento> lista;

    String caminho = Environment.getExternalStorageDirectory().getAbsolutePath()+"/MyMeds.txt";
    String caminhoContacto = Environment.getExternalStorageDirectory().getAbsolutePath()+"/MyContact.txt";

    DateFormat hourFormat = new SimpleDateFormat("HH:mm");
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    String linha;

    public String lerFicheiro()  {

        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(caminho));
            while ((linha = bufferedReader.readLine()) != null) {
                Log.d("READ", linha);
            }
            bufferedReader.close();

        } catch (FileNotFoundException e){
            Log.d("FICHEIRO", "ERRO, FICHEIRO NÃO EXISTE");
            return "nofile";
        } catch (IOException e){
            Log.d("FICHEIRO", "ERRO NO FICHEIRO DE TEXTO");
        }
        return "sucesso";
    }

    public String lerContacto()  {

        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(caminhoContacto));
            linha = bufferedReader.readLine();
            bufferedReader.close();

        } catch (FileNotFoundException e){
            Log.d("FICHEIRO", "ERRO NO FICHEIRO DE TEXTO");
            return "nofile";
        } catch (IOException e){
            Log.d("FICHEIRO", "ERRO NO FICHEIRO DE TEXTO");
        }
        return linha;
    }

    public void escreverFicheiroContacto(long contacto){
        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(caminhoContacto));
            linha = contacto + "\n";

            bufferedWriter.append(linha + "\n");
            bufferedWriter.close();
        }catch (IOException e){
            Log.d("FICHEIRO", "ERRO NO FICHEIRO DE TEXTO");
        }
    }

    public void escreverFicheiro(Medicamento medicamento){

        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(caminho, true));
            linha = medicamento.getNome().toString() + "\n";
            linha += medicamento.getQuantidade().toString() + " ";
            linha += medicamento.getTipoQuantidade().toString() + "\n";
            if(medicamento.getCaminhoGravacao().contains(".3gp")){
                linha += medicamento.getCaminhoGravacao().toString() + "\n";
            }else
                linha += "sem toque de notificação";
            for(int i=0; i<7; i++)
                linha += medicamento.getRepeticao(i) + " ";

            linha += "\n"+ hourFormat.format(medicamento.getHora()) + "\n";
            linha += dateFormat.format(medicamento.getDataInicio()) + " " + dateFormat.format(medicamento.getDataFim()) +"\n\n";


            bufferedWriter.append(linha);
            bufferedWriter.close();
        }catch (IOException e){
            Log.d("FICHEIRO", "ERRO NO FICHEIRO DE TEXTO");
        }
    }
}
