package ProgAvanz.clasificacion.algoritmos;

import ProgAvanz.clasificacion.herramientas.Distancias;
import ProgAvanz.clasificacion.tablas.RowWithLabel;
import ProgAvanz.clasificacion.tablas.TableWithLabels;

import java.util.List;


public class KNN implements Algorithm<TableWithLabels, Integer,List<Double>> {
    private TableWithLabels tabla;
    public void train(TableWithLabels data) {
        tabla = data;
    }
    public Integer estimate(List<Double> sample) {
        Distancias dis = new Distancias();
        int fila_distancia_minima = 0;
        double distancia_minima = dis.getDistanciaEuclides(tabla.getRowAt(0).getData(),sample) ;
        for(int i = 1; i < tabla.getRows().size(); i++){
            RowWithLabel fila = tabla.getRowAt(i);
            double distancia = dis.getDistanciaEuclides(fila.getData(),sample);
            if (distancia <= distancia_minima) {
                fila_distancia_minima = i;
                distancia_minima = distancia;
            }
        }
        RowWithLabel fila = tabla.getRowAt(fila_distancia_minima);
        int estimacion = tabla.getLabelAsInteger(fila.getLabel());
        return estimacion;
    }

}