package com.example.xml;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class Ejer1Activity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public String url = "https://e00-marca.uecdn.es/rss/futbol/primera-division.xml";

    private ListView lista;
    private List<Noticia> noticias;
    private Noticia[] ntCs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejer1);
        lista = findViewById(R.id.lvNoticias);
    }

    public void cargarXMLConDOM(View v){
        TextView tv = findViewById(R.id.tvPulsar);
        tv.setVisibility(View.GONE);
        Button b = findViewById(R.id.btnCargar);
        b.setVisibility(View.GONE);
        CargarXmlTask tarea = new CargarXmlTask();
        tarea.execute(url);
    }

    private class CargarXmlTask extends AsyncTask<String,Integer,Boolean> {

        protected Boolean doInBackground(String... params) {
            RssParserDOM domParser = new RssParserDOM(params[0]);
            noticias = domParser.parse();
            return true;
        }

        protected void onPostExecute(Boolean result) {
            if (noticias != null) {
                ntCs = new Noticia[noticias.size()];
                ntCs = noticias.toArray(ntCs);
                AdaptadorNoticias adaptador = new AdaptadorNoticias(Ejer1Activity.this, ntCs);
                lista.setAdapter(adaptador);
                lista.setOnItemClickListener(Ejer1Activity.this);
            }
        }
    }

    public void onItemClick(AdapterView<?> parent, View view, int index, long arg3) {
        AdaptadorNoticias adaptador = new AdaptadorNoticias(Ejer1Activity.this, ntCs);
        Noticia noticia = adaptador.getItem(index);
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(noticia.getEnlace()));
        startActivity(browserIntent);
    }
}




