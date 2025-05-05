package ProgAvanz.clasificacion.Modelo;

import ProgAvanz.clasificacion.Modelo.excepciones.LikedItemNotFoundException;

import java.io.IOException;
import java.net.URISyntaxException;

public interface CambioModelo {
    void manhattan();
    void kmeans();
    void knn();
    void euclidea();
    void recommend(String cancion, int nCanciones) throws IOException, URISyntaxException, LikedItemNotFoundException;
}
