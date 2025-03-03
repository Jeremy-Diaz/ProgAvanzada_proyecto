package ProgAvanz.clasificar_flores;

import java.util.List;


public class KNN {
    TableWithLabels tabla;
    public void train(TableWithLabels data) {
        tabla = data;
    }
    public Integer estimate(List<Double> sample) {
        int fila_distancia_minima = 0;
        double distancia_minima = getDistancia(tabla.getRows().get(0).getData(),sample) ;
        for(int i = 1; i < tabla.getRows().size(); i++){
            RowWithLabel fila = (RowWithLabel) tabla.getRows().get(i);
            double distancia = getDistancia(fila.getData(),sample);
            if (distancia < fila_distancia_minima) {
                fila_distancia_minima = i;
                distancia_minima = distancia;
            }
        }
        RowWithLabel fila = (RowWithLabel) tabla.getRows().get(fila_distancia_minima);
        return tabla.getLabelAsInteger(fila.getLabel());
    }
    private double getDistancia(List<Double> origen, List<Double> sample){
        double sumatorio = 0;
        for (int i = 0; i < origen.size(); i++){
            sumatorio += getDistanciaAux(origen.get(i), sample.get(i));
        }
        return Math.sqrt(sumatorio);
    }
    private double getDistanciaAux(double dato1, double dato2){
        return Math.pow(dato1 - dato2, 2);
    }
}