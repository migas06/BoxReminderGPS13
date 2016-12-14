package com.isec.boxreminder.Classes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by João André on 14/12/2016.
 */

public class Pesquisa
{
    public static ArrayList<Medicamento> pesquisaPorNome(String nome, List<Medicamento> listaMedicamentos)
    {
        if(listaMedicamentos.isEmpty())
            return null;

        ArrayList<Medicamento> meds = new ArrayList();

        for(Medicamento med : listaMedicamentos)
            if(med.getNome().toLowerCase().startsWith(nome.toLowerCase()))
                meds.add(med);

        return meds;
    }
}
