package ProgAvanz.clasificacion.Vista;

import ProgAvanz.clasificacion.Controlador.Escuchadora;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;

import static java.awt.event.ActionEvent.ACTION_FIRST;

public class FXapp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stagePrimario) {

        VBox general = new VBox(10);

        VBox rec_dis = new VBox(5);
        VBox knn_manhattan = new VBox(5);
        VBox kmeans_euclidean = new VBox(5);

        Label recomendacion = new Label("Tipo de recomendación");
        Label distancia = new Label("Tipo de recomendacion");
        RadioButton seleccion_tipo_recomendacion_1 = new RadioButton("KNN");
        seleccion_tipo_recomendacion_1.setOnAction(new Escuchadora());
        RadioButton seleccion_tipo_recomendacion_2 = new RadioButton("K_MEANS");
        RadioButton seleccion_tipo_distancia_1 = new RadioButton("Manhattan");
        RadioButton seleccion_tipo_distancia_2 = new RadioButton("Euclidea");

        ToggleGroup grupo_radio_recomendacion = new ToggleGroup();

        seleccion_tipo_recomendacion_1.setToggleGroup(grupo_radio_recomendacion);
        seleccion_tipo_recomendacion_2.setToggleGroup(grupo_radio_recomendacion);

        ToggleGroup grupo_radio_distancia = new ToggleGroup();

        seleccion_tipo_distancia_1.setToggleGroup(grupo_radio_distancia);
        seleccion_tipo_distancia_2.setToggleGroup(grupo_radio_distancia);

        HBox bloque_recomendacion_distancia = new HBox(15);

        rec_dis.getChildren().addAll(recomendacion,distancia);
        knn_manhattan.getChildren().addAll(seleccion_tipo_recomendacion_1,seleccion_tipo_distancia_1);
        kmeans_euclidean.getChildren().addAll(seleccion_tipo_recomendacion_2,seleccion_tipo_distancia_2);


        bloque_recomendacion_distancia.getChildren().addAll(rec_dis,knn_manhattan,kmeans_euclidean);

        //canciones
        VBox bloque_canciones = new VBox();

        Label titulos_canciones  =new Label("Titulos de canciones");
        ObservableList<String> canciones = FXCollections.observableArrayList();
        ListView<String> lista = new ListView<>(canciones);

        bloque_canciones.getChildren().addAll(titulos_canciones,lista);



        general.getChildren().addAll(bloque_recomendacion_distancia,bloque_canciones);

        stagePrimario.setTitle("Recomendadorneitor 3000");
        stagePrimario.setScene(new Scene(general, 400, 400));
        stagePrimario.show();




    }
}
