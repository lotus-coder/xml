package com.example.xml;

import java.util.Date;

public class New {

    private String titulo,link,descripcion,autor;

    public New (String titulo,String link, String descripcion,String autor){
        this.descripcion = descripcion;
        this.link  = link;
        this.titulo = titulo;
        this.autor = autor;
    }
    public New(){}
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
