package ProgAvanz.clasificacion.algoritmos;
import ProgAvanz.clasificacion.Interfaces.Algorithm;
import ProgAvanz.clasificacion.Interfaces.Distance;
import ProgAvanz.clasificacion.excepciones.InvalidClusterNumberException;
import ProgAvanz.clasificacion.herramientas.Distancias;
import ProgAvanz.clasificacion.herramientas.EuclideanDIstance;
import ProgAvanz.clasificacion.tablas.Row;
import ProgAvanz.clasificacion.tablas.Table;

import java.util.*;

public class KMeans implements Algorithm<Table, Integer, List<Double>> {
    private int nClusters;
    private int nIterations;
    private long seed;
    private Map<Row,List<Row>> groups;
    private List<Row> centroides;
    private Set<Integer> valores_random;
    private Distance distancia;

    public KMeans(int numClusters, int numIterations, long seed){

        nClusters = numClusters;
        nIterations = numIterations;
        this.seed = seed;
        groups = new HashMap<>();
        centroides = new ArrayList<>();
        distancia = new EuclideanDIstance();
    }

    public KMeans(int numClusters, int numIterations, long seed, Distance type_distance){

        nClusters = numClusters;
        nIterations = numIterations;
        this.seed = seed;
        groups = new HashMap<>();
        centroides = new ArrayList<>();
        distancia = type_distance;
    }

    public void train(Table data) throws InvalidClusterNumberException {
        if (nClusters > data.getRowCount())
            throw new InvalidClusterNumberException(nClusters);
        Random numRandom = new Random(seed);
        Distancias dis = new Distancias();

        for (int i = 0; i < nClusters; i++) {
            int random = numRandom.nextInt(0, data.getRowCount() - 1);
            Row fila_representante = data.getRowAt(random);
            centroides.add(fila_representante);
            groups.put(fila_representante, new LinkedList<>());

        }

        for (int i = 0; i < nIterations; i++) {
            for (Row fila : data.getRows()) {
                double distancia_minima = dis.getDistanciaEuclides(fila.getData(), centroides.get(0).getData());
                Row representante_mas_cercano = centroides.get(0);
                for (int j = 1; j < centroides.size(); j++) {
                    double distancia = dis.getDistanciaEuclides(fila.getData(), centroides.get(j).getData());
                    if (distancia < distancia_minima) {
                        distancia_minima = distancia;
                        representante_mas_cercano = centroides.get(j);
                    }
                }
                groups.get(representante_mas_cercano).add(fila);
            }




            List<Double> datos_centroide_nuevo = new LinkedList<>();

            for (Row representante : centroides) {
                for (int k = 0; k < representante.getData().size(); k++) {
                    List<Double> lista_columna = new LinkedList<>();
                    for (Row elemento : groups.get(representante)) {
                        lista_columna.add(elemento.getData().get(k));
                    }
                    datos_centroide_nuevo.add(mean(lista_columna));
                }
                groups.remove(representante);
                groups.put(new Row(datos_centroide_nuevo),new LinkedList<>());
                datos_centroide_nuevo = new LinkedList<>();
            }
            centroides.clear();
            centroides.addAll(groups.keySet());
        }
    }

    public Integer estimate (List < Double > sample) {
        Distancias dis = new Distancias();
        double distancia_mas_cercana = dis.getDistanciaEuclides(centroides.get(0).getData(), sample);
        int grupo_mas_cercano = 0;
        for(int i = 1; i < centroides.size(); i++){
            double distanc=distancia.calculateDistance(centroides.get(i).getData(),sample);
            if (distanc <= distancia_mas_cercana ){
                distancia_mas_cercana = distanc;
                grupo_mas_cercano = i;
            }
        }
        return grupo_mas_cercano;
    }

    public double mean (List < Double > lista) {
        double suma = 0;
        for (int i = 0; i < lista.size(); i++)
            suma += lista.get(i);
        return suma / lista.size();
    }
}