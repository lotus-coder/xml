package com.example.xml;

import java.util.Arrays;

public class Noticia {

    private String descripcion,enlace,autor,fecha,titulo;
    private String[] categoria;


    public Noticia(String titulo,String autor,String enlace,String[] categoria,String descripcion,String fecha) {
        this.titulo = titulo;
        this.autor = autor;
        this.enlace = enlace;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }
    public Noticia(){

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }

    public String[] getCategoria() {
        return categoria;
    }

    public void setCategoria(String[] categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Noticia{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", enlace='" + enlace + '\'' +
                ", categoria=" + Arrays.toString(categoria) +
                ", descripcion='" + descripcion + '\'' +
                ", fecha='" + fecha + '\'' +
                '}';
    }
}
