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
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class FXapp extends Application implements InterrogaVista {
    private CambioModelo modelo = new ImplementacionModelo();
    private Controlador controlador = new ImplementacionControlador(modelo,this);
    public FXapp() throws IOException, URISyntaxException {
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stagePrimario)  {

        VBox general = new VBox(10);

        VBox rec_dis = new VBox(5);
        VBox knn_manhattan = new VBox(5);
        VBox kmeans_euclidean = new VBox(5);

        Label recomendacion = new Label("Tipo de recomendación");
        Label distancia = new Label("Tipo de distancia");
        RadioButton seleccion_tipo_distancia_1 = new RadioButton("Manhattan");
        seleccion_tipo_distancia_1.setOnAction(EventHandler -> controlador.manhattan());
        RadioButton seleccion_tipo_distancia_2 = new RadioButton("Euclidea");
        seleccion_tipo_distancia_2.setOnAction(EventHandler -> controlador.euclidea());
        RadioButton seleccion_tipo_recomendacion_1 = new RadioButton("KNN");
        seleccion_tipo_recomendacion_1.setOnAction(EventHandler -> controlador.knn());

        RadioButton seleccion_tipo_recomendacion_2 = new RadioButton("K_MEANS");
        seleccion_tipo_recomendacion_2.setOnAction(EventHandler -> controlador.kmeans());

        ToggleGroup grupo_radio_recomendacion = new ToggleGroup();

        seleccion_tipo_recomendacion_1.setToggleGroup(grupo_radio_recomendacion);
        seleccion_tipo_recomendacion_2.setToggleGroup(grupo_radio_recomendacion);

        ToggleGroup grupo_radio_distancia = new ToggleGroup();
        seleccion_tipo_distancia_1.setToggleGroup(grupo_radio_distancia);//
        seleccion_tipo_distancia_2.setToggleGroup(grupo_radio_distancia);

        seleccion_tipo_recomendacion_1.disableProperty().bind(grupo_radio_distancia.selectedToggleProperty().isNull());
        seleccion_tipo_recomendacion_2.disableProperty().bind(grupo_radio_distancia.selectedToggleProperty().isNull());

        HBox bloque_recomendacion_distancia = new HBox(15);

        rec_dis.getChildren().addAll(recomendacion,distancia);
        knn_manhattan.getChildren().addAll(seleccion_tipo_recomendacion_1,seleccion_tipo_distancia_1);
        kmeans_euclidean.getChildren().addAll(seleccion_tipo_recomendacion_2,seleccion_tipo_distancia_2);


        bloque_recomendacion_distancia.getChildren().addAll(rec_dis,knn_manhattan,kmeans_euclidean);

        //canciones
        VBox bloque_canciones = new VBox();

        Label titulos_canciones  =new Label("Titulos de canciones");
        ObservableList<String> canciones = FXCollections.observableArrayList(tituloCanciones());
        ListView<String> lista = new ListView<>(canciones);

        lista.getSelectionModel().selectedItemProperty().addListener((item,valorInicial,valorActual) -> {
            controlador.setCancion(valorActual);
        });

        bloque_canciones.getChildren().addAll(titulos_canciones,lista);

        // boton aplicar y boton cambio de pagina

        HBox cambioPestaña = new HBox();
        Button cambio = new Button("Recomendar");
        cambio.disableProperty().bind(
                Bindings.or(
                                lista.getSelectionModel().selectedItemProperty().isNull(),
                                grupo_radio_distancia.selectedToggleProperty().isNull())
                        .or(grupo_radio_recomendacion.selectedToggleProperty().isNull())
        );

        cambio.setOnAction(actionEvent -> {
            try {
                nuevaVentana();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            } catch (LikedItemNotFoundException e) {
                throw new RuntimeException(e);
            } catch (InvalidClusterNumberException e) {
                throw new RuntimeException(e);
            }
        });
        cambioPestaña.getChildren().addAll(cambio);

        general.getChildren().addAll(bloque_recomendacion_distancia,bloque_canciones,cambioPestaña);

        stagePrimario.setTitle("Recomendar canciones");
        stagePrimario.setScene(new Scene(general, 400, 400));
        stagePrimario.show();

    }


    public List<String> tituloCanciones() {
        return modelo.tituloCanciones();
    }

    @Override
    public String getCancion() {
        return modelo.getCancion();
    }
    private void nuevaVentana() throws IOException, URISyntaxException, LikedItemNotFoundException, InvalidClusterNumberException {
       controlador.cambioPestaña();
        VBox general2 = new VBox();
        Label numeroRecomendaciones = new Label("Numero de recomendaciones: ");
        Spinner<Integer> numero = new Spinner<>(1,tituloCanciones().size(),1);

        HBox recomendaciones = new HBox(numeroRecomendaciones,numero);

        VBox bloque_canciones = new VBox();

        Label titulos_canciones  =new Label("Lista de canciones parecidas a "+ getCancion()+": ");
        ObservableList<String> canciones = FXCollections.observableArrayList(controlador.recommend(numero.getValue()));
        ListView<String> lista = new ListView<>(canciones);
        
        bloque_canciones.getChildren().addAll(titulos_canciones,lista);
        general2.getChildren().addAll(recomendaciones, bloque_canciones);
        Scene nueva = new Scene(general2,400,400);
        Stage nuevo = new Stage();
        nuevo.setScene(nueva);
        nuevo.show();
    }
}
