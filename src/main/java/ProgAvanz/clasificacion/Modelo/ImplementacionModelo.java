package ProgAvanz.clasificacion.Modelo;

import ProgAvanz.clasificacion.Modelo.Interfaces.Algorithm;
import ProgAvanz.clasificacion.Modelo.Interfaces.Distance;
import ProgAvanz.clasificacion.Modelo.algoritmos.KMeans;
import ProgAvanz.clasificacion.Modelo.algoritmos.KNN;
import ProgAvanz.clasificacion.Modelo.excepciones.LikedItemNotFoundException;
import ProgAvanz.clasificacion.Modelo.herramientas.*;
import ProgAvanz.clasificacion.Modelo.tablas.Table;
import ProgAvanz.clasificacion.Modelo.tablas.TableWithLabels;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ImplementacionModelo implements CambioModelo,InterrogaModelo {
    private String separator = System.getProperty("file.separator");
    private Algorithm algoritmo = null;
    private Table tabla;
    private Distance tipo_distancia = null;
    private RecSys recomendaciones = null;
    private boolean isKNN = false;
    private TableWithLabels trainTable;
    private TableWithLabels dataTable;
    private List<String> namesData;
    private CSVLabeledFileReader reader;
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

    public void recommend(String cancion, int nrecomendaciones) throws IOException, URISyntaxException, LikedItemNotFoundException {
        setUp();
        recomendaciones.recommend(cancion, nrecomendaciones );
    }
    private void setUp() throws IOException, URISyntaxException {
        recomendaciones = new RecSys(algoritmo);
        if(isKNN){
            setUpKNN();
        }
        else{
            setUpKMeans();

        }
    }
    private void setUpKMeans() throws IOException, URISyntaxException {
        reader = new CSVLabeledFileReader("songs_train_withoutnames.csv");
        trainTable = reader.readTableFromSource();
        reader = new CSVLabeledFileReader("songs_test_withoutnames.csv");
        dataTable = reader.readTableFromSource();
        namesData = readNames(songsFolder + separator + "songs_test_names.csv");
    }

    public void setUpKNN() throws IOException, URISyntaxException {
        reader = new CSVLabeledFileReader("songs_train.csv");
        trainTable = reader.readTableFromSource();
        reader = new CSVLabeledFileReader("songs_test.csv");
        dataTable = reader.readTableFromSource();
        namesData = readNames(songsFolder + separator + "songs_test_names.csv");
    }

    @Override
    public List<String> tituloCanciones() {
        return List.of();
    }
    private List<String> readNames(String fileOfItemNames) throws IOException, URISyntaxException {
        String path = getClass().getClassLoader().getResource(fileOfItemNames).toURI().getPath();

        List<String> names = new ArrayList<>();
        Scanner scanner = new Scanner(new File(path));
        while (scanner.hasNextLine()) {
            names.add(scanner.nextLine());
        }
        scanner.close();
        return names;
    }
}
