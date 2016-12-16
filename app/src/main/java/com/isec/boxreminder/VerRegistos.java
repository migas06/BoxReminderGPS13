package com.isec.boxreminder;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.isec.boxreminder.Classes.Alarme;
import com.isec.boxreminder.Classes.Ficheiro;
import com.isec.boxreminder.Classes.Medicamento;
import com.isec.boxreminder.Classes.MinhaListaAdaptavel;
import com.isec.boxreminder.Classes.Pesquisa;

import java.util.ArrayList;

public class VerRegistos extends Activity {

    Ficheiro ficheiro = new Ficheiro();

    ArrayList<Medicamento> lista = new ArrayList<Medicamento>();
    ArrayList<Medicamento> medsPesquisa;

    ListView listView;

    Context context = this;

    private SearchView pesquisa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_registos);

        pesquisa = (SearchView) findViewById(R.id.pesquisaRegisto);

        pesquisa.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if(!hasFocus)
                {
                    pesquisa.setQuery("", false);
                    pesquisa.setQueryHint("Pesquisar medicamento...");
                    lista  = ficheiro.lerFicheiro();

                    if(lista != null)
                        povoarListView();
                }
            }
        });

                pesquisa.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        executaPesquisa(newText);
                        return true;
                    }
                });

        pesquisa.setOnCloseListener(new SearchView.OnCloseListener()
        {
            @Override
            public boolean onClose()
            {
                pesquisa.setQuery("", false);
                pesquisa.setQueryHint("Pesquisar medicamento...");
                lista  = ficheiro.lerFicheiro();

                if(lista != null)
                    povoarListView();
                return true;
            }
        });
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        pesquisa.setQuery("", false);
        pesquisa.setQueryHint("Pesquisar medicamento...");

        lista  = ficheiro.lerFicheiro();

        if(lista != null)
            povoarListView();
    }

    private void povoarListView() {
        ArrayAdapter<Medicamento> adapta = new MinhaListaAdaptavel(context, lista);

        listView = (ListView) findViewById(R.id.listRegisto);
        listView.setAdapter(adapta);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(context, DetalhesMedicamento.class);
                intent.putExtra("medicamento", lista.get(i));
                startActivity(intent);
            }
        });
    }

    private void executaPesquisa(String query)
    {
        medsPesquisa = Pesquisa.pesquisaPorNome(query, lista);

        if(medsPesquisa == null)
            return;

        TextView tvNoMatches = (TextView) findViewById(R.id.tvNoSearchMatches);
        listView = (ListView) findViewById(R.id.listRegisto);

        if(!medsPesquisa.isEmpty())
        {
            tvNoMatches.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);

            ArrayAdapter<Medicamento> adapta = new MinhaListaAdaptavel(context, medsPesquisa);

            listView.setAdapter(adapta);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(context, DetalhesMedicamento.class);
                    intent.putExtra("medicamento", medsPesquisa.get(i));
                    startActivity(intent);
                }
            });
        }
        else
        {
            listView.setVisibility(View.GONE);
            tvNoMatches.setVisibility(View.VISIBLE);

            tvNoMatches.setText("Medicamento \"" + query + "\" não encontrado");
        }

    }
}
