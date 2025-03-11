package ProgAvanz.clasificacion.algoritmos;
import ProgAvanz.clasificacion.AlgoritmoSinEntrenarException;
import ProgAvanz.clasificacion.Distancias;
import ProgAvanz.clasificacion.tablas.Row;
import ProgAvanz.clasificacion.tablas.Table;

import java.util.*;

public class KMeans{
    private int nClusters;
    private int nIterations;
    private long seed;
    private Map<Row,List<Row>> groups;
    private List<Row> centroides;

    public KMeans(int numClusters, int numIterations, long seed){
        nClusters = numClusters;
        nIterations = numIterations;
        this.seed = seed;
        groups = new HashMap<>();
        centroides = new ArrayList<>();
    }

    public void train(Table data) throws AlgoritmoSinEntrenarException {
        Random numRandom = new Random(seed);
        Distancias dis = new Distancias();
        List<Row> representantes = new LinkedList<>();
        for (int i = 0; i < nClusters; i++) {
            Row fila_representante = data.getRowAt(numRandom.nextInt());
            representantes.add(fila_representante);
            groups.put(fila_representante, new LinkedList<>());

        }
        for (int i = 0; i < nIterations; i++) {
            for (Row fila : data.getRows()) {
                double distancia_minima = dis.getDistanciaEuclides(fila.getData(), representantes.get(0).getData());
                Row representante_mas_cercano = representantes.get(0);
                for (int j = 1; j < representantes.size(); j++) {
                    double distancia = dis.getDistanciaEuclides(fila.getData(), representantes.get(0).getData());
                    if (distancia < distancia_minima) {
                        distancia_minima = distancia;
                        representante_mas_cercano = representantes.get(i);
                    }
                }
                groups.get(representante_mas_cercano).add(fila);
            }

            // Promedios PREGUNTAR
            List<Double> centroide = new LinkedList<>();
            for (Row representante : representantes) {
                for (int k = 0; k < representante.getData().size(); k++) {
                    List<Double> lista_columna = new LinkedList<>();
                    for (Row elemento : groups.get(representante)) {
                        lista_columna.add(elemento.getData().get(k));
                    }
                    centroide.add(i,mean(lista_columna));
                }
                representantes.remove(representante);
                groups.remove(representante);
                groups.put(new Row(centroide),new LinkedList<>());
            }
            representantes.addAll(groups.keySet());
        }
    }

    public Integer estimate (List < Double > sample) {
        Distancias dis = new Distancias();
        Set<Row> centroides = new HashSet<>(groups.keySet());
        Iterator<Row> it = centroides.iterator();
        Row centroide_mas_cercano = it.next();
        double distancia_menor = dis.getDistanciaEuclides(centroide_mas_cercano.getData(),sample); ;
        while (it.hasNext()){
            Row centroide = it.next();
            double distancia = dis.getDistanciaEuclides(centroide.getData(),sample);
            if ( distancia< distancia_menor){
                distancia_menor = distancia;
                centroide_mas_cercano = centroide;
            }
        }

        return 0;


    }

    public double mean (List < Double > lista) {
        double suma = 0;
        for (int i = 0; i < lista.size(); i++)
            suma += lista.get(i);
        return suma / lista.size();
    }
}

// Preguntar si muchos fors anidados son kk o no :)
