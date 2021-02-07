package com.example.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
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

public class RssParserDOMNews {

    private URL rssURL;

    public RssParserDOMNews(String url){
        try{
            this.rssURL = new URL (url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<New> parse() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        List<New> noticias = new ArrayList<New>();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document dom = builder.parse(this.getInputStream());

            Element root = dom.getDocumentElement();

            NodeList items = root.getElementsByTagName("item");

            for (int i=0; i < items.getLength(); i++){
                New noticia = new New();

                Node item = items.item(i);

                NodeList datosNoticia = item.getChildNodes();

                String categorias = "";
                for (int j=0; j<datosNoticia.getLength(); j++){
                    Node dato = datosNoticia.item(j);
                    String etiqueta = dato.getNodeName();

                    if (etiqueta.equals("title")){
                        String texto = dameTexto(dato);
                        noticia.setTitulo(texto);
                    }else if (etiqueta.equals("link")){
                        String texto = dato.getFirstChild().getNodeValue();
                        noticia.setLink(texto);
                    }else if (etiqueta.equals("description")){
                        String texto = dato.getFirstChild().getNodeValue();
                        noticia.setDescripcion(texto);
                    }else if (etiqueta.equals("author")){
                        if(dato.getFirstChild() != null){
                        String texto = dato.getFirstChild().getNodeValue();
                        noticia.setAutor(texto);}
                    }
                }
                noticias.add(noticia);
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }

        return noticias;

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
