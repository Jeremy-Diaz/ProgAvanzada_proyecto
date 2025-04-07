package ProgAvanz.clasificacion.herramientas;

import ProgAvanz.clasificacion.Interfaces.Distance;

import java.util.List;

public class ManhattanDistance implements Distance {

    @Override
    public double calculateDistance(List<Double> p, List<Double> q) {
        double sumatorio = 0;
        for (int i = 0; i < p.size(); i++){
            sumatorio += Math.abs(p.get(i) - q.get(i));
        }
        return sumatorio;
    }
}
