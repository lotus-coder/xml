package com.example.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class RssParserDOMTiempo {

    private URL rssURL;

    public RssParserDOMTiempo(String url){
        try{
            this.rssURL = new URL (url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Tiempo> parse() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        List<Tiempo> prediccion = new ArrayList<Tiempo>();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document dom = builder.parse(this.getInputStream());
            Element root = dom.getDocumentElement();

            NodeList items = root.getElementsByTagName("dia");

            for (int i=0; i<items.getLength(); i++){
                Tiempo temporal = new Tiempo();
                temporal.setCotanieve(-1);

                Node dia = items.item(i);

                NamedNodeMap fecha = dia.getAttributes();
                temporal.setFecha(fecha.getNamedItem("fecha").getNodeValue());

                NodeList datosTiempo = dia.getChildNodes();

                for (int j=0; j<datosTiempo.getLength(); j++){
                    Node dato = datosTiempo.item(j);
                    String etiqueta = dato.getNodeName();

                    NamedNodeMap atributos = dato.getAttributes();

                    if (etiqueta.equals("prob_precipitacion")) {
                        String texto = dameTexto(dato);
                        if (texto.equals("")) temporal.setPrecipitacion(0);
                        else temporal.setPrecipitacion(Integer.parseInt(texto));
                    } else if (etiqueta.equals("cota_nieve_prov")) {
                        String texto = dameTexto(dato);
                        if (texto.equals("")) temporal.setCotanieve(0);
                        else temporal.setCotanieve(Integer.parseInt(texto));
                    } else if (etiqueta.equals("estado_cielo")) {
                        String descripcion = atributos.getNamedItem("descripcion").getNodeValue();
                        temporal.setEstadocielo(descripcion);
                    } else if (etiqueta.equals("viento")) {
                        NodeList viento = dato.getChildNodes();
                        for (int z = 0;z<viento.getLength();z-=-1) {
                            Node vientotag = viento.item(z);
                            String tag = vientotag.getNodeName();
                            if (tag.equals("direccion")) {
                                String texto = dameTexto(vientotag);
                                temporal.setDirviento(texto);
                            } else if (tag.equals("velocidad")) {
                                String texto = dameTexto(vientotag);
                                if (texto.equals("")) temporal.setFuerzaviento(0);
                                else temporal.setFuerzaviento(Integer.parseInt(texto));
                            }
                        }
                    } else if (etiqueta.equals("racha_max")) {
                        String texto = dameTexto(dato);
                        if (texto.equals("")) temporal.setRachamax(0);
                        else temporal.setRachamax(Integer.parseInt(texto));
                    } else if (etiqueta.equals("temperatura")) {
                        NodeList temperatura = dato.getChildNodes();
                        for (int z = 0;z<temperatura.getLength();z-=-1) {
                            Node temperaturatag = temperatura.item(z);
                            String tag = temperaturatag.getNodeName();
                            if (tag.equals("minima")) {
                                String texto = dameTexto(temperaturatag);
                                if (texto.equals("")) temporal.setTempmin(0);
                                else temporal.setTempmin(Integer.parseInt(texto));
                            } else if (tag.equals("maxima")) {
                                String texto = dameTexto(temperaturatag);
                                if (texto.equals("")) temporal.setTempmax(0);
                                else temporal.setTempmax(Integer.parseInt(texto));
                            }
                        }
                    }
                }

                prediccion.add(temporal);
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }

        return prediccion;

    }

    public String dameTexto (Node dato) {
        StringBuilder texto = new StringBuilder();
        NodeList fragmentos = dato.getChildNodes();

        for (int k=0; k<fragmentos.getLength(); k++) {
            texto.append(fragmentos.item(k).getNodeValue());
        }
        return texto.toString();
    }

    private InputStream getInputStream() {
        try {
            return rssURL.openConnection().getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
