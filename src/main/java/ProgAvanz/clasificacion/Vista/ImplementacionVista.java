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
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public  class ImplementacionVista implements InterrogaVista, InterrogaModelo, InformaVista {
    private static  InterrogaModelo modelo;
    private static Controlador controlador;
    private boolean cancion;
    public void setModelo(InterrogaModelo mod){ modelo = mod;}
    public void setControlador(Controlador con){ controlador = con;}
    boolean hay_cancion = false;
    public void start(Stage stagePrimario) {

        VBox general = new VBox(10);
        general.setStyle("-fx-background-color: #282B30");

        BorderPane general2 = new BorderPane();
        VBox rec_dis = new VBox(5);
        VBox knn_manhattan = new VBox(5);
        VBox kmeans_euclidean = new VBox(5);

        Label recomendacion = new Label("Tipo de recomendación");
        recomendacion.setStyle("-fx-text-fill: #EAEAEA;-fx-font-size: 16px");

        Label distancia = new Label("Tipo de distancia");
        distancia.setStyle("-fx-text-fill: #EAEAEA;-fx-font-size: 16px");

        RadioButton seleccion_tipo_distancia_1 = new RadioButton("Manhattan");
        seleccion_tipo_distancia_1.setStyle("-fx-text-fill: #EAEAEA");
        seleccion_tipo_distancia_1.setOnAction(EventHandler -> controlador.manhattan());

        RadioButton seleccion_tipo_distancia_2 = new RadioButton("Euclidea");
        seleccion_tipo_distancia_2.setStyle("-fx-text-fill: #EAEAEA");
        seleccion_tipo_distancia_2.setOnAction(EventHandler -> controlador.euclidea());

        RadioButton seleccion_tipo_recomendacion_1 = new RadioButton("KNN");
        seleccion_tipo_recomendacion_1.setStyle("-fx-text-fill: #EAEAEA");
        seleccion_tipo_recomendacion_1.setOnAction(EventHandler -> controlador.knn());

        RadioButton seleccion_tipo_recomendacion_2 = new RadioButton("K_MEANS");
        seleccion_tipo_recomendacion_2.setStyle("-fx-text-fill: #EAEAEA");
        seleccion_tipo_recomendacion_2.setOnAction(EventHandler -> controlador.kmeans());

        ToggleGroup grupo_radio_recomendacion = new ToggleGroup();
        seleccion_tipo_recomendacion_1.setToggleGroup(grupo_radio_recomendacion);
        seleccion_tipo_recomendacion_2.setToggleGroup(grupo_radio_recomendacion);

        ToggleGroup grupo_radio_distancia = new ToggleGroup();
        seleccion_tipo_distancia_1.setToggleGroup(grupo_radio_distancia);
        seleccion_tipo_distancia_2.setToggleGroup(grupo_radio_distancia);

        seleccion_tipo_recomendacion_1.disableProperty().bind(grupo_radio_distancia.selectedToggleProperty().isNull());
        seleccion_tipo_recomendacion_2.disableProperty().bind(grupo_radio_distancia.selectedToggleProperty().isNull());

        HBox bloque_recomendacion_distancia = new HBox(15);
        rec_dis.getChildren().addAll(distancia, recomendacion);
        knn_manhattan.getChildren().addAll(seleccion_tipo_distancia_1, seleccion_tipo_recomendacion_1);
        kmeans_euclidean.getChildren().addAll(seleccion_tipo_distancia_2, seleccion_tipo_recomendacion_2);
        bloque_recomendacion_distancia.getChildren().addAll(rec_dis, knn_manhattan, kmeans_euclidean);

        VBox bloque_canciones = new VBox();
        VBox.setVgrow(bloque_canciones, Priority.ALWAYS);

        Label titulos_canciones = new Label("Titulos de canciones");
        titulos_canciones.setStyle("-fx-text-fill: #EAEAEA;-fx-font-size: 20px");

        ObservableList<String> canciones = FXCollections.observableArrayList(tituloCanciones());
        FilteredList<String> lista_filtrada = new FilteredList<>(canciones, s -> true);

        ListView<String> lista = new ListView<>(lista_filtrada);
        lista.setStyle("-fx-background-color: #1e2124");

        TextField buscador = new TextField();
        buscador.setPromptText("Buscar una canción...");
        buscador.textProperty().addListener((elem, valorInicial, valorActual) -> {
            lista_filtrada.setPredicate(item -> {
                if (valorActual == null || valorActual.isEmpty()) return true;
                String lower = valorActual.toLowerCase();
                return item.toLowerCase().contains(lower);
            });
        });

        lista.getSelectionModel().selectedItemProperty().addListener((item, valorInicial, valorActual) -> {
            controlador.setCancion(valorActual);
        });

        BorderPane etiqueta_busqueda = new BorderPane();
        etiqueta_busqueda.setRight(buscador);
        etiqueta_busqueda.setLeft(titulos_canciones);

        bloque_canciones.getChildren().addAll(etiqueta_busqueda, lista);

        HBox cambioPestaña = new HBox();
        Button cambio = new Button("Recomendar");
        cambio.disableProperty().bind(
                Bindings.or(
                        lista.getSelectionModel().selectedItemProperty().isNull(),
                        grupo_radio_distancia.selectedToggleProperty().isNull()
                ).or(grupo_radio_recomendacion.selectedToggleProperty().isNull())
        );

        cambio.setOnAction(actionEvent -> {
            try {
                if(hay_cancion)
                    nuevaVentana();
                else
                    throw new NullPointerException();
            } catch (IOException | URISyntaxException | LikedItemNotFoundException | NullPointerException | InvalidClusterNumberException e) {
                throw new RuntimeException(e);
            }
        });

        cambioPestaña.getChildren().addAll(cambio);

        general.getChildren().addAll(bloque_recomendacion_distancia, bloque_canciones, cambioPestaña);
        VBox.setVgrow(lista, Priority.ALWAYS);

        stagePrimario.setTitle("Recomendar canciones");
        stagePrimario.setScene(new Scene(general, 500, 400));
        stagePrimario.show();
    }

    public List<String> tituloCanciones() {
        return modelo.tituloCanciones();
    }

    @Override
    public String getCancion() {
        return modelo.getCancion();
    }

    @Override
    public int getCancionesGenero() throws LikedItemNotFoundException {
        return modelo.getCancionesGenero();
    }

    private void nuevaVentana() throws IOException, URISyntaxException, LikedItemNotFoundException, InvalidClusterNumberException {
        controlador.cambioPestaña();

        VBox general2 = new VBox();
        general2.setStyle("-fx-background-color: #282B30");

        Label numeroRecomendaciones = new Label("Numero de recomendaciones: ");
        numeroRecomendaciones.setStyle("-fx-text-fill: #EAEAEA;-fx-font-size: 16px");

        VBox bloque_canciones = new VBox();
        ObservableList<String> todasCanciones = FXCollections.observableArrayList(controlador.recommend(getCancionesGenero()));
        System.out.println(todasCanciones.toString());

        Spinner<Integer> numero = new Spinner<>(1, todasCanciones.size(), 5);
        HBox recomendaciones = new HBox(numeroRecomendaciones, numero);

        Label titulos_canciones = new Label("Lista de canciones parecidas a " + getCancion() + ": ");
        titulos_canciones.setStyle("-fx-text-fill: #EAEAEA;-fx-font-size: 20px");

        ObservableList<String> cancionesVisibles = FXCollections.observableArrayList();
        ListView<String> lista = new ListView<>(cancionesVisibles);
        VBox.setVgrow(lista, Priority.ALWAYS);
        numero.valueProperty().addListener((obs, anterior, nuevo) ->
                actualizarLista(nuevo, cancionesVisibles, todasCanciones));

        actualizarLista(numero.getValue(), cancionesVisibles, todasCanciones);

        bloque_canciones.getChildren().addAll(titulos_canciones, lista);
        general2.getChildren().addAll(recomendaciones, bloque_canciones);
        VBox.setVgrow(bloque_canciones, Priority.ALWAYS);


        Scene nueva = new Scene(general2, 500, 400);
        Stage nuevo = new Stage();
        nuevo.setScene(nueva);
        nuevo.show();
    }

    private void actualizarLista(int num, ObservableList<String> cancionesVisibles, ObservableList<String> todasCanciones) {
        cancionesVisibles.setAll(todasCanciones.subList(0, Math.min(num, todasCanciones.size())));
    }

    @Override
    public void hayCancion() {
        hay_cancion = true;
    }
}
