package com.example.xml;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Temporal {

    //root -> prediccion -> dia...
    private Date fecha;
    private int precipitacion;  //prob_precipitacion
    private int cotanieve;      //cota_nieve_prov
    private String estadocielo; //estado_cielo@descripcion
    private String dirviento;   //viento -> direccion
    private int fuerzaviento;   //viento -> velocidad
    private int rachamax;       //racha_max
    private int tempmin;        //temperatura -> minima
    private int tempmax;        //temperatura -> maxima

    public Temporal() {}

    public String getFecha() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(fecha);
    }

    public void setFecha(String fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.fecha = sdf.parse(fecha);
        } catch (ParseException e) {
            this.fecha = null;
        }
    }

    public int getPrecipitacion() {
        return precipitacion;
    }

    public void setPrecipitacion(int precipitacion) {
        this.precipitacion = precipitacion;
    }

    public int getCotanieve() {
        return cotanieve;
    }

    public void setCotanieve(int cotanieve) {
        this.cotanieve = cotanieve;
    }

    public String getEstadocielo() {
        return estadocielo;
    }

    public void setEstadocielo(String estadocielo) {
        this.estadocielo = estadocielo;
    }

    public String getDirviento() {
        return dirviento;
    }

    public void setDirviento(String dirviento) {
        switch (dirviento) {
            case "N":
                this.dirviento = "Norte";
                break;
            case "E":
                this.dirviento = "Este";
                break;
            case "O":
                this.dirviento = "Oeste";
                break;
            case "S":
                this.dirviento = "Sur";
                break;
            case "SE":
                this.dirviento = "Sureste";
                break;
            case "SO":
                this.dirviento = "Suroeste";
                break;
            case "NE":
                this.dirviento = "Noreste";
                break;
            case "NO":
                this.dirviento = "Noroeste";
                break;
        }
    }

    public int getFuerzaviento() {
        return fuerzaviento;
    }

    public void setFuerzaviento(int fuerzaviento) {
        this.fuerzaviento = fuerzaviento;
    }

    public int getRachamax() {
        return rachamax;
    }

    public void setRachamax(int rachamax) {
        this.rachamax = rachamax;
    }

    public int getTempmin() {
        return tempmin;
    }

    public void setTempmin(int tempmin) {
        this.tempmin = tempmin;
    }

    public int getTempmax() {
        return tempmax;
    }

    public void setTempmax(int tempmax) {
        this.tempmax = tempmax;
    }
}
