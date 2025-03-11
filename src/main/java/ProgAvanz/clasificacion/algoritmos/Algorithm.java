package ProgAvanz.clasificacion.algoritmos;

import ProgAvanz.clasificacion.AlgoritmoSinEntrenarException;
import ProgAvanz.clasificacion.tablas.Table;

public interface Algorithm <T extends Table, S,R> {
    void  train(T datos)  throws AlgoritmoSinEntrenarException;
    R estimate(S dato);
}
