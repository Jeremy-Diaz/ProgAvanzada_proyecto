package ProgAvanz.clasificacion.Vista;

import ProgAvanz.clasificacion.Controlador.Controlador;
import ProgAvanz.clasificacion.Controlador.ImplementacionControlador;
import ProgAvanz.clasificacion.Modelo.CambioModelo;
import ProgAvanz.clasificacion.Modelo.ImplementacionModelo;
import ProgAvanz.clasificacion.Modelo.excepciones.InvalidClusterNumberException;
import ProgAvanz.clasificacion.Modelo.excepciones.LikedItemNotFoundException;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class FXapp extends Application{

    public FXapp(){}
    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage stagePrimario) throws IOException, URISyntaxException {
        ImplementacionVista vi = new ImplementacionVista();
        ImplementacionModelo modelo = new ImplementacionModelo();
        ImplementacionControlador controlador = new ImplementacionControlador(modelo,vi);

        vi.setModelo(modelo);
        vi.setControlador(controlador);
        modelo.setVista(vi);

        vi.start(stagePrimario);
    }
}
