package ProgAvanz.clasificacion.Modelo;

import ProgAvanz.clasificacion.Modelo.excepciones.InvalidClusterNumberException;
import ProgAvanz.clasificacion.Modelo.excepciones.LikedItemNotFoundException;
import ProgAvanz.clasificacion.Vista.InformaVista;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface CambioModelo {
    void setVista(InformaVista vi);
    void manhattan();
    void kmeans();
    void knn();
    void euclidea();
    List<String> recommend(int nCanciones) throws IOException, URISyntaxException, LikedItemNotFoundException;
    void cambioPestaña() throws IOException, URISyntaxException, InvalidClusterNumberException;
    void setCancion(String cancion);
}
