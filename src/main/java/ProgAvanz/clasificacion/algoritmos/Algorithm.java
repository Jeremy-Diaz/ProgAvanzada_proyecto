package ProgAvanz.clasificacion.algoritmos;

import ProgAvanz.clasificacion.excepciones.AlgoritmoSinEntrenarException;
import ProgAvanz.clasificacion.excepciones.InvalidClusterNumberException;
import ProgAvanz.clasificacion.tablas.Table;

public interface Algorithm <T extends Table, S,R> {
    void  train(T datos)  throws InvalidClusterNumberException;
    S estimate(R dato);
}
