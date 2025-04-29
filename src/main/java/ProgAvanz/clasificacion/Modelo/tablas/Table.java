package ProgAvanz.clasificacion.Modelo.tablas;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Table {
    //atributos
    private List<String> headers;
    private List<Row> filas;

    //constructores;
    public Table(){
        headers = new ArrayList<>();
        filas = new ArrayList<>();
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
    public int getIndexOfRow(Row fila_elegida){
        for (int i = 0; i < filas.size(); i++){
            if (filas.get(i).getData().equals(fila_elegida.getData()))
                return i;
        }
        return -1;
    }
    public int getRowCount(){
        return filas.size();
    }
    public List<Double> getColumnAt(int num_atributo){
        List<Double> datos_columna = new LinkedList<>();
        for(Row fila: filas) {
            datos_columna.add(fila.getData().get(num_atributo));
        }
        return datos_columna;
    }
    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }

    public void addHeaders(List<String> headers){
        this.headers.addAll(headers);
    }
    public void addHeaders(String[] headers){
        for (String header: headers){
            this.headers.add(header);
        }
    }

    public void addHeader(String header){
        headers.add(header);
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
