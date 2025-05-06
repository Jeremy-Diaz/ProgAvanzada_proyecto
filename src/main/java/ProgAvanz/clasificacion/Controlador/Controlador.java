package ProgAvanz.clasificacion.Controlador;

import ProgAvanz.clasificacion.Modelo.excepciones.LikedItemNotFoundException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface Controlador {
    void manhattan();

    void kmeans();

    void knn();

    void euclidea();

    List<String> recommend(String cancion, int numero ) throws IOException, URISyntaxException, LikedItemNotFoundException;

    void cambioPestaña() throws IOException, URISyntaxException;
}
