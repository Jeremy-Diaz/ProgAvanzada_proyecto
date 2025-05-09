package ProgAvanz.clasificacion.Controlador;

import ProgAvanz.clasificacion.Modelo.CambioModelo;
import ProgAvanz.clasificacion.Modelo.ImplementacionModelo;
import ProgAvanz.clasificacion.Modelo.excepciones.InvalidClusterNumberException;
import ProgAvanz.clasificacion.Modelo.excepciones.LikedItemNotFoundException;
import ProgAvanz.clasificacion.Vista.InterrogaVista;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class ImplementacionControlador implements Controlador {

    CambioModelo modelo;
    InterrogaVista vista;

    public ImplementacionControlador(CambioModelo mod, InterrogaVista vis) {
        modelo = mod;
        vista = vis;
    }

    @Override
    public void manhattan() {
        modelo.manhattan();
    }

    @Override
    public void kmeans() {
        modelo.kmeans();
    }

    @Override
    public void knn() {
        modelo.knn();
    }

    @Override
    public void euclidea() {
        modelo.euclidea();
    }

    @Override
    public List<String> recommend( int numero) throws IOException, URISyntaxException, LikedItemNotFoundException {
        return modelo.recommend(numero);
    }

    @Override
    public void cambioPestaña() throws IOException, URISyntaxException, InvalidClusterNumberException {
        modelo.cambioPestaña();
    }

    @Override
    public void setCancion(String cancion) {
        modelo.setCancion(cancion);
    }


}
