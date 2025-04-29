package ProgAvanz.clasificacion.Modelo.herramientas;

import java.util.List;

public class Distancias {
    public  double getDistanciaEuclides(List<Double> origen, List<Double> sample){
        double sumatorio = 0;
        for (int i = 0; i < origen.size(); i++){
            sumatorio += getDistanciaAux(origen.get(i), sample.get(i));
        }
        return Math.sqrt(sumatorio);
    }
    public double getDistanciaAux(double dato1, double dato2){
        return Math.pow(dato1 - dato2, 2);
    }


}
