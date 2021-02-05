package com.example.xml;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class Ejercicio2Activity extends AppCompatActivity {

    public String url = "http://www.aemet.es/xml/municipios/localidad_01059.xml";

    private List<Temporal> temporal;

    private Button hoy,manana,pasado;

    private TextView fecha,precipitacion,cotanieve,cielo,dirviento,racha,rachamax,min,max,mostrar;

    private boolean primerclick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejer2);

        hoy = findViewById(R.id.bHoy);
        manana = findViewById(R.id.bManana);
        pasado = findViewById(R.id.bPasado);

        fecha = findViewById(R.id.tvFecha);
        precipitacion = findViewById(R.id.tvPrecipitacion);
        cotanieve = findViewById(R.id.tvCotanieve);
        cielo = findViewById(R.id.tvCielo);
        dirviento = findViewById(R.id.tvDir);
        racha = findViewById(R.id.tvRacha);
        rachamax = findViewById(R.id.tvRachamax);
        min = findViewById(R.id.tvMin);
        max = findViewById(R.id.tvMax);

        mostrar = findViewById(R.id.tvMostrar);

        primerclick = true;

        CargarXmlTask tarea = new CargarXmlTask();
        tarea.execute(url);

    }

    public void cargarXMLConDOM(View v) {
        //Carga del XML mediante tarea Asincrona
        CargarXmlTask tarea = new CargarXmlTask();
        tarea.execute(url);
    }

    private class CargarXmlTask extends AsyncTask<String,Integer,Boolean> {

        protected Boolean doInBackground(String... params) {
            RssParserDOMtemperatura domParser = new RssParserDOMtemperatura(params[0]);
            temporal = domParser.parse();
            return true;
        }

        protected void onPostExecute(Boolean result) {
            if (temporal != null) {

                int lon = temporal.size();

                if (lon>=1) hoy.setEnabled(true);
                if (lon>=2) manana.setEnabled(true);
                if (lon>=3) pasado.setEnabled(true);

            }
        }
    }

    public void cargarDatos(View view) {
        if (primerclick) {
            mostrar.setVisibility(View.GONE);

            fecha.setVisibility(View.VISIBLE);
            precipitacion.setVisibility(View.VISIBLE);
            cotanieve.setVisibility(View.VISIBLE);
            cielo.setVisibility(View.VISIBLE);
            dirviento.setVisibility(View.VISIBLE);
            racha.setVisibility(View.VISIBLE);
            rachamax.setVisibility(View.VISIBLE);
            min.setVisibility(View.VISIBLE);
            max.setVisibility(View.VISIBLE);

            primerclick = false;
        }

        Temporal temporal;
        switch (view.getId()) {
            case R.id.bManana:
                temporal = this.temporal.get(1);
                break;
            case R.id.bPasado:
                temporal = this.temporal.get(2);
                break;
            default:
                temporal = this.temporal.get(0);
        }
        fecha.setText(getResources().getString(R.string.fecha)+" "+temporal.getFecha());
        precipitacion.setText(getResources().getString(R.string.precipitacion)+" "+temporal.getPrecipitacion()+"%");
        cotanieve.setText(getResources().getString(R.string.cotanieve)+" "+temporal.getCotanieve());
        cielo.setText(getResources().getString(R.string.cielo)+" "+temporal.getEstadocielo());
        dirviento.setText(getResources().getString(R.string.dirviento)+" "+temporal.getDirviento());
        racha.setText(getResources().getString(R.string.racha)+" "+temporal.getFuerzaviento());
        rachamax.setText(getResources().getString(R.string.rachamax)+" "+temporal.getRachamax());
        min.setText(getResources().getString(R.string.min)+" "+temporal.getTempmin());
        max.setText(getResources().getString(R.string.max)+" "+temporal.getTempmax());

    }
}
