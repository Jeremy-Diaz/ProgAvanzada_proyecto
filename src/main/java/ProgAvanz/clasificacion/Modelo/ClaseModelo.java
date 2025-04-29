package ProgAvanz.clasificacion.Modelo;

import ProgAvanz.clasificacion.Modelo.Interfaces.Algorithm;
import ProgAvanz.clasificacion.Modelo.Interfaces.Distance;
import ProgAvanz.clasificacion.Modelo.tablas.Table;

public class ClaseModelo {
    private Algorithm algoritmo;
    private Table tabla;
    private Distance tipo_distancia;

    public ClaseModelo(){
        algoritmo = null;
        tabla = null;
        tipo_distancia = null;
    }
}
