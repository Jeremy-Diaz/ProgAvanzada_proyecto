package ProgAvanz.clasificacion.herramientas;

import ProgAvanz.clasificacion.algoritmos.Algorithm;
import ProgAvanz.clasificacion.excepciones.InvalidClusterNumberException;
import ProgAvanz.clasificacion.excepciones.LikedItemNotFoundException;
import ProgAvanz.clasificacion.tablas.Row;
import ProgAvanz.clasificacion.tablas.Table;

import java.util.*;

public class RecSys {


    private Algorithm<Table,Integer,List<Double>> algoritmo;
    private List<String> nombres_de_item;
    private Table tabla;
    private Map<Integer,List<Row>> rows_clase;


    public RecSys(Algorithm algorithm){
        algoritmo = algorithm;
        tabla = new Table();
        nombres_de_item = new ArrayList<>();
    }

    public void train(Table trainData) throws InvalidClusterNumberException {
        algoritmo.train(trainData);
        tabla = trainData;
        rows_clase = new HashMap<>();
    }
    public void initialise(Table testData, List<String> testItemNames){
        for (Row fila: testData.getRows()){
            int clase =algoritmo.estimate(fila.getData());
            if(!rows_clase.containsKey(clase))
                rows_clase.put(clase,new LinkedList<>());
            rows_clase.get(clase).add(fila);

        }
        nombres_de_item = testItemNames;
    }
    public List<String> recommend(String nameLikedItem, int numRecomendations) throws LikedItemNotFoundException{
        if (!nombres_de_item.contains(nameLikedItem))
            throw new LikedItemNotFoundException(nameLikedItem);
        List<String> lista = new LinkedList<>();
        int indice_item_liked = getIndexOf(nameLikedItem);
        if (indice_item_liked != -1){
            Row fila_item_liked = tabla.getRowAt(indice_item_liked);
            int clase = algoritmo.estimate(fila_item_liked.getData()); // es necesario y eficiente?
            for( int i = 0; i < rows_clase.get(clase).size(); i++) {
                lista.add(nombres_de_item.get(tabla.getIndexOfRow(rows_clase.get(clase).get(i)))); // eficiente?
                if (i == numRecomendations-1)
                    break;
            }
        }
        return lista;
    }


    public int getIndexOf(String name){
        for (int i = 0; i < nombres_de_item.size(); i++){
            if (nombres_de_item.get(i).equals(name))
                return i;
        }
        return -1;
    }

}
