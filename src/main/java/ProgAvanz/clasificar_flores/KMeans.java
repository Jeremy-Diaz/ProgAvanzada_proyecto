package ProgAvanz.clasificar_flores;
import java.util.*;
import java.util.random.*;

public class KMeans{
    private int nClusters;
    private int nIterations;
    private long seed;
    private Map<Row,List<Row>> groups;

    public KMeans(int numClusters, int numIterations, long seed){
        nClusters = numClusters;
        nIterations = numIterations;
        this.seed = seed;
        groups = new HashMap<>();
    }

    public void train(Table data) throws AlgoritmoSinEntrenarException {
        Random numRandom = new Random(seed);
        List<Row> representantes = new LinkedList<>();
        for (int i = 0; i < nClusters; i++) {
            Row fila_representante = data.getRowAt(numRandom.nextInt());
            representantes.add(fila_representante);
            groups.put(fila_representante, new LinkedList<>());

        }
        for (int i = 0; i < nIterations; i++) { // PREGUNTAR, ES MEJOR PONER EL METODO O USAR EL KNN COMO METODO ESTATICO O EXTENDER KNN?
            // añadimos a todos a su correspondiente grupo
            for (Row fila : data.getRows()) {
                double distancia_minima = KNN.getDistancia(fila.getData(), representantes.get(0).getData());
                Row representante_mas_cercano = representantes.get(0);
                for (int j = 1; j < representantes.size(); j++) {
                    double distancia = KNN.getDistancia(fila.getData(), representantes.get(0).getData());
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
        return 69;
    }

    public double mean (List < Double > lista) {
        double suma = 0;
        for (int i = 0; i < lista.size(); i++)
            suma += lista.get(i);
        return suma / lista.size();
    }
}

// Preguntar si muchos fors anidados son kk o no :)
