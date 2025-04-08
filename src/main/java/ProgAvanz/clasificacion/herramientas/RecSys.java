package ProgAvanz.clasificacion.herramientas;

import ProgAvanz.clasificacion.Interfaces.Algorithm;
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
    private Table tabla_train;
    private List<Integer> etiquetas;

    public RecSys(Algorithm algorithm){
        algoritmo = algorithm;
        tabla = new Table();
        nombres_de_item = new ArrayList<>();
        rows_clase = new HashMap<>();
        tabla_train = new Table();
        etiquetas = new ArrayList<>();
    }

    public void train(Table trainData) throws InvalidClusterNumberException {
        algoritmo.train(trainData);
        tabla_train = trainData;

    }
    public void initialise(Table testData, List<String> testItemNames){
        for (Row fila: testData.getRows()){
            int clase =algoritmo.estimate(fila.getData());
            etiquetas.add(clase);
            /*if(!rows_clase.containsKey(clase))
                rows_clase.put(clase,new LinkedList<>());
            rows_clase.get(clase).add(fila);
            */
        }
        nombres_de_item = testItemNames;
        tabla = testData;
    }
    public List<String> recommend(String nameLikedItem, int numRecomendations) throws LikedItemNotFoundException{
        if (!nombres_de_item.contains(nameLikedItem))
            throw new LikedItemNotFoundException(nameLikedItem);
        List<String> lista = new LinkedList<>();
        int indice_item_liked = getIndexOf(nameLikedItem);
        if (indice_item_liked != -1){
            Row fila_item_liked = tabla.getRowAt(indice_item_liked);
            for (int i = 0; i< etiquetas.size();i++){
                if(lista.size() < numRecomendations ){
                    if (etiquetas.get(indice_item_liked) == etiquetas.get(i) && tabla.getRowAt(indice_item_liked) != tabla.getRowAt(i)) {
                        lista.add(nombres_de_item.get(i));
                    }
                }
            }
        }
        return lista;

            /*Row fila_item_liked = tabla.getRowAt(indice_item_liked);
            int clase = algoritmo.estimate(fila_item_liked.getData());// es necesario y eficiente?
            List<Row> lista_clase = rows_clase.get(clase);
            for( int i = 0; i < lista_clase.size(); i++) {
                String nombre_a_meter= nombres_de_item.get(tabla.getIndexOfRow(rows_clase.get(clase).get(i)));
                if (!nombre_a_meter.equals(nameLikedItem))
                    lista.add(nombre_a_meter); // eficiente?
                if (lista.size() == numRecomendations)
                    break;
            }
             */
    }


    public int getIndexOf(String name){
        for (int i = 0; i < nombres_de_item.size(); i++){
            if (nombres_de_item.get(i).equals(name))
                return i;
        }
        return -1;
    }

}
