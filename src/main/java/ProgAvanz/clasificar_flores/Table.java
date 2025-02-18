package ProgAvanz.clasificar_flores;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Table {
    //atributos
    private List<String> headers;
    private List<Row> filas;

    //constructores;
    public Table(){
        headers = new LinkedList<>();
        filas = new LinkedList<>();
    }
    //metodos

    public List<String> getHeaders() {
        return headers;
    }

    public List<Row> getRows(){
        return filas;
    }
    public Row getRowAt(int num_fila){
        return filas.get(num_fila);

    }
    public List<Double> getColumAt(int num_atributo){
        List<Double> datos_columna = new LinkedList<>();
        for(Row fila: filas) {
            datos_columna.add(fila.getDatos().get(num_atributo));
        }
        return datos_columna;
    }
    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }

    public void addHeaders(List<String> headers){
        this.headers.addAll(headers);
    }

    public void addRow(Row fila){
        filas.add(fila);
    }

    public Row removeLastRow(){
        return filas.removeLast();
    }
    public Row removeRowAt(int num_fila ){
        return filas.remove(num_fila);
    }

}
