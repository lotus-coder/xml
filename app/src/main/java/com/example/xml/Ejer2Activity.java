package com.example.xml;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class Ejer2Activity extends AppCompatActivity {

    public String url = "http://www.aemet.es/xml/municipios/localidad_01059.xml";

    private List<Tiempo> temporal;

    private Button btnHoy,btnManana,btnPasado;

    private TextView txtFecha,txtPrecipitacion,txtCotanieve,txtCielo,txtDirviento,txtRacha,txtRachamax,txtMin,txtMax,txtMostrar;

    private boolean pClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejer2);

        btnHoy = findViewById(R.id.btnHoy);
        btnManana = findViewById(R.id.btnManana);
        btnPasado = findViewById(R.id.btnPasado);

        txtFecha = findViewById(R.id.tvFecha);
        txtPrecipitacion = findViewById(R.id.tvPrecipitacion);
        txtCotanieve = findViewById(R.id.tvCotanieve);
        txtCielo = findViewById(R.id.tvCielo);
        txtDirviento = findViewById(R.id.tvDir);
        txtRacha = findViewById(R.id.tvRacha);
        txtRachamax = findViewById(R.id.tvRachamax);
        txtMin = findViewById(R.id.tvMin);
        txtMax = findViewById(R.id.tvMax);

        txtMostrar = findViewById(R.id.tvMostrar);

        pClick = true;

        CargaXmlTask tarea = new CargaXmlTask();
        tarea.execute(url);

    }

    public void cargarXMLConDOM(View v) {
        CargaXmlTask tarea = new CargaXmlTask();
        tarea.execute(url);
    }

    private class CargaXmlTask extends AsyncTask<String,Integer,Boolean> {

        protected Boolean doInBackground(String... params) {
            RssParserDOMtemperatura domParser = new RssParserDOMtemperatura(params[0]);
            temporal = domParser.parse();
            return true;
        }

        protected void onPostExecute(Boolean result) {
            if (temporal != null) {

                int lon = temporal.size();

                if (lon>=1) btnHoy.setEnabled(true);
                if (lon>=2) btnManana.setEnabled(true);
                if (lon>=3) btnPasado.setEnabled(true);

            }
        }
    }

    public void cargarDatos(View view) {
        if (pClick) {
            txtMostrar.setVisibility(View.GONE);

            txtFecha.setVisibility(View.VISIBLE);
            txtPrecipitacion.setVisibility(View.VISIBLE);
            txtCotanieve.setVisibility(View.VISIBLE);
            txtCielo.setVisibility(View.VISIBLE);
            txtDirviento.setVisibility(View.VISIBLE);
            txtRacha.setVisibility(View.VISIBLE);
            txtRachamax.setVisibility(View.VISIBLE);
            txtMin.setVisibility(View.VISIBLE);
            txtMax.setVisibility(View.VISIBLE);

            pClick = false;
        }

        Tiempo temporal;
        switch (view.getId()) {
            case R.id.btnManana:
                temporal = this.temporal.get(1);
                break;
            case R.id.btnPasado:
                temporal = this.temporal.get(2);
                break;
            default:
                temporal = this.temporal.get(0);
        }
        txtFecha.setText(getResources().getString(R.string.fecha)+" "+temporal.getFecha());
        txtPrecipitacion.setText(getResources().getString(R.string.precipitacion)+" "+temporal.getPrecipitacion()+"%");
        txtCotanieve.setText(getResources().getString(R.string.cotanieve)+" "+temporal.getCotanieve());
        txtCielo.setText(getResources().getString(R.string.cielo)+" "+temporal.getEstadocielo());
        txtDirviento.setText(getResources().getString(R.string.dirviento)+" "+temporal.getDirviento());
        txtRacha.setText(getResources().getString(R.string.racha)+" "+temporal.getFuerzaviento());
        txtRachamax.setText(getResources().getString(R.string.rachamax)+" "+temporal.getRachamax());
        txtMin.setText(getResources().getString(R.string.min)+" "+temporal.getTempmin());
        txtMax.setText(getResources().getString(R.string.max)+" "+temporal.getTempmax());

    }
}
