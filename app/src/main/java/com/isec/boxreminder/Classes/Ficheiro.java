package com.isec.boxreminder.Classes;

import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Miguel on 02-12-2016.
 */

public class Ficheiro {

    ArrayList<Medicamento> lista = new ArrayList<Medicamento>();

    String caminho = Environment.getExternalStorageDirectory().getAbsolutePath()+"/MyMeds.obj";
    String caminhoContacto = Environment.getExternalStorageDirectory().getAbsolutePath()+"/MyContact.txt";

    String linha;

    public String lerFicheiro()  {

        try{
            InputStream file = new FileInputStream(caminho);
            InputStream inputStream = new BufferedInputStream(file);
            ObjectInput objectInput = new ObjectInputStream(inputStream);

            lista = (ArrayList<Medicamento>)objectInput.readObject();
            System.out.println(lista.get(0).getNome());


        } catch (FileNotFoundException e){
            Log.d("FICHEIRO", "ERRO, FICHEIRO NÃO EXISTE");
            return "nofile";
        } catch (IOException e){
            Log.d("FICHEIRO", "ERRO NO FICHEIRO DE TEXTO");
        } catch (ClassNotFoundException e) {
            Log.d("FICHEIRO", "CLASSE NAO CONHECIDA");
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

    /*public void escreverFicheiro(Medicamento medicamento){

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
    }*/

    public void escreverFicheiro(Medicamento medicamento){

        lerFicheiro();

        try{
            OutputStream file = new FileOutputStream(caminho);
            OutputStream outputStream = new BufferedOutputStream(file);
            ObjectOutput objectOutput = new ObjectOutputStream(outputStream);

            lista.add(medicamento);

            objectOutput.writeObject(lista);
            objectOutput.close();

        }catch (IOException e){
            Log.d("FICHEIRO", "ERRO NO FICHEIRO");
        }
    }

    public ArrayList<Medicamento> getLista() {
        return lista;
    }
}
