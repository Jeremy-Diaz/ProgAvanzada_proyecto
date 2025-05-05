package ProgAvanz.clasificacion.Modelo;

import ProgAvanz.clasificacion.Modelo.Interfaces.Algorithm;
import ProgAvanz.clasificacion.Modelo.Interfaces.Distance;
import ProgAvanz.clasificacion.Modelo.algoritmos.KMeans;
import ProgAvanz.clasificacion.Modelo.algoritmos.KNN;
import ProgAvanz.clasificacion.Modelo.herramientas.*;
import ProgAvanz.clasificacion.Modelo.tablas.Table;

import java.util.List;

public class ImplementacionModelo implements CambioModelo,InterrogaModelo {
    private Algorithm algoritmo = null;
    private Table tabla;
    private Distance tipo_distancia = null;
    private RecSys recomendaciones = null;
    private String separator = System.getProperty("file.separator");
    private boolean isKNN = false;
    // TODO: cambiar ruta si hace falta
    private String songsFolder = "recommender";

    CSVLabeledFileReader reader_train = new CSVLabeledFileReader("songs_train.csv");

    public ImplementacionModelo(){
    }

    @Override
    public void manhattan() {
        tipo_distancia = new ManhattanDistance();
    }

    @Override
    public void euclidea() {
        tipo_distancia = new EuclideanDIstance();
    }

    @Override
    public void kmeans() {
        algoritmo = new KMeans(15,10,53,tipo_distancia);
        isKNN = false;
    }

    @Override
    public void knn() {
        algoritmo = new KNN(tipo_distancia);
        isKNN = true;
    }

    @Override
    public void recommend() {
        recomendaciones = new RecSys(algoritmo);
        if(isKNN){
            setUpKNN();
        }
        else{
            setUpKMeans();
        }
    }

    private void setUpKMeans() {

    }

    public void setUpKNN(){

    }

    @Override
    public List<String> tituloCanciones() {
        return List.of();
    }
}
