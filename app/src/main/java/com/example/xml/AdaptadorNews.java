package com.example.xml;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

class AdaptadorNews extends ArrayAdapter<New> {

    private New[] datosNoticia;

    public AdaptadorNews(@NonNull Context context, New[] datos) {
        super(context, R.layout.adaptador_noticias, datos);
        this.datosNoticia = datos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.adaptador_noticias, null);

        String titulo = datosNoticia[position].getTitulo();
        String autor = datosNoticia[position].getAutor();
        String descripcion = datosNoticia[position].getDescripcion();

        TextView tvTitulo = item.findViewById(R.id.titulo);
        tvTitulo.setText(titulo);
        TextView tvAutor = item.findViewById(R.id.autor);
        tvAutor.setText("Por: "+autor);
        String categoria = "";


        TextView tvDescripcion = item.findViewById(R.id.descripcion);
        tvDescripcion.setText(descripcion);
        TextView tvFecha = item.findViewById(R.id.fecha);

        return (item);
    }
}