package ProgAvanz.clasificacion.Modelo;

import ProgAvanz.clasificacion.Modelo.excepciones.LikedItemNotFoundException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface CambioModelo {
    void manhattan();
    void kmeans();
    void knn();
    void euclidea();
    List<String> recommend(String cancion, int nCanciones) throws IOException, URISyntaxException, LikedItemNotFoundException;
    void cambioPestaña() throws IOException, URISyntaxException;
    List<String> tituloCanciones();
}
