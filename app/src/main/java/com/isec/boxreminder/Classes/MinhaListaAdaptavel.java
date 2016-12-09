package com.isec.boxreminder.Classes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.isec.boxreminder.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Miguel on 08-12-2016.
 */
public class MinhaListaAdaptavel extends ArrayAdapter<Medicamento> {

    Context context;
    ArrayList<Medicamento> lista;

    DateFormat hourFormat = new SimpleDateFormat("HH:mm");
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public MinhaListaAdaptavel(Context context, ArrayList<Medicamento> lista) {
        super(context, R.layout.listview_adapter, lista);
        this.context = context;
        this.lista = lista;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Medicamento medicamento;

        if(itemView == null)
            itemView = inflater.inflate(R.layout.listview_adapter, parent, false);

        medicamento = lista.get(position);

        TextView textViewNome = (TextView) itemView.findViewById(R.id.listNomeMedicamento);
        TextView textViewHora = (TextView) itemView.findViewById(R.id.listHoraMedicamento);
        TextView textViewData = (TextView) itemView.findViewById(R.id.listDataMedicamento);

        textViewNome.setText(medicamento.getNome());
        textViewHora.setText(hourFormat.format(medicamento.getHora()));
        textViewData.setText(dateFormat.format(medicamento.getDataInicio()));

        return itemView;
        //return super.getView(position, convertView, parent);
    }


}
