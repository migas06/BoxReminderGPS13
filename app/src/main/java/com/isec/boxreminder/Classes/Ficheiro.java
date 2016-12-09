package com.isec.boxreminder.Classes;

import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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
    String caminhoContacto = Environment.getExternalStorageDirectory().getAbsolutePath()+"/MyContact.obj";

    long contacto;

    public String lerFicheiro()  {

        try{
            InputStream file = new FileInputStream(caminho);
            InputStream inputStream = new BufferedInputStream(file);
            ObjectInput objectInput = new ObjectInputStream(inputStream);

            lista = (ArrayList<Medicamento>)objectInput.readObject();

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
        String sContacto;
        try{
            InputStream file = new FileInputStream(caminhoContacto);
            InputStream inputStream = new BufferedInputStream(file);
            ObjectInput objectInput = new ObjectInputStream(inputStream);

            sContacto = (String) objectInput.readObject();
            contacto = Long.parseLong(sContacto);

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

    public void escreverFicheiroContacto(long contacto){
        try{
            OutputStream fOutputStream = new FileOutputStream(caminhoContacto);
            OutputStream outputStream = new BufferedOutputStream(fOutputStream);
            ObjectOutput objectOutput = new ObjectOutputStream(outputStream);


            objectOutput.writeObject(contacto+"");
            objectOutput.close();

        }catch (IOException e){
            Log.d("FICHEIRO", "ERRO NO FICHEIRO");
        }
    }

    public void delete (){
        File file = new File(caminhoContacto);

        if(file.exists())
            file.delete();
    }

    public void escreverFicheiro(){

        try{
            OutputStream file = new FileOutputStream(caminho);
            OutputStream outputStream = new BufferedOutputStream(file);
            ObjectOutput objectOutput = new ObjectOutputStream(outputStream);

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
