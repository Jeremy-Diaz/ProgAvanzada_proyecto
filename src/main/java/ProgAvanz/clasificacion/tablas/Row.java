package ProgAvanz.clasificacion.tablas;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Row {
    // atributos
    private List<Double> datos;

    //constructores
    public Row(){
        datos = new ArrayList<>();
    }
    public Row(List<Double> data){
        datos = data;
    }

    // metodos

    public List<Double> getData(){
        return datos;
    }

    public void setData(List<Double> datos) {
        this.datos = datos;
    }
    public void setDataAt (int i, double dato){
        datos.set(i,dato);
    }
    public void addData (double dato){
        datos.add(dato);
    }



}
