package ProgAvanz.clasificacion.Modelo;

import ProgAvanz.clasificacion.Modelo.Interfaces.Algorithm;
import ProgAvanz.clasificacion.Modelo.Interfaces.Distance;
import ProgAvanz.clasificacion.Modelo.algoritmos.KMeans;
import ProgAvanz.clasificacion.Modelo.algoritmos.KNN;
import ProgAvanz.clasificacion.Modelo.excepciones.InvalidClusterNumberException;
import ProgAvanz.clasificacion.Modelo.excepciones.LikedItemNotFoundException;
import ProgAvanz.clasificacion.Modelo.herramientas.*;
import ProgAvanz.clasificacion.Modelo.tablas.TableWithLabels;
import ProgAvanz.clasificacion.Vista.InformaVista;
import ProgAvanz.clasificacion.Vista.InterrogaModelo;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ImplementacionModelo implements CambioModelo, InterrogaModelo{

    private Algorithm algoritmo = null;
    private Distance tipo_distancia = null;
    private RecSys recomendaciones = null;
    private boolean isKNN = false;
    private String cancion_elegida = null;


    private String separator = System.getProperty("file.separator");
    private TableWithLabels trainTable;
    private TableWithLabels dataTable;
    private List<String> namesData;
    private CSVLabeledFileReader reader;
    private String songsFolder = "recommender";
    private InformaVista vista;
    public ImplementacionModelo() throws IOException, URISyntaxException {
        trainTable = new TableWithLabels();
        dataTable= new TableWithLabels();
        namesData = readNames(songsFolder + separator + "songs_test_names.csv");
    }

    @Override
    public void setVista(InformaVista vi) {
        vista = vi;
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
    public List<String> recommend( int nrecomendaciones) throws IOException, URISyntaxException, LikedItemNotFoundException {
        return recomendaciones.recommend(cancion_elegida, nrecomendaciones);

    }
    @Override
    public void cambioPestaña() throws IOException, URISyntaxException, InvalidClusterNumberException {
        setUp();
    }

    @Override
    public List<String> tituloCanciones() {
        return namesData;
    }

    @Override
    public String getCancion() {
        return cancion_elegida;
    }

    public int getCancionesGenero() throws LikedItemNotFoundException {return recomendaciones.contadorLikedSong(cancion_elegida);}

    @Override
    public void setCancion(String cancion) {
        cancion_elegida = cancion;
        vista.hayCancion();
    }

    private void setUp() throws IOException, URISyntaxException, InvalidClusterNumberException {
        recomendaciones = new RecSys(algoritmo);
        if(isKNN){
            setUpKNN();
        }
        else{
            setUpKMeans();

        }
        recomendaciones.train(trainTable);
        recomendaciones.initialise(dataTable,namesData);
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

    private List<String> readNames(String fileOfItemNames) throws IOException, URISyntaxException {
        String path = getClass().getClassLoader().getResource(fileOfItemNames).getPath();

        List<String> names = new ArrayList<>();
        Scanner scanner = new Scanner(new File(path));
        while (scanner.hasNextLine()) {
            names.add(scanner.nextLine());
        }
        scanner.close();
        return names;
    }

}

