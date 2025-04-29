package ProgAvanz.clasificacion.Modelo.herramientas;

import ProgAvanz.clasificacion.Modelo.Interfaces.Distance;

import java.util.List;

public class EuclideanDIstance implements Distance {
    @Override
    public double calculateDistance(List<Double> p, List<Double> q) {
        double sumatorio = 0;
        for (int i = 0; i < p.size(); i++){
            sumatorio += getDistanciaAux(p.get(i), q.get(i));
        }
        return Math.sqrt(sumatorio);
    }
    public double getDistanciaAux(double dato1, double dato2){
        return Math.pow(dato1 - dato2, 2);
    }

}
