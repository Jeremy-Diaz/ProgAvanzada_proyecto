package ProgAvanz.clasificacion.Modelo.herramientas;

import ProgAvanz.clasificacion.Modelo.Interfaces.Algorithm;
import ProgAvanz.clasificacion.Modelo.excepciones.InvalidClusterNumberException;
import ProgAvanz.clasificacion.Modelo.excepciones.LikedItemNotFoundException;
import ProgAvanz.clasificacion.Modelo.tablas.Row;
import ProgAvanz.clasificacion.Modelo.tablas.Table;

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
                    if (Objects.equals(etiquetas.get(indice_item_liked), etiquetas.get(i)) && !nameLikedItem.equals(nombres_de_item.get(i)) && !lista.contains(nombres_de_item.get(i))) {
                        lista.add(nombres_de_item.get(i));
                    }
                }
            }
        }
        return lista;
    }

    public int contadorLikedSong(String nameLikedItem) throws LikedItemNotFoundException {
        if (!nombres_de_item.contains(nameLikedItem))
            throw new LikedItemNotFoundException(nameLikedItem);
        int contador= 0;
        int indice_item_liked = getIndexOf(nameLikedItem);
        if (indice_item_liked != -1){
            Row fila_item_liked = tabla.getRowAt(indice_item_liked);
            for (int i = 0; i< etiquetas.size();i++){
                if (etiquetas.get(indice_item_liked) == etiquetas.get(i) && !nameLikedItem.equals(nombres_de_item.get(i))) {
                    contador++;
                }
            }
        }
        return contador;
    }

    public int getIndexOf(String name){
        for (int i = 0; i < nombres_de_item.size(); i++){
            if (nombres_de_item.get(i).equals(name))
                return i;
        }
        return -1;
    }

}
