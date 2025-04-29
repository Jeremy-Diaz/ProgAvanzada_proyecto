package ProgAvanz.clasificacion.Modelo.Interfaces;

import ProgAvanz.clasificacion.Modelo.excepciones.InvalidClusterNumberException;
import ProgAvanz.clasificacion.Modelo.tablas.Table;

public interface Algorithm <T extends Table, S,R> {
    void  train(T datos)  throws InvalidClusterNumberException;
    S estimate(R dato);
}
