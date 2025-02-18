package ProgAvanz.clasificar_flores;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Row {
    // atributos
    private List<Double> datos;

    //constructores
    public Row(){
        datos = new LinkedList<>();
    }

    // metodos

    public List<Double> getDatos(){
        return datos;
    }

    public void setData(List<Double> datos) {
        this.datos = datos;
    }
    public void addData (double dato){
        datos.add(dato);
    }

}
