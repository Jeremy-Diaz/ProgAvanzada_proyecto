package ProgAvanz.clasificacion.GUI;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.awt.*;
import java.util.Collection;

public class FXapp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stagePrimario) {

        VBox general = new VBox();

        HBox TipoRecomendacion = new HBox();

        Label label_tipo_recomendacion = new Label("Tipo de Recomendación");

        RadioButton seleccion_tipo_recomendacion_1 = new RadioButton("Tipo_1");
        RadioButton seleccion_tipo_recomendacion_2 = new RadioButton("Tipo_2");

        ToggleGroup grupo_radio_recomendacion = new ToggleGroup();

        seleccion_tipo_recomendacion_1.setToggleGroup(grupo_radio_recomendacion);
        seleccion_tipo_recomendacion_2.setToggleGroup(grupo_radio_recomendacion);

        TipoRecomendacion.getChildren().addAll(label_tipo_recomendacion, seleccion_tipo_recomendacion_1, seleccion_tipo_recomendacion_2);


        HBox tipoDistancia = new HBox();

        Label label_tipo_distancia = new Label("Tipo de distancia");

        RadioButton seleccion_tipo_distancia_1 = new RadioButton("Manhattan");
        RadioButton seleccion_tipo_distancia_2 = new RadioButton("Euclídea");

        ToggleGroup grupo_radio_distancia = new ToggleGroup();

        seleccion_tipo_distancia_1.setToggleGroup(grupo_radio_distancia);
        seleccion_tipo_distancia_2.setToggleGroup(grupo_radio_distancia);

        TipoRecomendacion.getChildren().addAll(label_tipo_distancia, seleccion_tipo_distancia_1, seleccion_tipo_distancia_2);


        general.getChildren().addAll(TipoRecomendacion, tipoDistancia);

        stagePrimario.setTitle("Recomendadorneitor 3000");
        stagePrimario.setScene(new Scene(general, 400, 400));
        stagePrimario.show();




    }
}
