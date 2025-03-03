package ProgAvanz.clasificar_flores;

import java.util.List;


public class KNN {
    TableWithLabels tabla;
    public void train(TableWithLabels data) {
        tabla = data;
    }
    public Integer estimate(List<Double> sample) {
        double fila_distancia_minima = 0;

        for(Row datos : tabla.getRows()){
            for(int i =0; i < datos.getData().size(); i++){

            }
        }
        return 0;
    }
    /*private double getDistancia(List<Double> origen, List<Double> sample){

    }
    private double getDistanciaAux(double dato1, double dato2){
        return Math.sqrt(Math.pow(dato1 - dato2, 2));
    }*/
}