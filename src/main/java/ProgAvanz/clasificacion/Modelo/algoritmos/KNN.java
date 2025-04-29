package ProgAvanz.clasificacion.Modelo.algoritmos;

import ProgAvanz.clasificacion.Modelo.Interfaces.Algorithm;
import ProgAvanz.clasificacion.Modelo.Interfaces.Distance;
import ProgAvanz.clasificacion.Modelo.herramientas.Distancias;
import ProgAvanz.clasificacion.Modelo.herramientas.EuclideanDIstance;
import ProgAvanz.clasificacion.Modelo.tablas.RowWithLabel;
import ProgAvanz.clasificacion.Modelo.tablas.TableWithLabels;


import java.util.List;


public class KNN implements Algorithm<TableWithLabels, Integer,List<Double>> {
    private TableWithLabels tabla;
    private Distance distancia;

    public KNN () {
        tabla = new TableWithLabels();
        distancia = new EuclideanDIstance();
    }
    public KNN (Distance  type_distance) {
        tabla = new TableWithLabels();
        distancia = type_distance;
    }
    public void train(TableWithLabels data) {
        tabla = data;
    }
    public Integer estimate(List<Double> sample) {
        Distancias dis = new Distancias();
        int fila_distancia_minima = 0;
        double distancia_minima = distancia.calculateDistance(tabla.getRowAt(0).getData(),sample) ;
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